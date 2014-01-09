package org.esupportail.helpdesk.data.web.utils;

import fj.F;
import org.esupportail.helpdesk.data.dao.entities.HTicket;
import org.esupportail.helpdesk.data.dao.entities.HUser;
import org.esupportail.helpdesk.data.web.beans.Ticket;
import org.esupportail.helpdesk.data.web.beans.User;

import static org.esupportail.helpdesk.data.web.beans.User.AuthInfos;
import static org.esupportail.helpdesk.data.web.beans.User.Preferences;

public final class Transform {


    public static final F<HUser, User> hUsertoUser = new F<HUser, User>() {
        public User f(HUser h) {
            return User.user(h.getDisplayName(), h.getEmail(), h.getRealId())
                    .withPk(h.getId())
                    .withAdmin(h.isAdmin())
                    .withEncodedAttributes(h.getEncodedAttributes())
                    .withLanguage(h.getLanguage());
        }
    };

    public static final F<HUser, AuthInfos> hUserToAuthInfos = new F<HUser, AuthInfos>() {
        public AuthInfos f(HUser h) {
            return AuthInfos.authInfos()
                    .withAuthLimit(h.getAuthLimit())
                    .withAuthSecret(h.getAuthSecret())
                    .withAuthType(h.getAuthType())
                    .withPassword(h.getPassword());
        }
    };

    public static final F<HUser, Preferences> hUserToPreferences = new F<HUser, Preferences>() {
        public Preferences f(HUser h) {
            return Preferences.preferences()
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
        }
    };

    public static final F<HTicket, Ticket> hTicketToTicket = new F<HTicket, Ticket>() {
        public Ticket f(HTicket h) {
            return Ticket.ticket(h.getLabel())
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
        }
    };
}
