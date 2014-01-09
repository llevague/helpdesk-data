package org.esupportail.helpdesk.data.web.controllers;

import fj.Effect;
import fj.F;
import fj.F2;
import fj.data.List;
import fj.data.Option;
import org.esupportail.helpdesk.data.dao.entities.HTicket;
import org.esupportail.helpdesk.data.dao.entities.HUser;
import org.esupportail.helpdesk.data.dao.services.IUserService;
import org.esupportail.helpdesk.data.web.beans.*;
import org.esupportail.helpdesk.data.web.utils.Transform;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;

import java.util.ArrayList;

import static fj.data.List.iterableList;
import static java.lang.String.format;
import static org.esupportail.helpdesk.data.web.beans.User.AuthInfos;
import static org.esupportail.helpdesk.data.web.beans.User.Preferences;
import static org.esupportail.helpdesk.data.web.utils.Transform.hUserToAuthInfos;
import static org.esupportail.helpdesk.data.web.utils.Transform.hUserToPreferences;
import static org.esupportail.helpdesk.data.web.utils.Transform.hUsertoUser;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Inject
    private IUserService userService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<ResourceSupport> listUsers() {
        ResourceSupport result = new ResourceSupport();
        for (HUser huser : userService.getUsers()) {
            result.add(linkTo(UserController.class).slash(huser.getId()).withRel(huser.getId()));
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity<User> getUser(@PathVariable final String id) {
        final Option<HUser> huser = userService.getUserById(id);

        final F2<User, String, User> addLink = new F2<User, String, User>() {
            public User f(final User user, final String rel) {
                user.add(linkTo(UserController.class)
                        .slash(user.getPk())
                        .slash(rel)
                        .withRel(rel));
                return user;
            }
        };

        return huser.map(new F<HUser, ResponseEntity<User>>() {
            public ResponseEntity<User> f(final HUser h) {
                User user = hUsertoUser.f(h);
                user = addLink.f(user, "auth-infos");
                user = addLink.f(user, "preferences");
                user = addLink.f(user, "tickets-owned");
                user.add(linkTo(UserController.class)
                        .slash(user.getPk()).withSelfRel());

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

    @RequestMapping(method = RequestMethod.GET, value = "/{id}/tickets-owned")
    public ResponseEntity<ResourceSupport> userOwnedTickets(@PathVariable final String id) {
        final List<HTicket> tickets = userService.getUserById(id).map(new F<HUser, List<HTicket>>() {
            public List<HTicket> f(HUser hUser) {
                return iterableList(hUser.getOwnedTickets());
            }
        }).orSome(List.<HTicket>nil());

        final ResourceSupport result = new ResourceSupport();
        for (HTicket ticket: tickets) {
            result.add(linkTo(TicketController.class)
                    .slash(ticket.getId())
                    .withRel(format("%s", ticket.getId())));
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
