package org.esupportail.helpdesk.data.dao.entities;

import java.util.*;
import javax.persistence.*;

/**
 * A class to store the config of the application in the database.
 */
@Entity
@Table(name = "h_conf")
public class HConfig {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * The time when the index was updated for the last time (for archived tickets).
     */
    @Basic
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "arch_tick_last_inde_time")
    private Date archivedTicketsLastIndexTime;

    /**
     * The department selection context time.
     */
    @Basic
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "depa_sele_cont_time")
    private Date departmentSelectionContextTime;

    /**
     * The time when the index was updated for the last time (for FAQs).
     */
    @Basic
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "faq_last_inde_time")
    private Date faqsLastIndexTime;

    /**
     * The default category icon.
     */
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "defa_cate_icon_id")
    private HIcon defaultCategoryIcon;

    /**
     * The default department icon.
     */
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "defa_depa_icon_id")
    private HIcon defaultDepartmentIcon;

    /**
     * The install date.
     */
    @Basic
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "inst_date")
    private Date installDate;

    /**
     * The time when the index was updated for the last time (for tickets).
     */
    @Basic
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "tick_last_inde_time")
    private Date ticketsLastIndexTime;

    /**
     *
     */
    private HConfig() {
    }

    /**
     * @return
     */
    public static HConfig config() {
        return new HConfig();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getArchivedTicketsLastIndexTime() {
        return archivedTicketsLastIndexTime;
    }

    public void setArchivedTicketsLastIndexTime(Date archivedTicketsLastIndexTime) {
        this.archivedTicketsLastIndexTime = archivedTicketsLastIndexTime;
    }

    public Date getDepartmentSelectionContextTime() {
        return departmentSelectionContextTime;
    }

    public void setDepartmentSelectionContextTime(Date departmentSelectionContextTime) {
        this.departmentSelectionContextTime = departmentSelectionContextTime;
    }

    public Date getFaqsLastIndexTime() {
        return faqsLastIndexTime;
    }

    public void setFaqsLastIndexTime(Date faqsLastIndexTime) {
        this.faqsLastIndexTime = faqsLastIndexTime;
    }

    public HIcon getDefaultCategoryIcon() {
        return defaultCategoryIcon;
    }

    public void setDefaultCategoryIcon(HIcon defaultCategoryIcon) {
        this.defaultCategoryIcon = defaultCategoryIcon;
    }

    public HIcon getDefaultDepartmentIcon() {
        return defaultDepartmentIcon;
    }

    public void setDefaultDepartmentIcon(HIcon defaultDepartmentIcon) {
        this.defaultDepartmentIcon = defaultDepartmentIcon;
    }

    public Date getInstallDate() {
        return installDate;
    }

    public void setInstallDate(Date installDate) {
        this.installDate = installDate;
    }

    public Date getTicketsLastIndexTime() {
        return ticketsLastIndexTime;
    }

    public void setTicketsLastIndexTime(Date ticketsLastIndexTime) {
        this.ticketsLastIndexTime = ticketsLastIndexTime;
    }
}