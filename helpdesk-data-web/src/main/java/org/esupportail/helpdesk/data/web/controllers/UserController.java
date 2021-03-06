package org.esupportail.helpdesk.data.web.controllers;

import fj.*;
import fj.data.List;
import fj.data.Option;
import org.esupportail.helpdesk.data.dao.entities.HTicket;
import org.esupportail.helpdesk.data.dao.entities.HUser;
import org.esupportail.helpdesk.data.dao.services.ITicketService;
import org.esupportail.helpdesk.data.dao.services.IUserService;
import org.esupportail.helpdesk.data.web.beans.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;

import static fj.P.p;
import static fj.data.List.iterableList;
import static java.lang.String.format;
import static org.esupportail.helpdesk.data.web.beans.User.AuthInfos;
import static org.esupportail.helpdesk.data.web.beans.User.Preferences;
import static org.esupportail.helpdesk.data.web.utils.Pagination.buildPaginationLink;
import static org.esupportail.helpdesk.data.web.utils.Transform.hUserToAuthInfos;
import static org.esupportail.helpdesk.data.web.utils.Transform.hUserToPreferences;
import static org.esupportail.helpdesk.data.web.utils.Transform.hUsertoUser;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Inject
    private IUserService userService;

    @Inject
    private ITicketService ticketService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<ResourceSupport> listUsers(@RequestParam(defaultValue = "0") final int page,
                                                     @RequestParam(defaultValue = "10") final int size,
                                                     @RequestParam(defaultValue = "id") final String sort) {
        final Page<HUser> pageUsers = userService.getUsers(page, size, new Sort(sort));

        ResourceSupport result = new ResourceSupport();
        for (HUser huser : pageUsers.getContent()) {
            result.add(linkTo(UserController.class).slash(huser.getId()).withRel(huser.getId()));
        }
        result.add(buildPaginationLink.f(p(pageUsers.getNumber(), pageUsers.getSize(), sort, Link.REL_SELF)));
        result.add(buildPaginationLink.f(p(0, pageUsers.getSize(), sort, Link.REL_FIRST)));
        if (pageUsers.hasPreviousPage()) {
            result.add(buildPaginationLink.f(p(pageUsers.getNumber() - 1, pageUsers.getSize(), sort, Link.REL_PREVIOUS)));
        }
        if (pageUsers.hasNextPage()) {
            result.add(buildPaginationLink.f(p(pageUsers.getNumber() + 1, pageUsers.getSize(), sort, Link.REL_NEXT)));
        }
        result.add(buildPaginationLink.f(p(pageUsers.getTotalPages() - 1, pageUsers.getSize(), sort, Link.REL_LAST)));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity<User> getUser(@PathVariable final String id) {
        final Option<HUser> huser = userService.getUserById(id);

        final F3<User, String, String, User> addLink = new F3<User, String, String, User>() {
            public User f(final User user, final String href, final String rel) {
                user.add(linkTo(UserController.class)
                        .slash(user.getPk())
                        .slash(href)
                        .withRel(rel));
                return user;
            }
        };

        return huser.map(new F<HUser, ResponseEntity<User>>() {
            public ResponseEntity<User> f(final HUser h) {
                User user = hUsertoUser.f(h);
                user.add(linkTo(UserController.class)
                        .slash(user.getPk()).withSelfRel());
                user = addLink.f(user, "auth-infos", "auth-infos");
                user = addLink.f(user, "preferences", "preferences");
                user = addLink.f(user, "tickets/owned", "tickets-owned");
                user = addLink.f(user, "tickets/managed", "tickets-managed");
                user = addLink.f(user, "tickets/created", "tickets-created");

                return new ResponseEntity<>(user, HttpStatus.OK);
            }
        }).orSome(new ResponseEntity<User>(HttpStatus.NOT_FOUND));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}/auth-infos")
    public ResponseEntity<AuthInfos> userAuthInfos(@PathVariable final String id) {
        final Option<HUser> huser = userService.getUserById(id);
        return huser.map(new F<HUser, ResponseEntity<AuthInfos>>() {
            public ResponseEntity<AuthInfos> f(final HUser h) {
                final AuthInfos authInfos = hUserToAuthInfos.f(h);

                authInfos.add(linkTo(UserController.class)
                        .slash(h.getId())
                        .withRel("user"));
                return new ResponseEntity<>(authInfos, HttpStatus.OK);
            }
        }).orSome(new ResponseEntity<AuthInfos>(HttpStatus.NOT_FOUND));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}/preferences")
    public ResponseEntity<Preferences> userPreferences(@PathVariable final String id) {
        final Option<HUser> user = userService.getUserById(id);
        return user.map(new F<HUser, ResponseEntity<Preferences>>() {
            public ResponseEntity<Preferences> f(final HUser h) {
                final Preferences preferences = hUserToPreferences.f(h);

                preferences.add(linkTo(UserController.class)
                        .slash(h.getId())
                        .withRel("user"));
                return new ResponseEntity<>(preferences, HttpStatus.OK);
            }
        }).orSome(new ResponseEntity<Preferences>(HttpStatus.NOT_FOUND));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}/tickets/owned")
    public ResponseEntity<ResourceSupport> userOwnedTickets(@PathVariable final String id) {
        return buildResponseTickets.f(ticketService.getOwnerTickets(id));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}/tickets/managed")
    public ResponseEntity<ResourceSupport> userManagedTickets(@PathVariable final String id) {
        return buildResponseTickets.f(ticketService.getManagerTickets(id));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}/tickets/created")
    public ResponseEntity<ResourceSupport> userCreatedTickets(@PathVariable final String id) {
        return buildResponseTickets.f(ticketService.getCreatorTickets(id));
    }

    private F<List<HTicket>, ResponseEntity<ResourceSupport>> buildResponseTickets = new F<List<HTicket>, ResponseEntity<ResourceSupport>>() {
        public ResponseEntity<ResourceSupport> f(final List<HTicket> list) {
            final ResourceSupport result = new ResourceSupport();
            list.foreach(new Effect<HTicket>() {
                public void e(HTicket ticket) {
                    result.add(linkTo(TicketController.class)
                            .slash(ticket.getId())
                            .withRel(format("%s", ticket.getId())));
                }
            });
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
    };

}
