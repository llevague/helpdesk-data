package org.esupportail.helpdesk.data.dao.entities;

import javax.persistence.*;

/**
 * A class to store department managers.
 */
@Entity
@Table(name = "h_depa_mana")
public class HDepartmentManager {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * True if the manager can assign already assigned tickets.
     */
    @Basic
    @Column(name = "assi_tick")
    private Boolean assignTicket;

    /**
     * True if the manager is available.
     */
    @Basic
    @Column(name = "avai")
    private Boolean available;

    /**
     * The department.
     */
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "depa_id", nullable = false)
    private HDepartment department;

    /**
     * The user.
     */
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_id", nullable = false)
    private HUser user;

    /**
     * True if the manager can manage the categories.
     */
    @Basic
    @Column(name = "mana_cate")
    private Boolean manageCategories;

    /**
     * True if the manager can manage the properties of the department.
     */
    @Basic
    @Column(name = "mana_depa")
    private Boolean manageProperties;

    /**
     * True if the manager can manage the FAQs.
     */
    @Basic
    @Column(name = "mana_faq")
    private Boolean manageFaq;

    /**
     * True if the manager can manage the managers.
     */
    @Basic
    @Column(name = "mana_mana")
    private Boolean manageManagers;

    /**
     * True if the manager can change to change the department of a ticket.
     */
    @Basic
    @Column(name = "modi_tick_depa")
    private Boolean modifyTicketDepartment;

    /**
     * The order of this department manager (in the department).
     */
    @Basic
    @Column(name = "orde", columnDefinition = "INT")
    private int order;

    /**
     * Rate.
     */
    @Basic
    @Column(name = "rate", columnDefinition = "INT")
    private int rate;

    /**
     * True if the manager can refuse tickets.
     */
    @Basic
    @Column(name = "refu_tick")
    private Boolean refuseTicket;

    /**
     * True if the manager can reopen tickets (s)he does not manage.
     */
    @Basic
    @Column(name = "reop_all_tick")
    private Boolean reopenAllTickets;

    /**
     * The time for the first report.
     */
    @Basic
    @Column(name = "repo_tim1", columnDefinition = "INT")
    private Integer reportTime1;

    /**
     * The time for the second report.
     */
    @Basic
    @Column(name = "repo_tim2", columnDefinition = "INT")
    private Integer reportTime2;

    /**
     * The report type.
     */
    @Basic
    @Column(name = "repo_type")
    private String reportType;

    /**
     * True to report on the week-end.
     */
    @Basic
    @Column(name = "repo_week")
    private Boolean reportWeekend;

    /**
     * True if the manager can set his own availability.
     */
    @Basic
    @Column(name = "set_own_avai")
    private Boolean setOwnAvailability;

    /**
     * True if the manager can take already assigned tickets.
     */
    @Basic
    @Column(name = "take_alre_assi_tick")
    private Boolean takeAlreadyAssignedTicket;

    /**
     * True if the manager can take free tickets.
     */
    @Basic
    @Column(name = "take_free_tick")
    private Boolean takeFreeTicket;

    /**
     * A priority level for ticket monitoring.
     */
    @Basic
    @Column(name = "tick_moni_any", columnDefinition = "INT")
    private Integer ticketMonitoringAny;

    /**
     * A priority level for ticket monitoring.
     */
    @Basic
    @Column(name = "tick_moni_cate", columnDefinition = "INT")
    private Integer ticketMonitoringCategory;

    /**
     * A priority level for ticket monitoring.
     */
    @Basic
    @Column(name = "tick_moni_mana", columnDefinition = "INT")
    private Integer ticketMonitoringManaged;

    /**
     * The minimum rate.
     */
    @Transient
    public static final int MIN_RATE = 0;

    /**
     * The maximum rate.
     */
    @Transient
    public static final int MAX_RATE = 100;

    /**
     *
     */
    private HDepartmentManager() {
    }

    /**
     * Bean constructor.
     *
     * @param user
     * @param department
     */
    private HDepartmentManager(final HUser user, final HDepartment department) {
        this.user = user;
        this.department = department;
        this.available = false;
        this.refuseTicket = false;
        this.takeAlreadyAssignedTicket = false;
        this.takeFreeTicket = false;
        this.assignTicket = false;
        this.manageProperties = false;
        this.manageManagers = false;
        this.manageCategories = false;
        this.manageFaq = false;
        this.modifyTicketDepartment = false;
        this.rate = MAX_RATE;
        this.setOwnAvailability = false;
        this.reopenAllTickets = false;
        this.order = -1;
        this.reportType = null;
    }


    /**
     * Copy.
     *
     * @param dm the department manager to copy.
     */
    private HDepartmentManager(final HDepartmentManager dm) {
        this.id = dm.id;
        this.user = dm.user;
        this.department = dm.department;
        this.available = dm.available;
        this.refuseTicket = dm.refuseTicket;
        this.takeAlreadyAssignedTicket = dm.takeAlreadyAssignedTicket;
        this.takeFreeTicket = dm.takeFreeTicket;
        this.assignTicket = dm.assignTicket;
        this.manageProperties = dm.manageProperties;
        this.manageManagers = dm.manageManagers;
        this.manageCategories = dm.manageCategories;
        this.manageFaq = dm.manageFaq;
        this.modifyTicketDepartment = dm.modifyTicketDepartment;
        this.rate = dm.rate;
        this.setOwnAvailability = dm.setOwnAvailability;
        this.reopenAllTickets = dm.reopenAllTickets;
        this.order = dm.order;
        this.ticketMonitoringAny = dm.ticketMonitoringAny;
        this.ticketMonitoringCategory = dm.ticketMonitoringCategory;
        this.ticketMonitoringManaged = dm.ticketMonitoringManaged;
        this.reportType = dm.reportType;
        this.reportTime1 = dm.reportTime1;
        this.reportTime2 = dm.reportTime2;
        this.reportWeekend = dm.reportWeekend;
    }

    /**
     * @param user
     * @param department
     * @return
     */
    public static HDepartmentManager departmentManager(final HUser user, final HDepartment department) {
        return new HDepartmentManager(user, department);
    }

    /**
     * @param departmentManager
     * @return a copy of the original {@link HDepartmentManager}
     */
    public static HDepartmentManager departmentManager(final HDepartmentManager departmentManager) {
        return new HDepartmentManager(departmentManager);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getAssignTicket() {
        return assignTicket;
    }

    public void setAssignTicket(Boolean assignTicket) {
        this.assignTicket = assignTicket;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public HDepartment getDepartment() {
        return department;
    }

    public void setDepartment(HDepartment department) {
        this.department = department;
    }

    public HUser getUser() {
        return user;
    }

    public void setUser(HUser user) {
        this.user = user;
    }

    public Boolean getManageCategories() {
        return manageCategories;
    }

    public void setManageCategories(Boolean manageCategories) {
        this.manageCategories = manageCategories;
    }

    public Boolean getManageProperties() {
        return manageProperties;
    }

    public void setManageProperties(Boolean manageProperties) {
        this.manageProperties = manageProperties;
    }

    public Boolean getManageFaq() {
        return manageFaq;
    }

    public void setManageFaq(Boolean manageFaq) {
        this.manageFaq = manageFaq;
    }

    public Boolean getManageManagers() {
        return manageManagers;
    }

    public void setManageManagers(Boolean manageManagers) {
        this.manageManagers = manageManagers;
    }

    public Boolean getModifyTicketDepartment() {
        return modifyTicketDepartment;
    }

    public void setModifyTicketDepartment(Boolean modifyTicketDepartment) {
        this.modifyTicketDepartment = modifyTicketDepartment;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public Boolean getRefuseTicket() {
        return refuseTicket;
    }

    public void setRefuseTicket(Boolean refuseTicket) {
        this.refuseTicket = refuseTicket;
    }

    public Boolean getReopenAllTickets() {
        return reopenAllTickets;
    }

    public void setReopenAllTickets(Boolean reopenAllTickets) {
        this.reopenAllTickets = reopenAllTickets;
    }

    public Integer getReportTime1() {
        return reportTime1;
    }

    public void setReportTime1(Integer reportTime1) {
        this.reportTime1 = reportTime1;
    }

    public Integer getReportTime2() {
        return reportTime2;
    }

    public void setReportTime2(Integer reportTime2) {
        this.reportTime2 = reportTime2;
    }

    public String getReportType() {
        return reportType;
    }

    public void setReportType(String reportType) {
        this.reportType = reportType;
    }

    public Boolean getReportWeekend() {
        return reportWeekend;
    }

    public void setReportWeekend(Boolean reportWeekend) {
        this.reportWeekend = reportWeekend;
    }

    public Boolean getSetOwnAvailability() {
        return setOwnAvailability;
    }

    public void setSetOwnAvailability(Boolean setOwnAvailability) {
        this.setOwnAvailability = setOwnAvailability;
    }

    public Boolean getTakeAlreadyAssignedTicket() {
        return takeAlreadyAssignedTicket;
    }

    public void setTakeAlreadyAssignedTicket(Boolean takeAlreadyAssignedTicket) {
        this.takeAlreadyAssignedTicket = takeAlreadyAssignedTicket;
    }

    public Boolean getTakeFreeTicket() {
        return takeFreeTicket;
    }

    public void setTakeFreeTicket(Boolean takeFreeTicket) {
        this.takeFreeTicket = takeFreeTicket;
    }

    public Integer getTicketMonitoringAny() {
        return ticketMonitoringAny;
    }

    public void setTicketMonitoringAny(Integer ticketMonitoringAny) {
        this.ticketMonitoringAny = ticketMonitoringAny;
    }

    public Integer getTicketMonitoringCategory() {
        return ticketMonitoringCategory;
    }

    public void setTicketMonitoringCategory(Integer ticketMonitoringCategory) {
        this.ticketMonitoringCategory = ticketMonitoringCategory;
    }

    public Integer getTicketMonitoringManaged() {
        return ticketMonitoringManaged;
    }

    public void setTicketMonitoringManaged(Integer ticketMonitoringManaged) {
        this.ticketMonitoringManaged = ticketMonitoringManaged;
    }
}