package org.esupportail.helpdesk.data.dao.entities;

import org.esupportail.helpdesk.data.dao.enums.Monitoring;
import org.esupportail.helpdesk.data.dao.enums.Priority;
import org.esupportail.helpdesk.data.dao.enums.TicketScope;

import java.util.*;
import javax.persistence.*;

import static fj.data.Option.fromNull;

/**
 * The class that represents categories.
 */
@Entity
@Table(name = "h_cate")
public class HCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * True if users can put tickets in the category.
     */
    @Basic
    @Column(name = "add_new_tick")
    private Boolean addNewTickets;

    /**
     * The current state of the assignment algorithm.
     */
    @Basic
    @Column(name = "algo_stat")
    private String assignmentAlgorithmState;

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
    @Column(name = "auto_expi", columnDefinition = "INT")
    private Integer autoExpire;

    /**
     * v2 not null stuff.
     */
    @Basic
    @Column(name = "defa_prio_leve", columnDefinition = "INT")
    @SuppressWarnings("unused")
    private Integer oldDefaultPriorityLevel;

    /**
     * The default scope for the tickets of the container (inherit when null).
     */
    @Basic
    @Column(name = "defa_tick_scop")
    private String defaultTicketScope;

    /**
     * The effective scope for the new tickets.
     */
    @Basic
    @Column(name = "effe_defa_tick_scop")
    private String effectiveDefaultTicketScope;

    /**
     * The real category.
     */
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "real_cate_id")
    private HCategory realCategory;

    /**
     * The parent category.
     */
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "pare_id")
    private HCategory parent;

    /**
     * The department of the category.
     */
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "depa_id")
    private HDepartment department;

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
     * True if the FAQ links of the category are inherited (from its parent).
     */
    @Basic
    @Column(name = "inhe_faq_link")
    private Boolean inheritFaqLinks;

    /**
     * True if the members of the category are inherited (from its parent).
     */
    @Basic
    @Column(name = "inhe_memb")
    private Boolean inheritMembers;

    /**
     * True to inherit email monitoring properties.
     */
    @Basic
    @Column(name = "inhe_moni")
    private Boolean inheritMonitoring;

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
     * the URL of the department.
     */
    @Basic
    @Column(name = "url")
    private String url;

    /**
     * The long label.
     */
    @Basic
    @Column(name = "xlab")
    private String xlabel;

    @OneToMany(targetEntity = HAction.class, mappedBy = "categoryAfter", cascade = CascadeType.MERGE)
    private Set<HAction> actionsAfter = new HashSet<>();

    @OneToMany(targetEntity = HAction.class, mappedBy = "categoryBefore", cascade = CascadeType.MERGE)
    private Set<HAction> actionsBefore = new HashSet<>();

    @OneToMany(targetEntity = HCategoryMember.class, mappedBy = "category", cascade = CascadeType.MERGE)
    private Set<HCategoryMember> categoryMembers = new HashSet<>();

    @OneToMany(targetEntity = HCategory.class, mappedBy = "realCategory", cascade = CascadeType.MERGE)
    private Set<HCategory> categories = new HashSet<>();

    @OneToMany(targetEntity = HCategory.class, mappedBy = "parent", cascade = CascadeType.MERGE)
    private Set<HCategory> parentCategories = new HashSet<>();

    @OneToMany(targetEntity = HFaqLink.class, mappedBy = "category", cascade = CascadeType.MERGE)
    private Set<HFaqLink> faqLinks = new HashSet<>();

    @OneToMany(targetEntity = HOldTicketTemplate.class, mappedBy = "category", cascade = CascadeType.MERGE)
    private Set<HOldTicketTemplate> oldTicketTemplates = new HashSet<>();

    @OneToMany(targetEntity = HTicket.class, mappedBy = "category", cascade = CascadeType.MERGE)
    private Set<HTicket> tickets = new HashSet<>();

    @OneToMany(targetEntity = HUser.class, mappedBy = "controlPanelCategoryFilter", cascade = CascadeType.MERGE)
    private Set<HUser> users = new HashSet<>();


    public HCategory() {
        this.defaultTicketScope = TicketScope.DEFAULT.value();
        this.defaultTicketPriority = Priority.DEFAULT.value();
        this.hideToExternalUsers = Boolean.FALSE;
        this.inheritMonitoring = Boolean.TRUE;
        this.monitoringEmail = null;
        this.monitoringLocalEmails = Boolean.TRUE;
        this.monitoringEmailAuthType = null;
        this.monitoringLevel = Monitoring.MONITORING_NEVER.value();
        this.icon = null;
        this.oldDefaultPriorityLevel = Priority.DEFAULT.value();
        this.inheritMembers = Boolean.TRUE;
        this.inheritFaqLinks = Boolean.TRUE;
    }

    private HCategory(HCategory category) {
        this.id = category.id;
        this.label = category.label;
        this.xlabel = category.xlabel;
        this.autoExpire = category.autoExpire;
        this.defaultTicketScope = category.defaultTicketScope;
        this.effectiveDefaultTicketScope = category.effectiveDefaultTicketScope;
        this.defaultTicketLabel = category.defaultTicketLabel;
        this.defaultTicketMessage = category.defaultTicketMessage;
        this.defaultTicketPriority = category.defaultTicketPriority;
        this.assignmentAlgorithmName = category.assignmentAlgorithmName;
        this.order = category.order;
        this.url = category.url;
        this.hideToExternalUsers = category.hideToExternalUsers;
        this.inheritMonitoring = category.inheritMonitoring;
        this.monitoringEmail = category.monitoringEmail;
        this.monitoringLevel = category.monitoringLevel;
        this.monitoringLocalEmails = category.monitoringLocalEmails;
        this.monitoringEmailAuthType = category.monitoringEmailAuthType;
        this.icon = category.icon;
        this.oldDefaultPriorityLevel = Priority.DEFAULT.value();
        this.department = category.department;
        this.parent = category.parent;
        this.realCategory = category.realCategory;
        this.inheritMembers = category.inheritMembers;
        this.inheritFaqLinks = category.inheritFaqLinks;
        this.assignmentAlgorithmState = category.assignmentAlgorithmState;
        this.addNewTickets = category.addNewTickets;
    }

    public static HCategory category() {
        return new HCategory();
    }

    /**
     * Copies the initial category
     *
     * @param category
     * @return a copy of the initial category
     */
    public static HCategory category(final HCategory category) {
        return new HCategory(category);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getAddNewTickets() {
        return fromNull(addNewTickets).orSome(Boolean.TRUE);
    }

    public void setAddNewTickets(Boolean addNewTickets) {
        this.addNewTickets = addNewTickets;
    }

    public String getAssignmentAlgorithmState() {
        return assignmentAlgorithmState;
    }

    public void setAssignmentAlgorithmState(String assignmentAlgorithmState) {
        this.assignmentAlgorithmState = assignmentAlgorithmState;
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

    public Integer getOldDefaultPriorityLevel() {
        return Priority.DEFAULT.value();
    }

    public void setOldDefaultPriorityLevel(Integer oldDefaultPriorityLevel) {
        this.oldDefaultPriorityLevel = Priority.DEFAULT.value();
    }

    public String getDefaultTicketScope() {
        return defaultTicketScope;
    }

    public void setDefaultTicketScope(String defaultTicketScope) {
        this.defaultTicketScope = defaultTicketScope;
    }

    public String getEffectiveDefaultTicketScope() {
        return effectiveDefaultTicketScope;
    }

    public void setEffectiveDefaultTicketScope(String effectiveDefaultTicketScope) {
        this.effectiveDefaultTicketScope = effectiveDefaultTicketScope;
    }

    public HCategory getRealCategory() {
        return realCategory;
    }

    public void setRealCategory(HCategory realCategory) {
        this.realCategory = realCategory;
    }

    public HCategory getParent() {
        return parent;
    }

    public void setParent(HCategory parent) {
        this.parent = parent;
    }

    public HDepartment getDepartment() {
        return department;
    }

    public void setDepartment(HDepartment department) {
        this.department = department;
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

    public Boolean getInheritFaqLinks() {
        return fromNull(inheritFaqLinks).orSome(Boolean.TRUE);
    }

    public void setInheritFaqLinks(Boolean inheritFaqLinks) {
        this.inheritFaqLinks = inheritFaqLinks;
    }

    public Boolean getInheritMembers() {
        return inheritMembers;
    }

    public void setInheritMembers(Boolean inheritMembers) {
        this.inheritMembers = inheritMembers;
    }

    public Boolean getInheritMonitoring() {
        return inheritMonitoring;
    }

    public void setInheritMonitoring(Boolean inheritMonitoring) {
        this.inheritMonitoring = inheritMonitoring;
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

    public Set<HCategoryMember> getCategoryMembers() {
        return categoryMembers;
    }

    public void setCategoryMembers(Set<HCategoryMember> categoryMembers) {
        this.categoryMembers = categoryMembers;
    }

    public Set<HCategory> getCategories() {
        return categories;
    }

    public void setCategories(Set<HCategory> categories) {
        this.categories = categories;
    }

    public Set<HCategory> getParentCategories() {
        return parentCategories;
    }

    public void setParentCategories(Set<HCategory> parentCategories) {
        this.parentCategories = parentCategories;
    }

    public Set<HFaqLink> getFaqLinks() {
        return faqLinks;
    }

    public void setFaqLinks(Set<HFaqLink> faqLinks) {
        this.faqLinks = faqLinks;
    }

    public Set<HOldTicketTemplate> getOldTicketTemplates() {
        return oldTicketTemplates;
    }

    public void setOldTicketTemplates(Set<HOldTicketTemplate> oldTicketTemplates) {
        this.oldTicketTemplates = oldTicketTemplates;
    }

    public Set<HTicket> getTickets() {
        return tickets;
    }

    public void setTickets(Set<HTicket> tickets) {
        this.tickets = tickets;
    }

    public Set<HUser> getUsers() {
        return users;
    }

    public void setUsers(Set<HUser> users) {
        this.users = users;
    }
}