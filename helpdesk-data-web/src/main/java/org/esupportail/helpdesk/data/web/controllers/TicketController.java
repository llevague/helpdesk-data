package org.esupportail.helpdesk.data.web.controllers;


import fj.F;
import fj.data.Option;
import org.esupportail.helpdesk.data.dao.entities.HTicket;
import org.esupportail.helpdesk.data.dao.services.ITicketService;
import org.esupportail.helpdesk.data.web.beans.Ticket;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@RestController
@RequestMapping(value = "/tickets")
public class TicketController {

    @Inject
    private ITicketService ticketService;

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity<Ticket> getUser(@PathVariable final Long id) {
        final Option<HTicket> hticket = ticketService.getTicketById(id);
        return hticket.map(new F<HTicket, ResponseEntity<Ticket>>() {
            public ResponseEntity<Ticket> f(final HTicket h) {
                final Ticket ticket = Ticket.ticket(h.getLabel())
                        .withPk(h.getId())
                        .withChargeTime(h.getChargeTime())
                        .withClosureTime(h.getClosureTime())
                        .withComputer(h.getComputer())
                        .withCreationDate(h.getCreationDate())
                        .withCreationDay(h.getCreationDay())
                        .withCreationDow(h.getCreationDow())
                        .withCreationHour(h.getCreationHour())
                        .withCreationMonth(h.getCreationMonth())
                        .withCreationYear(h.getCreationYear())
                        .withEffectiveScope(h.getEffectiveScope())
                        .withLastActionDate(h.getLastActionDate())
                        .withManagerDisplayName(h.getManagerDisplayName())
                        .withOrigin(h.getOrigin())
                        .withPriorityLevel(h.getPriorityLevel())
                        .withRecallDate(h.getRecallDate())
                        .withScope(h.getScope())
                        .withSpentTime(h.getSpentTime())
                        .withStatus(h.getStatus());
                Option<String> owner = ticketService.getTicketOwner(id);
                Option<String> manager = ticketService.getTicketManager(id);
                Option<String> creator = ticketService.getTicketCreator(id);

                ticket.add(linkTo(TicketController.class)
                        .slash(ticket.getPk())
                        .withSelfRel());
                if(owner.isSome())
                    ticket.add(linkTo(UserController.class)
                            .slash(owner.some())
                            .withRel("owner"));
                if(manager.isSome())
                    ticket.add(linkTo(UserController.class)
                            .slash(manager.some())
                            .withRel("manager"));
                if(creator.isSome())
                    ticket.add(linkTo(UserController.class)
                            .slash(creator.some())
                            .withRel("creator"));
                return new ResponseEntity<>(ticket, HttpStatus.OK);
            }
        }).orSome(new ResponseEntity<Ticket>(HttpStatus.NOT_FOUND));
    }
}
