package org.esupportail.helpdesk.data.web.controllers;


import fj.Effect;
import fj.F;
import fj.F2;
import fj.P3;
import fj.data.Option;
import org.esupportail.helpdesk.data.dao.entities.HTicket;
import org.esupportail.helpdesk.data.dao.entities.HUser;
import org.esupportail.helpdesk.data.dao.services.ITicketService;
import org.esupportail.helpdesk.data.web.beans.Ticket;
import org.esupportail.helpdesk.data.web.utils.Transform;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.inject.Inject;

import static fj.P.p;
import static fj.data.Option.fromNull;
import static fj.data.Option.some;
import static java.lang.String.format;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@RestController
@RequestMapping(value = "/tickets")
public class TicketController {

    @Inject
    private ITicketService ticketService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<ResourceSupport> listTickets(@RequestParam(defaultValue = "0") final int page,
                                                     @RequestParam(defaultValue = "10") final int size,
                                                     @RequestParam(defaultValue = "id") final String sort) {
        final Page<HTicket> pageTickets = ticketService.getTickets(page, size, new Sort(sort));

        final F<P3<Integer, Integer, String>, Link> buildPageLink =
                new F<P3<Integer, Integer, String>, Link>() {
                    public Link f(P3<Integer, Integer, String> p) {
                        String path = linkTo(TicketController.class)
                                .toUriComponentsBuilder()
                                .queryParam("page", p._1())
                                .queryParam("size", p._2())
                                .queryParam("sort", sort)
                                .build()
                                .toUriString();
                        Link link = new Link(path, p._3());
                        return link;
                    }
                };

        ResourceSupport result = new ResourceSupport();
        for (HTicket hTicket : pageTickets.getContent()) {
            result.add(linkTo(TicketController.class).slash(hTicket.getId()).withRel(format("%s", hTicket.getId())));
        }
        result.add(buildPageLink.f(p(pageTickets.getNumber(), pageTickets.getSize(), Link.REL_SELF)));
        result.add(buildPageLink.f(p(0, pageTickets.getSize(), Link.REL_FIRST)));
        if (pageTickets.hasPreviousPage()) {
            result.add(buildPageLink.f(p(pageTickets.getNumber() - 1, pageTickets.getSize(), Link.REL_PREVIOUS)));
        }
        if (pageTickets.hasNextPage()) {
            result.add(buildPageLink.f(p(pageTickets.getNumber() + 1, pageTickets.getSize(), Link.REL_NEXT)));
        }
        result.add(buildPageLink.f(p(pageTickets.getTotalPages() - 1, pageTickets.getSize(), Link.REL_LAST)));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

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
