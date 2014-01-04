package org.esupportail.helpdesk.data.dao.entities;

import org.esupportail.helpdesk.data.dao.utils.Functions;

import java.util.*;
import javax.persistence.*;

import static org.esupportail.helpdesk.data.dao.enums.Priority.DEFAULT;
import static org.esupportail.helpdesk.data.dao.enums.TicketScope.UNDEF;

/**
 * The representation of a ticket action.
 */
@Entity
@Table(name = "h_acti")
public class HAction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * The type of the action.
     */
    @Basic
    @Column(name = "acti_type", nullable = false)
    private String actionType;

    /**
     * Label of ticket before action.
     */
    @Basic
    @Column(name = "comp_befo")
    private String computerBefore;

    /**
     * Label of ticket after action.
     */
    @Basic
    @Column(name = "comp_afte")
    private String computerAfter;

    /**
     * The date of the action.
     */
    @Basic
    @Column(name = "date", nullable = false)
    private Date date;

    /**
     * The name of the uploaded file.
     */
    @Basic
    @Column(name = "file_name")
    private String fileName;

    /**
     * HCategory before action.
     */
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "cate_befo_id")
    private HCategory categoryBefore;

    /**
     * HCategory after action.
     */
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "cate_afte_id")
    private HCategory categoryAfter;

    /**
     * HDepartment of ticket before action.
     */
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "depa_befo_id")
    private HDepartment departmentBefore;

    /**
     * HDepartment of ticket after action.
     */
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "depa_afte_id")
    private HDepartment departmentAfter;

    /**
     * old faqEntry connection.
     */
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "faq_entr_conn_afte_id")
    private HOldFaqEntry oldFaqEntryConnectionAfter;

    /**
     * old faqPart connection.
     */
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "faq_part_conn_afte_id")
    private HOldFaqPart oldFaqPartConnectionAfter;

    /**
     * A file bound to the action (optional).
     */
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "file_id")
    private HOldFileInfo oldFileInfo;

    /**
     * HTicket connection.
     */
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "conn_afte_id")
    private HTicket oldConnectionAfter;

    /**
     * The ticket.
     */
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "tick_id", nullable = false)
    private HTicket ticket;

    /**
     * The user that did the action.
     */
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_id")
    private HUser user;

    /**
     * Invited user action.
     */
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "invi_user_id")
    private HUser invitedUser;

    /**
     * ticketOwner of ticket before action.
     */
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "tick_owne_befo_id")
    private HUser ticketOwnerBefore;

    /**
     * ticketOwner of ticket after action.
     */
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "tick_owne_afte_id")
    private HUser ticketOwnerAfter;

    /**
     * Manager of ticket before action.
     */
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "mana_befo_id")
    private HUser managerBefore;

    /**
     * Manager of ticket after action.
     */
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "mana_afte_id")
    private HUser managerAfter;

    /**
     * Label of ticket before action.
     */
    @Basic
    @Column(name = "labe_befo")
    private String labelBefore;

    /**
     * Label of ticket after action.
     */
    @Basic
    @Column(name = "labe_afte")
    private String labelAfter;

    /**
     * The message of the action.
     */
    @Basic
    @Column(name = "mess")
    private String message;

    /**
     * Origin of ticket before action.
     */
    @Basic
    @Column(name = "orig_befo")
    private String originBefore;

    /**
     * Origin of ticket after action.
     */
    @Basic
    @Column(name = "orig_afte")
    private String originAfter;

    /**
     * Priority level of ticket before action.
     */
    @Basic
    @Column(name = "prio_leve_befo", columnDefinition = "INT")
    private Integer priorityLevelBefore;

    /**
     * Priority level of ticket after action.
     */
    @Basic
    @Column(name = "prio_leve_afte", columnDefinition = "INT")
    private int priorityLevelAfter;

    /**
     * The date when the ticket should be recalled.
     */
    @Basic
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "reca_date")
    private Date recallDate;

    /**
     * Scope of ticket before action.
     */
    @Basic
    @Column(name = "scop_befo")
    private String scopeBefore;

    /**
     * Scope of ticket after action.
     */
    @Basic
    @Column(name = "scop_afte")
    private String scopeAfter;

    /**
     * The scope of the action.
     */
    @Basic
    @Column(name = "scop", nullable = false)
    private String scope;

    /**
     * Spent time of ticket before action.
     */
    @Basic
    @Column(name = "spen_time_befo")
    private Long spentTimeBefore;

    /**
     * Spent time of ticket after action.
     */
    @Basic
    @Column(name = "spen_time_afte")
    private Long spentTimeAfter;

    /**
     * Status before the action.
     */
    @Basic
    @Column(name = "stat_befo", nullable = false)
    private String statusBefore;

    /**
     * Status after the action.
     */
    @Basic
    @Column(name = "stat_afte", nullable = false)
    private String statusAfter;

    @OneToMany(targetEntity = HAlert.class, mappedBy = "action", cascade = CascadeType.MERGE)
    private Set<HAlert> alerts = new HashSet<>();

    private HAction() {
        this.date = new Date();
    }

    private HAction(final HUser owner, final HTicket ticket, final String actionType, final String statusAfter, final String scope, final String message) {
        this();
        this.user = owner;
        this.ticket = ticket;
        this.actionType = actionType;
        this.scope = scope;
        this.message = message;
        this.priorityLevelBefore = DEFAULT.value();
        this.priorityLevelAfter = DEFAULT.value();
        this.scopeBefore = UNDEF.value();
        this.scopeAfter = UNDEF.value();
        this.spentTimeBefore = -1L;
        this.spentTimeAfter = -1L;
        this.statusBefore = ticket.getStatus();
        this.statusAfter = statusAfter;
    }

    public static HAction action(final HUser owner, final HTicket ticket, final String actionType, final String statusAfter, final String scope, final String message) {
        return new HAction(owner, ticket, actionType, statusAfter, scope, message);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getActionType() {
        return actionType;
    }

    public void setActionType(String actionType) {
        this.actionType = actionType;
    }

    public String getComputerBefore() {
        return computerBefore;
    }

    public void setComputerBefore(String computerBefore) {
        this.computerBefore = computerBefore;
    }

    public String getComputerAfter() {
        return computerAfter;
    }

    public void setComputerAfter(String computerAfter) {
        this.computerAfter = computerAfter;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public HCategory getCategoryBefore() {
        return categoryBefore;
    }

    public void setCategoryBefore(HCategory categoryBefore) {
        this.categoryBefore = categoryBefore;
    }

    public HCategory getCategoryAfter() {
        return categoryAfter;
    }

    public void setCategoryAfter(HCategory categoryAfter) {
        this.categoryAfter = categoryAfter;
    }

    public HDepartment getDepartmentBefore() {
        return departmentBefore;
    }

    public void setDepartmentBefore(HDepartment departmentBefore) {
        this.departmentBefore = departmentBefore;
    }

    public HDepartment getDepartmentAfter() {
        return departmentAfter;
    }

    public void setDepartmentAfter(HDepartment departmentAfter) {
        this.departmentAfter = departmentAfter;
    }

    public HOldFaqEntry getOldFaqEntryConnectionAfter() {
        return oldFaqEntryConnectionAfter;
    }

    public void setOldFaqEntryConnectionAfter(HOldFaqEntry oldFaqEntryConnectionAfter) {
        this.oldFaqEntryConnectionAfter = oldFaqEntryConnectionAfter;
    }

    public HOldFaqPart getOldFaqPartConnectionAfter() {
        return oldFaqPartConnectionAfter;
    }

    public void setOldFaqPartConnectionAfter(HOldFaqPart oldFaqPartConnectionAfter) {
        this.oldFaqPartConnectionAfter = oldFaqPartConnectionAfter;
    }

    public HOldFileInfo getOldFileInfo() {
        return oldFileInfo;
    }

    public void setOldFileInfo(HOldFileInfo oldFileInfo) {
        this.oldFileInfo = oldFileInfo;
    }

    public HTicket getOldConnectionAfter() {
        return oldConnectionAfter;
    }

    public void setOldConnectionAfter(HTicket oldConnectionAfter) {
        this.oldConnectionAfter = oldConnectionAfter;
    }

    public HTicket getTicket() {
        return ticket;
    }

    public void setTicket(HTicket ticket) {
        this.ticket = ticket;
    }

    public HUser getUser() {
        return user;
    }

    public void setUser(HUser user) {
        this.user = user;
    }

    public HUser getInvitedUser() {
        return invitedUser;
    }

    public void setInvitedUser(HUser invitedUser) {
        this.invitedUser = invitedUser;
    }

    public HUser getTicketOwnerBefore() {
        return ticketOwnerBefore;
    }

    public void setTicketOwnerBefore(HUser ticketOwnerBefore) {
        this.ticketOwnerBefore = ticketOwnerBefore;
    }

    public HUser getTicketOwnerAfter() {
        return ticketOwnerAfter;
    }

    public void setTicketOwnerAfter(HUser ticketOwnerAfter) {
        this.ticketOwnerAfter = ticketOwnerAfter;
    }

    public HUser getManagerBefore() {
        return managerBefore;
    }

    public void setManagerBefore(HUser managerBefore) {
        this.managerBefore = managerBefore;
    }

    public HUser getManagerAfter() {
        return managerAfter;
    }

    public void setManagerAfter(HUser managerAfter) {
        this.managerAfter = managerAfter;
    }

    public String getLabelBefore() {
        return labelBefore;
    }

    public void setLabelBefore(String labelBefore) {
        this.labelBefore = labelBefore;
    }

    public String getLabelAfter() {
        return labelAfter;
    }

    public void setLabelAfter(String labelAfter) {
        this.labelAfter = labelAfter;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getOriginBefore() {
        return originBefore;
    }

    public void setOriginBefore(String originBefore) {
        this.originBefore = originBefore;
    }

    public String getOriginAfter() {
        return originAfter;
    }

    public void setOriginAfter(String originAfter) {
        this.originAfter = originAfter;
    }

    public Integer getPriorityLevelBefore() {
        return priorityLevelBefore;
    }

    public void setPriorityLevelBefore(Integer priorityLevelBefore) {
        this.priorityLevelBefore = priorityLevelBefore;
    }

    public int getPriorityLevelAfter() {
        return priorityLevelAfter;
    }

    public void setPriorityLevelAfter(int priorityLevelAfter) {
        this.priorityLevelAfter = priorityLevelAfter;
    }

    public Date getRecallDate() {
        return recallDate;
    }

    public void setRecallDate(Date recallDate) {
        this.recallDate = recallDate;
    }

    public String getScopeBefore() {
        return scopeBefore;
    }

    public void setScopeBefore(String scopeBefore) {
        this.scopeBefore = scopeBefore;
    }

    public String getScopeAfter() {
        return scopeAfter;
    }

    public void setScopeAfter(String scopeAfter) {
        this.scopeAfter = scopeAfter;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public Long getSpentTimeBefore() {
        return spentTimeBefore;
    }

    public void setSpentTimeBefore(Long spentTimeBefore) {
        this.spentTimeBefore = spentTimeBefore;
    }

    public Long getSpentTimeAfter() {
        return spentTimeAfter;
    }

    public void setSpentTimeAfter(Long spentTimeAfter) {
        this.spentTimeAfter = spentTimeAfter;
    }

    public String getStatusAfter() {
        return statusAfter;
    }

    public void setStatusAfter(String statusAfter) {
        this.statusAfter = statusAfter;
    }

    public String getStatusBefore() {
        return statusBefore;
    }

    public void setStatusBefore(String statusBefore) {
        this.statusBefore = statusBefore;
    }

    public Set<HAlert> getAlerts() {
        return alerts;
    }

    public String getEffectiveScope() {
        return Functions.effectiveScope.f(scope, ticket.getEffectiveScope());
    }
}