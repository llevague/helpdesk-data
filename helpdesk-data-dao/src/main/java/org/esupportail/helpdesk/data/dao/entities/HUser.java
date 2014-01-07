package org.esupportail.helpdesk.data.dao.entities;

import java.util.*;
import javax.persistence.*;

import static org.esupportail.helpdesk.data.dao.enums.ControlPanel.MANAGER_INVOLVEMENT_FILTER_ANY;
import static org.esupportail.helpdesk.data.dao.enums.ControlPanel.STATUS_FILTER_ANY;
import static org.esupportail.helpdesk.data.dao.enums.ControlPanel.USER_INVOLVEMENT_FILTER_ANY;
import static org.esupportail.helpdesk.data.dao.enums.Search.TYPE_FILTER_ALL;

@Entity
@Table(name = "h_user")
public class HUser {

    @Id
    private String id;

    /**
     * True for administrators.
     */
    @Basic
    @Column(name = "admi")
    private boolean admin;

    /**
     * True for the advanced search, false for simple.
     */
    @Basic
    @Column(name = "adva_sear")
    private Boolean advancedSearch;

    /**
     * The validity of the auth secret.
     */
    @Basic
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "auth_limit")
    private Date authLimit;

    /**
     * The auth secret.
     */
    @Basic
    @Column(name = "auth_secr")
    private String authSecret;

    /**
     * The auth type.
     */
    @Basic
    @Column(name = "auth_type")
    private String authType;

    /**
     * True if the user monitors his bookmarked tickets.
     */
    @Basic
    @Column(name = "book_moni")
    private Boolean bookmarkMonitoring;

    /**
     * The involvement filter of the control panel for the user interface.
     */
    @Basic
    @Column(name = "cont_pane_invo_filt2")
    private String controlPanelUserInvolvementFilter;

    /**
     * The involvement filter of the control panel for the manager interface.
     */
    @Basic
    @Column(name = "cont_pane_mana_invo_filt")
    private String controlPanelManagerInvolvementFilter;

    /**
     * The status filter of the manager control panel.
     */
    @Basic
    @Column(name = "cont_pane_mana_stat_filt")
    private String controlPanelManagerStatusFilter;

    /**
     * The order of the control panel.
     */
    @Basic
    @Column(name = "cont_pane_orde")
    private String storedControlPanelOrder;

    /**
     * The page size of the control panel.
     */
    @Basic
    @Column(name = "cont_pane_page_size", columnDefinition = "INT")
    private Integer controlPanelPageSize;

    /**
     * The refresh delay of the control panel.
     */
    @Basic
    @Column(name = "cont_pane_refr_dela", columnDefinition = "INT")
    private Integer controlPanelRefreshDelay;

    /**
     * The status filter of the user control panel.
     */
    @Basic
    @Column(name = "cont_pane_stat_filt2")
    private String controlPanelUserStatusFilter;

    /**
     * The interface of the control panel.
     */
    @Basic
    @Column(name = "cont_pane_user_inte")
    private Boolean controlPanelUserInterface;

    /**
     * True to select only category members tickets on the control panel.
     */
    @Basic
    @Column(name = "cont_panel_cate_memb_filt")
    private Boolean controlPanelCategoryMemberFilter;

    /**
     * The columns to print on the control panel.
     */
    @Basic
    @Column(name = "cont_panel_colu")
    private String controlPanelColumns;

    /**
     * The last time the department selection was valid (for the user).
     */
    @Basic
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "depa_sele_cont_time")
    private Date departmentSelectionContextTime;

    /**
     * Display Name of the user.
     */
    @Basic
    @Column(name = "disp_name")
    private String displayName;

    /**
     * Email of the user (memorized for Shibboleth users.
     */
    @Basic
    @Column(name = "email")
    private String email;

    /**
     * The attributes.
     */
    @Basic
    @Column(name = "enco_attr")
    private String encodedAttributes;

    /**
     * True to receive expiration emails.
     */
    @Basic
    @Column(name = "expi_moni")
    private Boolean expirationMonitoring;

    /**
     * The category filter of the control panel.
     */
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "cont_panel_cate_filt")
    private HCategory controlPanelCategoryFilter;

    /**
     * The department filter for the journal page.
     */
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "jour_depa_filt")
    private HDepartment journalDepartmentFilter;

    /**
     * The department filter of the search interface.
     */
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "sear_depa_filt")
    private HDepartment searchDepartmentFilter;

    /**
     * The department filter of the manager control panel.
     */
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "cont_pane_mana_depa_filt")
    private HDepartment controlPanelManagerDepartmentFilter;

    /**
     * The department filter of the user control panel.
     */
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "cont_pane_depa_filt")
    private HDepartment controlPanelUserDepartmentFilter;

    /**
     * True if the user monitors the tickets (s)he is is invited for.
     */
    @Basic
    @Column(name = "invi_moni")
    private Boolean invitedMonitoring;

    /**
     * The page size for the journal page.
     */
    @Basic
    @Column(name = "jour_page_size", columnDefinition = "INT")
    private Integer journalPageSize;

    /**
     * The preferred language.
     */
    @Basic
    @Column(name = "lang")
    private String language;

    /**
     * True if the user monitors the tickets (s)he owns.
     */
    @Basic
    @Column(name = "owne_moni")
    private Boolean ownerMonitoring;

    /**
     * The transition between pages.
     */
    @Basic
    @Column(name = "page_tran")
    private String pageTransition;

    /**
     * The password, null for local users.
     */
    @Basic
    @Column(name = "pass")
    private String password;

    /**
     * Real id of the user.
     */
    @Basic
    @Column(name = "real_id")
    private String realId;

    /**
     * True to receive FAQ reports.
     */
    @Basic
    @Column(name = "rece_faq_repo")
    private Boolean receiveFaqReports;

    /**
     * True to receive manager monitoring.
     */
    @Basic
    @Column(name = "rece_mana_moni")
    private Boolean receiveManagerMonitoring;

    /**
     * True to receive ticket reports.
     */
    @Basic
    @Column(name = "rece_repo")
    private Boolean receiveTicketReports;

    /**
     * True to receive all the ticket reports in one.
     */
    @Basic
    @Column(name = "rece_repo_all_in_one")
    private Boolean receiveTicketReportsAllInOne;

    /**
     * The search date 1.
     */
    @Basic
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "sear_date_1")
    private Date searchDate1;

    /**
     * The search date 2.
     */
    @Basic
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "sear_date_2")
    private Date searchDate2;

    /**
     * True to sort search results by date.
     */
    @Basic
    @Column(name = "sear_sort_by_date")
    private Boolean searchSortByDate;

    /**
     * The search type filter.
     */
    @Basic
    @Column(name = "sear_type_filt")
    private String searchTypeFilter;

    /**
     * True to show help when entering tickets.
     */
    @Basic
    @Column(name = "show_add_tick_help")
    private Boolean showAddTicketHelp;

    /**
     * True to show a popup on ticket closure.
     */
    @Basic
    @Column(name = "show_popu_on_clos")
    private Boolean showPopupOnClosure;

    /**
     * True to show tickets after closure.
     */
    @Basic
    @Column(name = "show_tick_afte_clos")
    private Boolean showTicketAfterClosure;

    /**
     * The start page.
     */
    @Basic
    @Column(name = "star_page")
    private String startPage;

    @OneToMany(targetEntity = HAction.class, mappedBy = "user", cascade = CascadeType.MERGE)
    private Set<HAction> actions = new HashSet<>();

    @OneToMany(targetEntity = HAction.class, mappedBy = "ticketOwnerBefore", cascade = CascadeType.MERGE)
    private Set<HAction> actionsOwnerBefore = new HashSet<>();

    @OneToMany(targetEntity = HAction.class, mappedBy = "ticketOwnerAfter", cascade = CascadeType.MERGE)
    private Set<HAction> actionsOwnerAfter = new HashSet<>();

    @OneToMany(targetEntity = HAction.class, mappedBy = "managerBefore", cascade = CascadeType.MERGE)
    private Set<HAction> actionsManagerBefore = new HashSet<>();

    @OneToMany(targetEntity = HAction.class, mappedBy = "managerAfter", cascade = CascadeType.MERGE)
    private Set<HAction> actionsManagerAfter = new HashSet<>();

    @OneToMany(targetEntity = HAction.class, mappedBy = "invitedUser", cascade = CascadeType.MERGE)
    private Set<HAction> actionsInvited = new HashSet<>();

    @OneToMany(targetEntity = HAlert.class, mappedBy = "user", cascade = CascadeType.MERGE)
    private Set<HAlert> alerts = new HashSet<>();

    @OneToMany(targetEntity = HArchivedAction.class, mappedBy = "user", cascade = CascadeType.MERGE)
    private Set<HArchivedAction> archivedActions = new HashSet<>();

    @OneToMany(targetEntity = HArchivedAction.class, mappedBy = "ticketOwnerBefore", cascade = CascadeType.MERGE)
    private Set<HArchivedAction> archivedActionsOwnerBefore = new HashSet<>();

    @OneToMany(targetEntity = HArchivedAction.class, mappedBy = "ticketOwnerAfter", cascade = CascadeType.MERGE)
    private Set<HArchivedAction> archivedActionsOwnerAfter = new HashSet<>();

    @OneToMany(targetEntity = HArchivedAction.class, mappedBy = "managerBefore", cascade = CascadeType.MERGE)
    private Set<HArchivedAction> archivedActionsManagerBefore = new HashSet<>();

    @OneToMany(targetEntity = HArchivedAction.class, mappedBy = "managerAfter", cascade = CascadeType.MERGE)
    private Set<HArchivedAction> archivedActionsManagerAfter = new HashSet<>();

    @OneToMany(targetEntity = HArchivedAction.class, mappedBy = "invitedUser", cascade = CascadeType.MERGE)
    private Set<HArchivedAction> archivedActionsInvited = new HashSet<>();

    @OneToMany(targetEntity = HArchivedFileInfo.class, mappedBy = "user", cascade = CascadeType.MERGE)
    private Set<HArchivedFileInfo> archivedFileInfos = new HashSet<>();

    @OneToMany(targetEntity = HArchivedInvitation.class, mappedBy = "user", cascade = CascadeType.MERGE)
    private Set<HArchivedInvitation> archivedInvitations = new HashSet<>();

    @OneToMany(targetEntity = HArchivedTicket.class, mappedBy = "owner", cascade = CascadeType.MERGE)
    private Set<HArchivedTicket> archivedOwnedTickets = new HashSet<>();

    @OneToMany(targetEntity = HArchivedTicket.class, mappedBy = "manager", cascade = CascadeType.MERGE)
    private Set<HArchivedTicket> archivedManagedTickets = new HashSet<>();

    @OneToMany(targetEntity = HArchivedTicket.class, mappedBy = "creator", cascade = CascadeType.MERGE)
    private Set<HArchivedTicket> archivedCreatedTickets = new HashSet<>();

    @OneToMany(targetEntity = HBookmark.class, mappedBy = "user", cascade = CascadeType.MERGE)
    private Set<HBookmark> bookmarks = new HashSet<>();

    @OneToMany(targetEntity = HCategoryMember.class, mappedBy = "user", cascade = CascadeType.MERGE)
    private Set<HCategoryMember> categoryMembers = new HashSet<>();

    @OneToMany(targetEntity = HDepartmentInvitation.class, mappedBy = "user", cascade = CascadeType.MERGE)
    private Set<HDepartmentInvitation> departmentInvitations = new HashSet<>();

    @OneToMany(targetEntity = HDepartmentManager.class, mappedBy = "user", cascade = CascadeType.MERGE)
    private Set<HDepartmentManager> departmentManagers = new HashSet<>();

    @OneToMany(targetEntity = HDepartmentSelectionConfig.class, mappedBy = "user", cascade = CascadeType.MERGE)
    private Set<HDepartmentSelectionConfig> departmentSelectionConfigs = new HashSet<>();

    @OneToMany(targetEntity = HFaqEvent.class, mappedBy = "author", cascade = CascadeType.MERGE)
    private Set<HFaqEvent> faqEvents = new HashSet<>();

    @OneToMany(targetEntity = HFileInfo.class, mappedBy = "user", cascade = CascadeType.MERGE)
    private Set<HFileInfo> fileInfos = new HashSet<>();

    @OneToMany(targetEntity = HHistoryItem.class, mappedBy = "user", cascade = CascadeType.MERGE)
    private Set<HHistoryItem> historyItems = new HashSet<>();

    @OneToMany(targetEntity = HInvitation.class, mappedBy = "user", cascade = CascadeType.MERGE)
    private Set<HInvitation> invitations = new HashSet<>();

    @OneToMany(targetEntity = HResponse.class, mappedBy = "user", cascade = CascadeType.MERGE)
    private Set<HResponse> responses = new HashSet<>();

    @OneToMany(targetEntity = HTicketMonitoring.class, mappedBy = "user", cascade = CascadeType.MERGE)
    private Set<HTicketMonitoring> ticketMonitorings = new HashSet<>();

    @OneToMany(targetEntity = HTicketView.class, mappedBy = "user", cascade = CascadeType.MERGE)
    private Set<HTicketView> ticketViews = new HashSet<>();

    @OneToMany(targetEntity = HTicket.class, mappedBy = "owner", cascade = CascadeType.MERGE)
    private Set<HTicket> ownedTickets = new HashSet<>();

    @OneToMany(targetEntity = HTicket.class, mappedBy = "creator", cascade = CascadeType.MERGE)
    private Set<HTicket> createdTickets = new HashSet<>();

    @OneToMany(targetEntity = HTicket.class, mappedBy = "manager", cascade = CascadeType.MERGE)
    private Set<HTicket> managedTickets = new HashSet<>();

    /**
     *
     */
    public HUser() {
        this.admin = false;
        this.controlPanelUserInterface = true;
        this.controlPanelCategoryMemberFilter = false;
        this.receiveTicketReports = true;
        this.receiveFaqReports = true;
        this.receiveTicketReportsAllInOne = true;
        this.receiveManagerMonitoring = true;
        this.controlPanelUserStatusFilter = STATUS_FILTER_ANY.value();
        this.controlPanelManagerStatusFilter = STATUS_FILTER_ANY.value();
        this.controlPanelUserInvolvementFilter = USER_INVOLVEMENT_FILTER_ANY.value();
        this.controlPanelManagerInvolvementFilter = MANAGER_INVOLVEMENT_FILTER_ANY.value();
        this.searchTypeFilter = TYPE_FILTER_ALL.value();
        this.bookmarkMonitoring = true;
        this.expirationMonitoring = true;
    }

    /**
     * @return
     */
    public static HUser user() {
        return new HUser();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public Boolean getAdvancedSearch() {
        return advancedSearch;
    }

    public void setAdvancedSearch(Boolean advancedSearch) {
        this.advancedSearch = advancedSearch;
    }

    public Date getAuthLimit() {
        return authLimit;
    }

    public void setAuthLimit(Date authLimit) {
        this.authLimit = authLimit;
    }

    public String getAuthSecret() {
        return authSecret;
    }

    public void setAuthSecret(String authSecret) {
        this.authSecret = authSecret;
    }

    public String getAuthType() {
        return authType;
    }

    public void setAuthType(String authType) {
        this.authType = authType;
    }

    public Boolean getBookmarkMonitoring() {
        return bookmarkMonitoring;
    }

    public void setBookmarkMonitoring(Boolean bookmarkMonitoring) {
        this.bookmarkMonitoring = bookmarkMonitoring;
    }

    public String getControlPanelUserInvolvementFilter() {
        return controlPanelUserInvolvementFilter;
    }

    public void setControlPanelUserInvolvementFilter(String controlPanelUserInvolvementFilter) {
        this.controlPanelUserInvolvementFilter = controlPanelUserInvolvementFilter;
    }

    public String getControlPanelManagerInvolvementFilter() {
        return controlPanelManagerInvolvementFilter;
    }

    public void setControlPanelManagerInvolvementFilter(String controlPanelManagerInvolvementFilter) {
        this.controlPanelManagerInvolvementFilter = controlPanelManagerInvolvementFilter;
    }

    public String getControlPanelManagerStatusFilter() {
        return controlPanelManagerStatusFilter;
    }

    public void setControlPanelManagerStatusFilter(String controlPanelManagerStatusFilter) {
        this.controlPanelManagerStatusFilter = controlPanelManagerStatusFilter;
    }

    public String getStoredControlPanelOrder() {
        return storedControlPanelOrder;
    }

    public void setStoredControlPanelOrder(String storedControlPanelOrder) {
        this.storedControlPanelOrder = storedControlPanelOrder;
    }

    public Integer getControlPanelPageSize() {
        return controlPanelPageSize;
    }

    public void setControlPanelPageSize(Integer controlPanelPageSize) {
        this.controlPanelPageSize = controlPanelPageSize;
    }

    public Integer getControlPanelRefreshDelay() {
        return controlPanelRefreshDelay;
    }

    public void setControlPanelRefreshDelay(Integer controlPanelRefreshDelay) {
        this.controlPanelRefreshDelay = controlPanelRefreshDelay;
    }

    public String getControlPanelUserStatusFilter() {
        return controlPanelUserStatusFilter;
    }

    public void setControlPanelUserStatusFilter(String controlPanelUserStatusFilter) {
        this.controlPanelUserStatusFilter = controlPanelUserStatusFilter;
    }

    public Boolean getControlPanelUserInterface() {
        return controlPanelUserInterface;
    }

    public void setControlPanelUserInterface(Boolean controlPanelUserInterface) {
        this.controlPanelUserInterface = controlPanelUserInterface;
    }

    public Boolean getControlPanelCategoryMemberFilter() {
        return controlPanelCategoryMemberFilter;
    }

    public void setControlPanelCategoryMemberFilter(Boolean controlPanelCategoryMemberFilter) {
        this.controlPanelCategoryMemberFilter = controlPanelCategoryMemberFilter;
    }

    public String getControlPanelColumns() {
        return controlPanelColumns;
    }

    public void setControlPanelColumns(String controlPanelColumns) {
        this.controlPanelColumns = controlPanelColumns;
    }

    public Date getDepartmentSelectionContextTime() {
        return departmentSelectionContextTime;
    }

    public void setDepartmentSelectionContextTime(Date departmentSelectionContextTime) {
        this.departmentSelectionContextTime = departmentSelectionContextTime;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEncodedAttributes() {
        return encodedAttributes;
    }

    public void setEncodedAttributes(String encodedAttributes) {
        this.encodedAttributes = encodedAttributes;
    }

    public Boolean getExpirationMonitoring() {
        return expirationMonitoring;
    }

    public void setExpirationMonitoring(Boolean expirationMonitoring) {
        this.expirationMonitoring = expirationMonitoring;
    }

    public HCategory getControlPanelCategoryFilter() {
        return controlPanelCategoryFilter;
    }

    public void setControlPanelCategoryFilter(HCategory controlPanelCategoryFilter) {
        this.controlPanelCategoryFilter = controlPanelCategoryFilter;
    }

    public HDepartment getJournalDepartmentFilter() {
        return journalDepartmentFilter;
    }

    public void setJournalDepartmentFilter(HDepartment journalDepartmentFilter) {
        this.journalDepartmentFilter = journalDepartmentFilter;
    }

    public HDepartment getSearchDepartmentFilter() {
        return searchDepartmentFilter;
    }

    public void setSearchDepartmentFilter(HDepartment searchDepartmentFilter) {
        this.searchDepartmentFilter = searchDepartmentFilter;
    }

    public HDepartment getControlPanelManagerDepartmentFilter() {
        return controlPanelManagerDepartmentFilter;
    }

    public void setControlPanelManagerDepartmentFilter(HDepartment controlPanelManagerDepartmentFilter) {
        this.controlPanelManagerDepartmentFilter = controlPanelManagerDepartmentFilter;
    }

    public HDepartment getControlPanelUserDepartmentFilter() {
        return controlPanelUserDepartmentFilter;
    }

    public void setControlPanelUserDepartmentFilter(HDepartment controlPanelUserDepartmentFilter) {
        this.controlPanelUserDepartmentFilter = controlPanelUserDepartmentFilter;
    }

    public Boolean getInvitedMonitoring() {
        return invitedMonitoring;
    }

    public void setInvitedMonitoring(Boolean invitedMonitoring) {
        this.invitedMonitoring = invitedMonitoring;
    }

    public Integer getJournalPageSize() {
        return journalPageSize;
    }

    public void setJournalPageSize(Integer journalPageSize) {
        this.journalPageSize = journalPageSize;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Boolean getOwnerMonitoring() {
        return ownerMonitoring;
    }

    public void setOwnerMonitoring(Boolean ownerMonitoring) {
        this.ownerMonitoring = ownerMonitoring;
    }

    public String getPageTransition() {
        return pageTransition;
    }

    public void setPageTransition(String pageTransition) {
        this.pageTransition = pageTransition;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRealId() {
        return realId;
    }

    public void setRealId(String realId) {
        this.realId = realId;
    }

    public Boolean getReceiveFaqReports() {
        return receiveFaqReports;
    }

    public void setReceiveFaqReports(Boolean receiveFaqReports) {
        this.receiveFaqReports = receiveFaqReports;
    }

    public Boolean getReceiveManagerMonitoring() {
        return receiveManagerMonitoring;
    }

    public void setReceiveManagerMonitoring(Boolean receiveManagerMonitoring) {
        this.receiveManagerMonitoring = receiveManagerMonitoring;
    }

    public Boolean getReceiveTicketReports() {
        return receiveTicketReports;
    }

    public void setReceiveTicketReports(Boolean receiveTicketReports) {
        this.receiveTicketReports = receiveTicketReports;
    }

    public Boolean getReceiveTicketReportsAllInOne() {
        return receiveTicketReportsAllInOne;
    }

    public void setReceiveTicketReportsAllInOne(Boolean receiveTicketReportsAllInOne) {
        this.receiveTicketReportsAllInOne = receiveTicketReportsAllInOne;
    }

    public Date getSearchDate1() {
        return searchDate1;
    }

    public void setSearchDate1(Date searchDate1) {
        this.searchDate1 = searchDate1;
    }

    public Date getSearchDate2() {
        return searchDate2;
    }

    public void setSearchDate2(Date searchDate2) {
        this.searchDate2 = searchDate2;
    }

    public Boolean getSearchSortByDate() {
        return searchSortByDate;
    }

    public void setSearchSortByDate(Boolean searchSortByDate) {
        this.searchSortByDate = searchSortByDate;
    }

    public String getSearchTypeFilter() {
        return searchTypeFilter;
    }

    public void setSearchTypeFilter(String searchTypeFilter) {
        this.searchTypeFilter = searchTypeFilter;
    }

    public Boolean getShowAddTicketHelp() {
        return showAddTicketHelp;
    }

    public void setShowAddTicketHelp(Boolean showAddTicketHelp) {
        this.showAddTicketHelp = showAddTicketHelp;
    }

    public Boolean getShowPopupOnClosure() {
        return showPopupOnClosure;
    }

    public void setShowPopupOnClosure(Boolean showPopupOnClosure) {
        this.showPopupOnClosure = showPopupOnClosure;
    }

    public Boolean getShowTicketAfterClosure() {
        return showTicketAfterClosure;
    }

    public void setShowTicketAfterClosure(Boolean showTicketAfterClosure) {
        this.showTicketAfterClosure = showTicketAfterClosure;
    }

    public String getStartPage() {
        return startPage;
    }

    public void setStartPage(String startPage) {
        this.startPage = startPage;
    }

    public Set<HAction> getActions() {
        return actions;
    }

    public void setActions(Set<HAction> actions) {
        this.actions = actions;
    }

    public Set<HAction> getActionsOwnerBefore() {
        return actionsOwnerBefore;
    }

    public void setActionsOwnerBefore(Set<HAction> actionsOwnerBefore) {
        this.actionsOwnerBefore = actionsOwnerBefore;
    }

    public Set<HAction> getActionsOwnerAfter() {
        return actionsOwnerAfter;
    }

    public void setActionsOwnerAfter(Set<HAction> actionsOwnerAfter) {
        this.actionsOwnerAfter = actionsOwnerAfter;
    }

    public Set<HAction> getActionsManagerBefore() {
        return actionsManagerBefore;
    }

    public void setActionsManagerBefore(Set<HAction> actionsManagerBefore) {
        this.actionsManagerBefore = actionsManagerBefore;
    }

    public Set<HAction> getActionsManagerAfter() {
        return actionsManagerAfter;
    }

    public void setActionsManagerAfter(Set<HAction> actionsManagerAfter) {
        this.actionsManagerAfter = actionsManagerAfter;
    }

    public Set<HAction> getActionsInvited() {
        return actionsInvited;
    }

    public void setActionsInvited(Set<HAction> actionsInvited) {
        this.actionsInvited = actionsInvited;
    }

    public Set<HAlert> getAlerts() {
        return alerts;
    }

    public void setAlerts(Set<HAlert> alerts) {
        this.alerts = alerts;
    }

    public Set<HArchivedAction> getArchivedActions() {
        return archivedActions;
    }

    public void setArchivedActions(Set<HArchivedAction> archivedActions) {
        this.archivedActions = archivedActions;
    }

    public Set<HArchivedAction> getArchivedActionsOwnerBefore() {
        return archivedActionsOwnerBefore;
    }

    public void setArchivedActionsOwnerBefore(Set<HArchivedAction> archivedActionsOwnerBefore) {
        this.archivedActionsOwnerBefore = archivedActionsOwnerBefore;
    }

    public Set<HArchivedAction> getArchivedActionsOwnerAfter() {
        return archivedActionsOwnerAfter;
    }

    public void setArchivedActionsOwnerAfter(Set<HArchivedAction> archivedActionsOwnerAfter) {
        this.archivedActionsOwnerAfter = archivedActionsOwnerAfter;
    }

    public Set<HArchivedAction> getArchivedActionsManagerBefore() {
        return archivedActionsManagerBefore;
    }

    public void setArchivedActionsManagerBefore(Set<HArchivedAction> archivedActionsManagerBefore) {
        this.archivedActionsManagerBefore = archivedActionsManagerBefore;
    }

    public Set<HArchivedAction> getArchivedActionsManagerAfter() {
        return archivedActionsManagerAfter;
    }

    public void setArchivedActionsManagerAfter(Set<HArchivedAction> archivedActionsManagerAfter) {
        this.archivedActionsManagerAfter = archivedActionsManagerAfter;
    }

    public Set<HArchivedAction> getArchivedActionsInvited() {
        return archivedActionsInvited;
    }

    public void setArchivedActionsInvited(Set<HArchivedAction> archivedActionsInvited) {
        this.archivedActionsInvited = archivedActionsInvited;
    }

    public Set<HArchivedFileInfo> getArchivedFileInfos() {
        return archivedFileInfos;
    }

    public void setArchivedFileInfos(Set<HArchivedFileInfo> archivedFileInfos) {
        this.archivedFileInfos = archivedFileInfos;
    }

    public Set<HArchivedInvitation> getArchivedInvitations() {
        return archivedInvitations;
    }

    public void setArchivedInvitations(Set<HArchivedInvitation> archivedInvitations) {
        this.archivedInvitations = archivedInvitations;
    }

    public Set<HArchivedTicket> getArchivedOwnedTickets() {
        return archivedOwnedTickets;
    }

    public void setArchivedOwnedTickets(Set<HArchivedTicket> archivedOwnedTickets) {
        this.archivedOwnedTickets = archivedOwnedTickets;
    }

    public Set<HArchivedTicket> getArchivedManagedTickets() {
        return archivedManagedTickets;
    }

    public void setArchivedManagedTickets(Set<HArchivedTicket> archivedManagedTickets) {
        this.archivedManagedTickets = archivedManagedTickets;
    }

    public Set<HArchivedTicket> getArchivedCreatedTickets() {
        return archivedCreatedTickets;
    }

    public void setArchivedCreatedTickets(Set<HArchivedTicket> archivedCreatedTickets) {
        this.archivedCreatedTickets = archivedCreatedTickets;
    }

    public Set<HBookmark> getBookmarks() {
        return bookmarks;
    }

    public void setBookmarks(Set<HBookmark> bookmarks) {
        this.bookmarks = bookmarks;
    }

    public Set<HCategoryMember> getCategoryMembers() {
        return categoryMembers;
    }

    public void setCategoryMembers(Set<HCategoryMember> categoryMembers) {
        this.categoryMembers = categoryMembers;
    }

    public Set<HDepartmentInvitation> getDepartmentInvitations() {
        return departmentInvitations;
    }

    public void setDepartmentInvitations(Set<HDepartmentInvitation> departmentInvitations) {
        this.departmentInvitations = departmentInvitations;
    }

    public Set<HDepartmentManager> getDepartmentManagers() {
        return departmentManagers;
    }

    public void setDepartmentManagers(Set<HDepartmentManager> departmentManagers) {
        this.departmentManagers = departmentManagers;
    }

    public Set<HDepartmentSelectionConfig> getDepartmentSelectionConfigs() {
        return departmentSelectionConfigs;
    }

    public void setDepartmentSelectionConfigs(Set<HDepartmentSelectionConfig> departmentSelectionConfigs) {
        this.departmentSelectionConfigs = departmentSelectionConfigs;
    }

    public Set<HFaqEvent> getFaqEvents() {
        return faqEvents;
    }

    public void setFaqEvents(Set<HFaqEvent> faqEvents) {
        this.faqEvents = faqEvents;
    }

    public Set<HFileInfo> getFileInfos() {
        return fileInfos;
    }

    public void setFileInfos(Set<HFileInfo> fileInfos) {
        this.fileInfos = fileInfos;
    }

    public Set<HHistoryItem> getHistoryItems() {
        return historyItems;
    }

    public void setHistoryItems(Set<HHistoryItem> historyItems) {
        this.historyItems = historyItems;
    }

    public Set<HInvitation> getInvitations() {
        return invitations;
    }

    public void setInvitations(Set<HInvitation> invitations) {
        this.invitations = invitations;
    }

    public Set<HResponse> getResponses() {
        return responses;
    }

    public void setResponses(Set<HResponse> responses) {
        this.responses = responses;
    }

    public Set<HTicketMonitoring> getTicketMonitorings() {
        return ticketMonitorings;
    }

    public void setTicketMonitorings(Set<HTicketMonitoring> ticketMonitorings) {
        this.ticketMonitorings = ticketMonitorings;
    }

    public Set<HTicketView> getTicketViews() {
        return ticketViews;
    }

    public void setTicketViews(Set<HTicketView> ticketViews) {
        this.ticketViews = ticketViews;
    }

    public Set<HTicket> getOwnedTickets() {
        return ownedTickets;
    }

    public void setOwnedTickets(Set<HTicket> ownedTickets) {
        this.ownedTickets = ownedTickets;
    }

    public Set<HTicket> getCreatedTickets() {
        return createdTickets;
    }

    public void setCreatedTickets(Set<HTicket> createdTickets) {
        this.createdTickets = createdTickets;
    }

    public Set<HTicket> getManagedTickets() {
        return managedTickets;
    }

    public void setManagedTickets(Set<HTicket> managedTickets) {
        this.managedTickets = managedTickets;
    }
}