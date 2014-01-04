package org.esupportail.helpdesk.data.dao.entities;

import java.util.*;
import javax.persistence.*;

import static fj.data.Option.fromString;

/**
 * A class to store the state of the application in the database.
 */
@Entity
@Table(name = "h_stat")
public class HState {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * The time when the index was updated for the last time (for archived tickets).
     *
     * @deprecated
     */
    @Deprecated
    @Basic
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "arch_tick_last_inde_time")
    private Date archivedTicketsLastIndexTime;

    /**
     * The time when the index was updated for the last time (for FAQ containers).
     *
     * @deprecated
     */
    @Deprecated
    @Basic
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "faq_cont_last_inde_time")
    private Date faqContainersLastIndexTime;

    /**
     * The time when the index was updated for the last time (for FAQ entries).
     *
     * @deprecated
     */
    @Deprecated
    @Basic
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "faq_entr_last_inde_time")
    private Date faqEntriesLastIndexTime;

    /**
     * The time when the index was updated for the last time (for tickets).
     *
     * @deprecated
     */
    @Deprecated
    @Basic
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "tick_last_inde_time")
    private Date ticketsLastIndexTime;

    /**
     * The state of the upgrade.
     */
    @Basic
    @Column(name = "upgr_stat")
    private String upgradeState;

    /**
     *
     */
    private HState() {
    }

    /**
     * @return
     */
    public static HState state() {
        return new HState();
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

    public Date getFaqContainersLastIndexTime() {
        return faqContainersLastIndexTime;
    }

    public void setFaqContainersLastIndexTime(Date faqContainersLastIndexTime) {
        this.faqContainersLastIndexTime = faqContainersLastIndexTime;
    }

    public Date getFaqEntriesLastIndexTime() {
        return faqEntriesLastIndexTime;
    }

    public void setFaqEntriesLastIndexTime(Date faqEntriesLastIndexTime) {
        this.faqEntriesLastIndexTime = faqEntriesLastIndexTime;
    }

    public Date getTicketsLastIndexTime() {
        return ticketsLastIndexTime;
    }

    public void setTicketsLastIndexTime(Date ticketsLastIndexTime) {
        this.ticketsLastIndexTime = ticketsLastIndexTime;
    }

    public String getUpgradeState() {
        return upgradeState;
    }

    public void setUpgradeState(String upgradeState) {
        this.upgradeState = fromString(upgradeState).toNull();
    }
}