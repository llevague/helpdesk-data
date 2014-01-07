package org.esupportail.helpdesk.data.web.controllers;

import fj.F;
import fj.data.List;
import fj.data.Option;
import org.esupportail.helpdesk.data.dao.entities.HTicket;
import org.esupportail.helpdesk.data.dao.entities.HUser;
import org.esupportail.helpdesk.data.dao.services.IUserService;
import org.esupportail.helpdesk.data.web.beans.*;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;

import static java.lang.String.format;
import static org.esupportail.helpdesk.data.web.beans.User.AuthInfos;
import static org.esupportail.helpdesk.data.web.beans.User.Preferences;
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
        return huser.map(new F<HUser, ResponseEntity<User>>() {
            public ResponseEntity<User> f(final HUser h) {
                final User user = User.user(h.getDisplayName(), h.getEmail(), h.getRealId())
                        .withPk(h.getId())
                        .withAdmin(h.isAdmin())
                        .withEncodedAttributes(h.getEncodedAttributes())
                        .withLanguage(h.getLanguage());
                user.add(linkTo(UserController.class)
                        .slash(user.getPk())
                        .slash("auth-infos")
                        .withRel("auth-infos"));
                user.add(linkTo(UserController.class)
                        .slash(user.getPk())
                        .slash("preferences")
                        .withRel("preferences"));
                user.add(linkTo(UserController.class)
                        .slash(user.getPk())
                        .slash("tickets-owned")
                        .withRel("tickets-owned"));
                return new ResponseEntity<>(user, HttpStatus.OK);
            }
        }).orSome(new ResponseEntity<User>(HttpStatus.NOT_FOUND));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}/auth-infos")
    public ResponseEntity<AuthInfos> userAuthInfos(@PathVariable final String id) {
        final Option<HUser> huser = userService.getUserById(id);
        return huser.map(new F<HUser, ResponseEntity<AuthInfos>>() {
            public ResponseEntity<AuthInfos> f(final HUser h) {
                final AuthInfos authInfos = AuthInfos.authInfos()
                        .withAuthLimit(h.getAuthLimit())
                        .withAuthSecret(h.getAuthSecret())
                        .withAuthType(h.getAuthType())
                        .withPassword(h.getPassword());

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
                final Preferences preferences = Preferences.preferences()
                        .withAdvancedSearch(h.getAdvancedSearch())
                        .withBookmarkMonitoring(h.getBookmarkMonitoring())
                        .withControlPanelUserInvolvementFilter(h.getControlPanelUserInvolvementFilter())
                        .withControlPanelManagerInvolvementFilter(h.getControlPanelManagerInvolvementFilter())
                        .withControlPanelManagerStatusFilter(h.getControlPanelManagerStatusFilter())
                        .withStoredControlPanelOrder(h.getStoredControlPanelOrder())
                        .withControlPanelPageSize(h.getControlPanelPageSize())
                        .withControlPanelRefreshDelay(h.getControlPanelRefreshDelay())
                        .withControlPanelUserStatusFilter(h.getControlPanelUserStatusFilter())
                        .withControlPanelUserInterface(h.getControlPanelUserInterface())
                        .withControlPanelCategoryMemberFilter(h.getControlPanelCategoryMemberFilter())
                        .withControlPanelColumns(h.getControlPanelColumns())
                        .withDepartmentSelectionContextTime(h.getDepartmentSelectionContextTime())
                        .withExpirationMonitoring(h.getExpirationMonitoring())
                        .withInvitedMonitoring(h.getInvitedMonitoring())
                        .withJournalPageSize(h.getJournalPageSize())
                        .withOwnerMonitoring(h.getOwnerMonitoring())
                        .withPageTransition(h.getPageTransition())
                        .withReceiveFaqReports(h.getReceiveFaqReports())
                        .withReceiveManagerMonitoring(h.getReceiveManagerMonitoring())
                        .withReceiveTicketReports(h.getReceiveTicketReports())
                        .withReceiveTicketReportsAllInOne(h.getReceiveTicketReportsAllInOne())
                        .withSearchDate1(h.getSearchDate1())
                        .withSearchDate2(h.getSearchDate2())
                        .withSearchSortByDate(h.getSearchSortByDate())
                        .withSearchTypeFilter(h.getSearchTypeFilter())
                        .withShowAddTicketHelp(h.getShowAddTicketHelp())
                        .withShowPopupOnClosure(h.getShowPopupOnClosure())
                        .withShowTicketAfterClosure(h.getShowTicketAfterClosure());

                preferences.add(linkTo(UserController.class)
                        .slash(h.getId())
                        .withRel("user"));
                return new ResponseEntity<>(preferences, HttpStatus.OK);
            }
        }).orSome(new ResponseEntity<Preferences>(HttpStatus.NOT_FOUND));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}/tickets-owned")
    public ResponseEntity<ResourceSupport> userOwnedTickets(@PathVariable final String id) {
        final List<HTicket> tickets = userService.getUserOwnedTickets(id);
        final ResourceSupport result = new ResourceSupport();
        for (HTicket ticket: tickets) {
            result.add(linkTo(TicketController.class)
                    .slash(ticket.getId())
                    .withRel(format("%s", ticket.getId())));
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
