package org.esupportail.helpdesk.data.web.beans;

import lombok.*;
import lombok.experimental.Wither;
import org.springframework.hateoas.ResourceSupport;

import java.io.Serializable;
import java.util.Date;

@Getter
@Wither
@ToString
@AllArgsConstructor(staticName = "user")
public class User extends ResourceSupport implements Serializable {

    private static long serialVersionUID = 3294650527598576031L;

    private String pk;

    private boolean admin;

    @Wither(AccessLevel.NONE)
    private String displayName;

    @Wither(AccessLevel.NONE)
    private String email;

    private String encodedAttributes;

//    private HCategory controlPanelCategoryFilter;
//
//    private HDepartment journalDepartmentFilter;
//
//    private HDepartment searchDepartmentFilter;
//
//    private HDepartment controlPanelManagerDepartmentFilter;
//
//    private HDepartment controlPanelUserDepartmentFilter;


    private String language;

    @Wither(AccessLevel.NONE)
    private String realId;

    @Getter
    @Wither
    @ToString
    @AllArgsConstructor(staticName = "authInfos")
    public static class AuthInfos extends ResourceSupport implements Serializable {

        private static final long serialVersionUID = 3623340514055455821L;

        private Date authLimit;

        private String authSecret;

        private String authType;

        private String password;

        private AuthInfos() {
            super();
        }

        public static AuthInfos authInfos() {
            return new AuthInfos();
        }
    }

    @Getter
    @Wither
    @ToString
    @AllArgsConstructor(staticName = "preferences")
    public static class Preferences extends ResourceSupport implements Serializable {

        private static final long serialVersionUID = 4934035475015654460L;

        private Boolean advancedSearch;

        private Boolean bookmarkMonitoring;

        private String controlPanelUserInvolvementFilter;

        private String controlPanelManagerInvolvementFilter;

        private String controlPanelManagerStatusFilter;

        private String storedControlPanelOrder;

        private Integer controlPanelPageSize;

        private Integer controlPanelRefreshDelay;

        private String controlPanelUserStatusFilter;

        private Boolean controlPanelUserInterface;

        private Boolean controlPanelCategoryMemberFilter;

        private String controlPanelColumns;

        private Date departmentSelectionContextTime;

        private Boolean expirationMonitoring;

        private Boolean invitedMonitoring;

        private Integer journalPageSize;

        private Boolean ownerMonitoring;

        private String pageTransition;

        private Boolean receiveFaqReports;

        private Boolean receiveManagerMonitoring;

        private Boolean receiveTicketReports;

        private Boolean receiveTicketReportsAllInOne;

        private Date searchDate1;

        private Date searchDate2;

        private Boolean searchSortByDate;

        private String searchTypeFilter;

        private Boolean showAddTicketHelp;

        private Boolean showPopupOnClosure;

        private Boolean showTicketAfterClosure;

        private Preferences() {
            super();
        }

        public static Preferences preferences() {
            return new Preferences();
        }

    }

    private User(String displayName, String email, String realId) {
        super();
        this.displayName = displayName;
        this.email = email;
        this.realId = realId;
    }

    public static User user(String displayName, String email, String realId) {
        return new User(displayName, email, realId);
    }
}
