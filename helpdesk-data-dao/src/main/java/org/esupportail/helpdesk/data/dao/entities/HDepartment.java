package org.esupportail.helpdesk.data.dao.entities;

import org.esupportail.helpdesk.data.dao.enums.Monitoring;
import org.esupportail.helpdesk.data.dao.enums.Priority;
import org.esupportail.helpdesk.data.dao.enums.TicketScope;

import java.util.*;
import javax.persistence.*;

/**
 * The class that represents departments.
 */
@Entity
@Table(name = "h_depa")
public class HDepartment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * The name of the default assignment algorithm.
     */
    @Basic
    @Column(name = "assi_algo_name")
    private String assignmentAlgorithmName;

    /**
     * The number of days before a ticket is automatically approved when closed
     * (no approbation when 0, inherit when null).
     */
    @Basic
    @Column(name = "auto_expi2", columnDefinition = "INT")
    private Integer autoExpire;

    /**
     * The computer url builder name.
     */
    @Basic
    @Column(name = "comp_url_buil_name")
    private String computerUrlBuilderName;

    /**
     * The default FAQ scope.
     */
    @Basic
    @Column(name = "defa_faq_scop")
    private String defaultFaqScope;

    /**
     * the default scope for the tickets of the container (inherit when null).
     */
    @Basic
    @Column(name = "defa_tick_scop")
    private String defaultTicketScope;

    /**
     * The effective default FAQ scope.
     */
    @Basic
    @Column(name = "effe_defa_faq_scop")
    private String effectiveDefaultFaqScope;

    /**
     * The effective scope for the new tickets.
     */
    @Basic
    @Column(name = "effe_defa_tick_scop")
    private String effectiveDefaultTicketScope;

    /**
     * Free field for filtering the departments seen by the users.
     */
    @Basic
    @Column(name = "filt")
    private String filter;

    /**
     * The real department.
     */
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "real_depa_id")
    private HDepartment realDepartment;

    /**
     * The icon.
     */
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "icon_id")
    private HIcon icon;

    /**
     * True to hide to application users.
     */
    @Basic
    @Column(name = "hide_to_exte_user")
    private Boolean hideToExternalUsers;

    /**
     * The label.
     */
    @Basic
    @Column(name = "labe", nullable = false)
    private String label;

    /**
     * The email address to send an email.
     */
    @Basic
    @Column(name = "moni_emai")
    private String monitoringEmail;

    /**
     * The auth type for sent emails.
     */
    @Basic
    @Column(name = "moni_emai_auth_type")
    private String monitoringEmailAuthType;

    /**
     * The monitoring level.
     */
    @Basic
    @Column(name = "moni_leve", columnDefinition = "INT")
    private Integer monitoringLevel;

    /**
     * True to send email to local users.
     */
    @Basic
    @Column(name = "moni_loca_emai")
    private Boolean monitoringLocalEmails;

    /**
     * The order of this container.
     */
    @Basic
    @Column(name = "orde", columnDefinition = "INT")
    private Integer order;

    /**
     * True if the managers must fill the time spent when closing a ticket.
     */
    @Basic
    @Column(name = "spen_time_need")
    private boolean spentTimeNeeded;

    /**
     * The default label for the tickets of the container (inherit when null).
     */
    @Basic
    @Column(name = "tick_temp_labe")
    private String defaultTicketLabel;

    /**
     * The default message for the tickets of the container (inherit when null).
     */
    @Basic
    @Column(name = "tick_temp_mess")
    private String defaultTicketMessage;

    /**
     * The default priority for the tickets of the container (inherit when null).
     */
    @Basic
    @Column(name = "tick_temp_prio_leve", columnDefinition = "INT")
    private int defaultTicketPriority;

    /**
     * The URL of the department.
     */
    @Basic
    @Column(name = "url")
    private String url;

    /**
     * True if the department is enabled.
     */
    @Basic
    @Column(name = "visi")
    private boolean enabled;

    /**
     * The long label.
     */
    @Basic
    @Column(name = "xlab")
    private String xlabel;

    @OneToMany(targetEntity = HAction.class, mappedBy = "departmentAfter", cascade = CascadeType.MERGE)
    private Set<HAction> actionsAfter = new HashSet<>();

    @OneToMany(targetEntity = HAction.class, mappedBy = "departmentBefore", cascade = CascadeType.MERGE)
    private Set<HAction> actionsBefore = new HashSet<>();

    @OneToMany(targetEntity = HArchivedAction.class, mappedBy = "departmentAfter", cascade = CascadeType.MERGE)
    private Set<HArchivedAction> archivedActionsAfter = new HashSet<>();

    @OneToMany(targetEntity = HArchivedAction.class, mappedBy = "departmentBefore", cascade = CascadeType.MERGE)
    private Set<HArchivedAction> archivedActionsBefore = new HashSet<>();

    @OneToMany(targetEntity = HArchivedTicket.class, mappedBy = "department", cascade = CascadeType.MERGE)
    private Set<HArchivedTicket> archivedTickets = new HashSet<>();

    @OneToMany(targetEntity = HArchivedTicket.class, mappedBy = "creationDepartment", cascade = CascadeType.MERGE)
    private Set<HArchivedTicket> archivedTicketsCreation = new HashSet<>();

    @OneToMany(targetEntity = HCategory.class, mappedBy = "department", cascade = CascadeType.MERGE)
    private Set<HCategory> categories = new HashSet<>();

    @OneToMany(targetEntity = HDepartmentInvitation.class, mappedBy = "department", cascade = CascadeType.MERGE)
    private Set<HDepartmentInvitation> departmentInvitations = new HashSet<>();

    @OneToMany(targetEntity = HDepartmentManager.class, mappedBy = "department", cascade = CascadeType.MERGE)
    private Set<HDepartmentManager> departmentManagers = new HashSet<>();

    @OneToMany(targetEntity = HDepartment.class, mappedBy = "realDepartment", cascade = CascadeType.MERGE)
    private Set<HDepartment> departments = new HashSet<>();

    @OneToMany(targetEntity = HFaq.class, mappedBy = "department", cascade = CascadeType.MERGE)
    private Set<HFaq> faqs = new HashSet<>();

    @OneToMany(targetEntity = HFaqEvent.class, mappedBy = "department", cascade = CascadeType.MERGE)
    private Set<HFaqEvent> faqEvents = new HashSet<>();

    @OneToMany(targetEntity = HFaqEvent.class, mappedBy = "fromDepartment", cascade = CascadeType.MERGE)
    private Set<HFaqEvent> faqEventsFrom = new HashSet<>();

    @OneToMany(targetEntity = HFaqEvent.class, mappedBy = "toDepartment", cascade = CascadeType.MERGE)
    private Set<HFaqEvent> faqEventsTo = new HashSet<>();

    @OneToMany(targetEntity = HFaqLink.class, mappedBy = "department", cascade = CascadeType.MERGE)
    private Set<HFaqLink> faqLinks = new HashSet<>();

    @OneToMany(targetEntity = HDeprecatedFaqContainer.class, mappedBy = "department", cascade = CascadeType.MERGE)
    private Set<HDeprecatedFaqContainer> deprecatedFaqContainers = new HashSet<>();

    @OneToMany(targetEntity = HResponse.class, mappedBy = "department", cascade = CascadeType.MERGE)
    private Set<HResponse> responses = new HashSet<>();

    @OneToMany(targetEntity = HTicket.class, mappedBy = "department", cascade = CascadeType.MERGE)
    private Set<HTicket> tickets = new HashSet<>();

    @OneToMany(targetEntity = HTicket.class, mappedBy = "creationDepartment", cascade = CascadeType.MERGE)
    private Set<HTicket> createdTickets = new HashSet<>();

    @OneToMany(targetEntity = HUser.class, mappedBy = "journalDepartmentFilter", cascade = CascadeType.MERGE)
    private Set<HUser> journalDepartmentFilterUsers = new HashSet<>();

    @OneToMany(targetEntity = HUser.class, mappedBy = "searchDepartmentFilter", cascade = CascadeType.MERGE)
    private Set<HUser> searchDepartmentFilterUsers = new HashSet<>();

    @OneToMany(targetEntity = HUser.class, mappedBy = "controlPanelManagerDepartmentFilter", cascade = CascadeType.MERGE)
    private Set<HUser> controlPanelManagerDepartmentFilterUsers = new HashSet<>();

    @OneToMany(targetEntity = HUser.class, mappedBy = "controlPanelUserDepartmentFilter", cascade = CascadeType.MERGE)
    private Set<HUser> controlPanelUserDepartmentFilterUsers = new HashSet<>();

    /**
     *
     */
    public HDepartment() {
        this.defaultTicketScope = TicketScope.DEFAULT.value();
        this.defaultTicketPriority = Priority.DEFAULT.value();
        this.hideToExternalUsers = Boolean.FALSE;
        this.monitoringEmail = null;
        this.monitoringLocalEmails = Boolean.TRUE;
        this.monitoringEmailAuthType = null;
        this.monitoringLevel = Monitoring.MONITORING_NEVER.value();
        this.icon = null;
    }

    /**
     * @param label
     */
    private HDepartment(final String label) {
        this();
        this.label = label;
    }

    /**
     * Copy.
     *
     * @param department the department to copy
     */
    private HDepartment(final HDepartment department) {
        this.id = department.id;
        this.label = department.label;
        this.xlabel = department.xlabel;
        this.autoExpire = department.autoExpire;
        this.defaultTicketScope = department.defaultTicketScope;
        this.effectiveDefaultTicketScope = department.effectiveDefaultTicketScope;
        this.defaultTicketLabel = department.defaultTicketLabel;
        this.defaultTicketMessage = department.defaultTicketMessage;
        this.defaultTicketPriority = department.defaultTicketPriority;
        this.assignmentAlgorithmName = department.assignmentAlgorithmName;
        this.order = department.order;
        this.url = department.url;
        this.hideToExternalUsers = department.hideToExternalUsers;
        this.monitoringEmail = department.monitoringEmail;
        this.monitoringLevel = department.monitoringLevel;
        this.monitoringLocalEmails = department.monitoringLocalEmails;
        this.monitoringEmailAuthType = department.monitoringEmailAuthType;
        this.icon = department.icon;
        this.enabled = department.enabled;
        this.spentTimeNeeded = department.spentTimeNeeded;
        this.filter = department.filter;
        this.defaultFaqScope = department.defaultFaqScope;
        this.effectiveDefaultFaqScope = department.effectiveDefaultFaqScope;
        this.realDepartment = department.realDepartment;
        this.computerUrlBuilderName = department.computerUrlBuilderName;
    }

    /**
     * @param label
     * @return a new {@link HDepartment}
     */
    public static HDepartment department(final String label) {
        return new HDepartment(label);
    }

    /**
     * @param department
     * @return a copy of the original {@link HDepartment}
     */
    public static HDepartment department(final HDepartment department) {
        return new HDepartment(department);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAssignmentAlgorithmName() {
        return assignmentAlgorithmName;
    }

    public void setAssignmentAlgorithmName(String assignmentAlgorithmName) {
        this.assignmentAlgorithmName = assignmentAlgorithmName;
    }

    public Integer getAutoExpire() {
        return autoExpire;
    }

    public void setAutoExpire(Integer autoExpire) {
        this.autoExpire = autoExpire;
    }

    public String getComputerUrlBuilderName() {
        return computerUrlBuilderName;
    }

    public void setComputerUrlBuilderName(String computerUrlBuilderName) {
        this.computerUrlBuilderName = computerUrlBuilderName;
    }

    public String getDefaultFaqScope() {
        return defaultFaqScope;
    }

    public void setDefaultFaqScope(String defaultFaqScope) {
        this.defaultFaqScope = defaultFaqScope;
    }

    public String getDefaultTicketScope() {
        return defaultTicketScope;
    }

    public void setDefaultTicketScope(String defaultTicketScope) {
        this.defaultTicketScope = defaultTicketScope;
    }

    public String getEffectiveDefaultFaqScope() {
        return effectiveDefaultFaqScope;
    }

    public void setEffectiveDefaultFaqScope(String effectiveDefaultFaqScope) {
        this.effectiveDefaultFaqScope = effectiveDefaultFaqScope;
    }

    public String getEffectiveDefaultTicketScope() {
        return effectiveDefaultTicketScope;
    }

    public void setEffectiveDefaultTicketScope(String effectiveDefaultTicketScope) {
        this.effectiveDefaultTicketScope = effectiveDefaultTicketScope;
    }

    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }

    public HDepartment getRealDepartment() {
        return realDepartment;
    }

    public void setRealDepartment(HDepartment realDepartment) {
        this.realDepartment = realDepartment;
    }

    public HIcon getIcon() {
        return icon;
    }

    public void setIcon(HIcon icon) {
        this.icon = icon;
    }

    public Boolean getHideToExternalUsers() {
        return hideToExternalUsers;
    }

    public void setHideToExternalUsers(Boolean hideToExternalUsers) {
        this.hideToExternalUsers = hideToExternalUsers;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getMonitoringEmail() {
        return monitoringEmail;
    }

    public void setMonitoringEmail(String monitoringEmail) {
        this.monitoringEmail = monitoringEmail;
    }

    public String getMonitoringEmailAuthType() {
        return monitoringEmailAuthType;
    }

    public void setMonitoringEmailAuthType(String monitoringEmailAuthType) {
        this.monitoringEmailAuthType = monitoringEmailAuthType;
    }

    public Integer getMonitoringLevel() {
        return monitoringLevel;
    }

    public void setMonitoringLevel(Integer monitoringLevel) {
        this.monitoringLevel = monitoringLevel;
    }

    public Boolean getMonitoringLocalEmails() {
        return monitoringLocalEmails;
    }

    public void setMonitoringLocalEmails(Boolean monitoringLocalEmails) {
        this.monitoringLocalEmails = monitoringLocalEmails;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public boolean isSpentTimeNeeded() {
        return spentTimeNeeded;
    }

    public void setSpentTimeNeeded(boolean spentTimeNeeded) {
        this.spentTimeNeeded = spentTimeNeeded;
    }

    public String getDefaultTicketLabel() {
        return defaultTicketLabel;
    }

    public void setDefaultTicketLabel(String defaultTicketLabel) {
        this.defaultTicketLabel = defaultTicketLabel;
    }

    public String getDefaultTicketMessage() {
        return defaultTicketMessage;
    }

    public void setDefaultTicketMessage(String defaultTicketMessage) {
        this.defaultTicketMessage = defaultTicketMessage;
    }

    public int getDefaultTicketPriority() {
        return defaultTicketPriority;
    }

    public void setDefaultTicketPriority(int defaultTicketPriority) {
        this.defaultTicketPriority = defaultTicketPriority;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getXlabel() {
        return xlabel;
    }

    public void setXlabel(String xlabel) {
        this.xlabel = xlabel;
    }

    public Set<HAction> getActionsAfter() {
        return actionsAfter;
    }

    public void setActionsAfter(Set<HAction> actionsAfter) {
        this.actionsAfter = actionsAfter;
    }

    public Set<HAction> getActionsBefore() {
        return actionsBefore;
    }

    public void setActionsBefore(Set<HAction> actionsBefore) {
        this.actionsBefore = actionsBefore;
    }

    public Set<HArchivedAction> getArchivedActionsAfter() {
        return archivedActionsAfter;
    }

    public void setArchivedActionsAfter(Set<HArchivedAction> archivedActionsAfter) {
        this.archivedActionsAfter = archivedActionsAfter;
    }

    public Set<HArchivedAction> getArchivedActionsBefore() {
        return archivedActionsBefore;
    }

    public void setArchivedActionsBefore(Set<HArchivedAction> archivedActionsBefore) {
        this.archivedActionsBefore = archivedActionsBefore;
    }

    public Set<HArchivedTicket> getArchivedTickets() {
        return archivedTickets;
    }

    public void setArchivedTickets(Set<HArchivedTicket> archivedTickets) {
        this.archivedTickets = archivedTickets;
    }

    public Set<HArchivedTicket> getArchivedTicketsCreation() {
        return archivedTicketsCreation;
    }

    public void setArchivedTicketsCreation(Set<HArchivedTicket> archivedTicketsCreation) {
        this.archivedTicketsCreation = archivedTicketsCreation;
    }

    public Set<HCategory> getCategories() {
        return categories;
    }

    public void setCategories(Set<HCategory> categories) {
        this.categories = categories;
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

    public Set<HDepartment> getDepartments() {
        return departments;
    }

    public void setDepartments(Set<HDepartment> departments) {
        this.departments = departments;
    }

    public Set<HFaq> getFaqs() {
        return faqs;
    }

    public void setFaqs(Set<HFaq> faqs) {
        this.faqs = faqs;
    }

    public Set<HFaqEvent> getFaqEvents() {
        return faqEvents;
    }

    public void setFaqEvents(Set<HFaqEvent> faqEvents) {
        this.faqEvents = faqEvents;
    }

    public Set<HFaqEvent> getFaqEventsFrom() {
        return faqEventsFrom;
    }

    public void setFaqEventsFrom(Set<HFaqEvent> faqEventsFrom) {
        this.faqEventsFrom = faqEventsFrom;
    }

    public Set<HFaqEvent> getFaqEventsTo() {
        return faqEventsTo;
    }

    public void setFaqEventsTo(Set<HFaqEvent> faqEventsTo) {
        this.faqEventsTo = faqEventsTo;
    }

    public Set<HFaqLink> getFaqLinks() {
        return faqLinks;
    }

    public void setFaqLinks(Set<HFaqLink> faqLinks) {
        this.faqLinks = faqLinks;
    }

    public Set<HDeprecatedFaqContainer> getDeprecatedFaqContainers() {
        return deprecatedFaqContainers;
    }

    public void setDeprecatedFaqContainers(Set<HDeprecatedFaqContainer> deprecatedFaqContainers) {
        this.deprecatedFaqContainers = deprecatedFaqContainers;
    }

    public Set<HResponse> getResponses() {
        return responses;
    }

    public void setResponses(Set<HResponse> responses) {
        this.responses = responses;
    }

    public Set<HTicket> getTickets() {
        return tickets;
    }

    public void setTickets(Set<HTicket> tickets) {
        this.tickets = tickets;
    }

    public Set<HTicket> getCreatedTickets() {
        return createdTickets;
    }

    public void setCreatedTickets(Set<HTicket> createdTickets) {
        this.createdTickets = createdTickets;
    }

    public Set<HUser> getJournalDepartmentFilterUsers() {
        return journalDepartmentFilterUsers;
    }

    public void setJournalDepartmentFilterUsers(Set<HUser> journalDepartmentFilterUsers) {
        this.journalDepartmentFilterUsers = journalDepartmentFilterUsers;
    }

    public Set<HUser> getSearchDepartmentFilterUsers() {
        return searchDepartmentFilterUsers;
    }

    public void setSearchDepartmentFilterUsers(Set<HUser> searchDepartmentFilterUsers) {
        this.searchDepartmentFilterUsers = searchDepartmentFilterUsers;
    }

    public Set<HUser> getControlPanelManagerDepartmentFilterUsers() {
        return controlPanelManagerDepartmentFilterUsers;
    }

    public void setControlPanelManagerDepartmentFilterUsers(Set<HUser> controlPanelManagerDepartmentFilterUsers) {
        this.controlPanelManagerDepartmentFilterUsers = controlPanelManagerDepartmentFilterUsers;
    }

    public Set<HUser> getControlPanelUserDepartmentFilterUsers() {
        return controlPanelUserDepartmentFilterUsers;
    }

    public void setControlPanelUserDepartmentFilterUsers(Set<HUser> controlPanelUserDepartmentFilterUsers) {
        this.controlPanelUserDepartmentFilterUsers = controlPanelUserDepartmentFilterUsers;
    }
}