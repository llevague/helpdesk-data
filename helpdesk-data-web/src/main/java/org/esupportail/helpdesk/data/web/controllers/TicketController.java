package org.esupportail.helpdesk.data.web.controllers;


import fj.Effect;
import fj.F;
import fj.F2;
import fj.data.Option;
import org.esupportail.helpdesk.data.dao.entities.HTicket;
import org.esupportail.helpdesk.data.dao.entities.HUser;
import org.esupportail.helpdesk.data.dao.services.ITicketService;
import org.esupportail.helpdesk.data.web.beans.Ticket;
import org.esupportail.helpdesk.data.web.utils.Transform;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;

import static fj.data.Option.fromNull;
import static fj.data.Option.some;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@RestController
@RequestMapping(value = "/tickets")
public class TicketController {

    @Inject
    private ITicketService ticketService;

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity<Ticket> getUser(@PathVariable final Long id) {
        final Option<HTicket> hticket = ticketService.getTicketById(id);

        final F<HUser, Option<String>> userId = new F<HUser, Option<String>>() {
            public Option<String> f(HUser hUser) {
                return some(hUser.getId());
            }
        };
        final F2<Ticket, String, Effect<String>> addUserLink = new F2<Ticket, String, Effect<String>>() {
            public Effect<String> f(final Ticket ticket, final String rel) {
                return new Effect<String>() {
                    public void e(String id) {
                        ticket.add(linkTo(UserController.class)
                                .slash(id)
                                .withRel(rel));
                    }
                };
            }
        };

        return hticket.map(new F<HTicket, ResponseEntity<Ticket>>() {
            public ResponseEntity<Ticket> f(final HTicket h) {
                final Ticket ticket = Transform.hTicketToTicket.f(h);
                fromNull(h.getOwner()).bind(userId).foreach(addUserLink.f(ticket, "owner"));
                fromNull(h.getManager()).bind(userId).foreach(addUserLink.f(ticket,"manager"));
                fromNull(h.getCreator()).bind(userId).foreach(addUserLink.f(ticket,"creator"));

                ticket.add(linkTo(TicketController.class)
                        .slash(ticket.getPk())
                        .withSelfRel());

                return new ResponseEntity<>(ticket, HttpStatus.OK);
            }
        }).orSome(new ResponseEntity<Ticket>(HttpStatus.NOT_FOUND));
    }
}
