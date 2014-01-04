package org.esupportail.helpdesk.data.dao.entities;

import java.util.*;
import javax.persistence.*;

import static fj.data.Option.fromString;

/**
 * The representation of a ticket action.
 */
@Entity
@Table(name = "h_arch_acti")
public class HArchivedAction {

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
     * HCategory before action.
     */
    @Basic
    @Column(name = "cate_befo_labe")
    private String categoryBeforeLabel;

    /**
     * HCategory after action.
     */
    @Basic
    @Column(name = "cate_afte_labe")
    private String categoryAfterLabel;

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
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date", nullable = false)
    private Date date;

    /**
     * The effective scope of the action.
     */
    @Basic
    @Column(name = "effe_scop", nullable = false)
    private String effectiveScope;

    /**
     * The name of the uploaded file.
     */
    @Basic
    @Column(name = "file_name")
    private String filename;

    /**
     * The archived ticket.
     */
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "arch_tick_id", nullable = false)
    private HArchivedTicket archivedTicket;

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
     * The user that did the action.
     */
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_id")
    private HUser user;

    /**
     * ticketOwner of ticket after action.
     */
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "tick_owne_afte_id")
    private HUser ticketOwnerAfter;

    /**
     * Invited user action.
     */
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "invi_user_id")
    private HUser invitedUser;

    /**
     * Manager of ticket after action.
     */
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "mana_afte_id")
    private HUser managerAfter;

    /**
     * ticketOwner of ticket before action.
     */
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "tick_owne_befo_id")
    private HUser ticketOwnerBefore;

    /**
     * Manager of ticket before action.
     */
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "mana_befo_id")
    private HUser managerBefore;

    /**
     * Label of ticket after action.
     */
    @Basic
    @Column(name = "labe_afte")
    private String labelAfter;

    /**
     * Label of ticket before action.
     */
    @Basic
    @Column(name = "labe_befo")
    private String labelBefore;

    /**
     * The message of the action.
     */
    @Basic
    @Column(name = "mess")
    private String message;

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
    private Integer priorityLevelAfter;

    /**
     * The date when the ticket should be recalled.
     */
    @Basic
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "reca_date")
    private Date recallDate;

    /**
     * Label of ticket before action.
     */
    @Basic
    @Column(name = "scop_befo")
    private String scopeBefore;

    /**
     * Label of ticket after action.
     */
    @Basic
    @Column(name = "scop_afte")
    private String scopeAfter;

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

    /**
     *
     */
    private HArchivedAction() {
        this.date = new Date();
    }

    /**
     * @param owner
     * @param archivedTicket
     * @param effectiveScope
     */
    private HArchivedAction(final HUser owner, final HArchivedTicket archivedTicket, final String effectiveScope) {
        this();
        this.user = owner;
        this.archivedTicket = archivedTicket;
        this.effectiveScope = effectiveScope;
    }

    /**
     * General constructor of action. (Set the needed not null attributes).
     *
     * @param action
     * @param archivedTicket
     */
    private HArchivedAction(final HAction action, final HArchivedTicket archivedTicket) {
        this(action.getUser(), archivedTicket, action.getEffectiveScope());
        setActionType(action.getActionType());
        setComputerAfter(action.getComputerAfter());
        setComputerBefore(action.getComputerBefore());
        if (action.getCategoryAfter() != null) {
            setCategoryAfterLabel(action.getCategoryAfter().getLabel());
        }
        if (action.getCategoryBefore() != null) {
            setCategoryBeforeLabel(action.getCategoryBefore().getLabel());
        }
        setDate(action.getDate());
        setDepartmentAfter(action.getDepartmentAfter());
        setDepartmentBefore(action.getDepartmentBefore());
        setInvitedUser(action.getInvitedUser());
        setLabelAfter(action.getLabelAfter());
        setLabelBefore(action.getLabelBefore());
        setManagerAfter(action.getManagerAfter());
        setManagerBefore(action.getManagerBefore());
        setMessage(action.getMessage());
        setPriorityLevelAfter(action.getPriorityLevelAfter());
        setPriorityLevelBefore(action.getPriorityLevelBefore());
        setScopeAfter(action.getScopeAfter());
        setScopeBefore(action.getScopeBefore());
        setSpentTimeAfter(action.getSpentTimeAfter());
        setSpentTimeBefore(action.getSpentTimeBefore());
        setStatusAfter(action.getStatusAfter());
        setStatusBefore(action.getStatusBefore());
        setTicketOwnerAfter(action.getTicketOwnerAfter());
        setTicketOwnerBefore(action.getTicketOwnerBefore());
        setFilename(action.getFileName());
        setRecallDate(action.getRecallDate());
    }


    /**
     * @param owner
     * @param archivedTicket
     * @param effectiveScope
     */
    public static HArchivedAction archivedAction(final HUser owner, final HArchivedTicket archivedTicket, final String effectiveScope) {
        return new HArchivedAction(owner, archivedTicket, effectiveScope);
    }

    /**
     * @param action
     * @param archivedTicket
     */
    public static HArchivedAction archivedAction(final HAction action, final HArchivedTicket archivedTicket) {
        return new HArchivedAction(action, archivedTicket);
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
        this.actionType = fromString(actionType).toNull();
    }

    public String getCategoryBeforeLabel() {
        return categoryBeforeLabel;
    }

    public void setCategoryBeforeLabel(String categoryBeforeLabel) {
        this.categoryBeforeLabel = categoryBeforeLabel;
    }

    public String getCategoryAfterLabel() {
        return categoryAfterLabel;
    }

    public void setCategoryAfterLabel(String categoryAfterLabel) {
        this.categoryAfterLabel = categoryAfterLabel;
    }

    public String getComputerBefore() {
        return computerBefore;
    }

    public void setComputerBefore(String computerBefore) {
        this.computerBefore = fromString(computerBefore).toNull();
    }

    public String getComputerAfter() {
        return computerAfter;
    }

    public void setComputerAfter(String computerAfter) {
        this.computerAfter = fromString(computerAfter).toNull();
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getEffectiveScope() {
        return effectiveScope;
    }

    public void setEffectiveScope(String effectiveScope) {
        this.effectiveScope = effectiveScope;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public HArchivedTicket getArchivedTicket() {
        return archivedTicket;
    }

    public void setArchivedTicket(HArchivedTicket archivedTicket) {
        this.archivedTicket = archivedTicket;
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

    public HUser getUser() {
        return user;
    }

    public void setUser(HUser user) {
        this.user = user;
    }

    public HUser getTicketOwnerAfter() {
        return ticketOwnerAfter;
    }

    public void setTicketOwnerAfter(HUser ticketOwnerAfter) {
        this.ticketOwnerAfter = ticketOwnerAfter;
    }

    public HUser getInvitedUser() {
        return invitedUser;
    }

    public void setInvitedUser(HUser invitedUser) {
        this.invitedUser = invitedUser;
    }

    public HUser getManagerAfter() {
        return managerAfter;
    }

    public void setManagerAfter(HUser managerAfter) {
        this.managerAfter = managerAfter;
    }

    public HUser getTicketOwnerBefore() {
        return ticketOwnerBefore;
    }

    public void setTicketOwnerBefore(HUser ticketOwnerBefore) {
        this.ticketOwnerBefore = ticketOwnerBefore;
    }

    public HUser getManagerBefore() {
        return managerBefore;
    }

    public void setManagerBefore(HUser managerBefore) {
        this.managerBefore = managerBefore;
    }

    public String getLabelAfter() {
        return labelAfter;
    }

    public void setLabelAfter(String labelAfter) {
        this.labelAfter = fromString(labelAfter).toNull();
    }

    public String getLabelBefore() {
        return labelBefore;
    }

    public void setLabelBefore(String labelBefore) {
        this.labelBefore = fromString(labelBefore).toNull();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = fromString(message).toNull();
    }

    public Integer getPriorityLevelBefore() {
        return priorityLevelBefore;
    }

    public void setPriorityLevelBefore(Integer priorityLevelBefore) {
        this.priorityLevelBefore = priorityLevelBefore;
    }

    public Integer getPriorityLevelAfter() {
        return priorityLevelAfter;
    }

    public void setPriorityLevelAfter(Integer priorityLevelAfter) {
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
        this.scopeBefore = fromString(scopeBefore).toNull();
    }

    public String getScopeAfter() {
        return scopeAfter;
    }

    public void setScopeAfter(String scopeAfter) {
        this.scopeAfter = fromString(scopeAfter).toNull();
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

    public String getStatusBefore() {
        return statusBefore;
    }

    public void setStatusBefore(String statusBefore) {
        this.statusBefore = fromString(statusBefore).toNull();
    }

    public String getStatusAfter() {
        return statusAfter;
    }

    public void setStatusAfter(String statusAfter) {
        this.statusAfter = fromString(statusAfter).toNull();
    }
}