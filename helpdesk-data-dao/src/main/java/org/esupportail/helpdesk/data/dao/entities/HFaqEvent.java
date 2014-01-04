package org.esupportail.helpdesk.data.dao.entities;

import java.util.*;
import javax.persistence.*;

/**
 * A FAQ event, used to send reports.
 */
@Entity
@Table(name = "h_faq_even")
public class HFaqEvent {

    @Id
    private Long id;

    /**
     * The action.
     */
    @Basic
    @Column(nullable = false)
    private String action;

    /**
     * The date.
     */
    @Basic
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "dat")
    private Date date;

    /**
     * The FAQ id.
     */
    @Basic
    @Column(name = "faq_id")
    private Long faqId;

    /**
     * The to department.
     */
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "to_depa_id")
    private HDepartment toDepartment;

    /**
     * The from department.
     */
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "from_depa_id")
    private HDepartment fromDepartment;

    /**
     * The department.
     */
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "depa_id")
    private HDepartment department;

    /**
     * The author.
     */
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "auth_id")
    private HUser author;

    /**
     * The label (for deletions).
     */
    @Basic
    @Column(name = "labe")
    private String label;

    /**
     *
     */
    private HFaqEvent() {
    }

    /**
     * @param action
     * @param author
     * @param faq
     * @param department
     * @param fromDepartment
     * @param toDepartment
     */
    private HFaqEvent(
            final String action,
            final HUser author,
            final HFaq faq,
            final HDepartment department,
            final HDepartment fromDepartment,
            final HDepartment toDepartment) {
        this.action = action;
        this.author = author;
        this.label = faq.getLabel();
        this.department = department;
        this.fromDepartment = fromDepartment;
        this.toDepartment = toDepartment;
        this.faqId = faq.getId();
        this.date = new Date();
    }

    /**
     * @param action
     * @param author
     * @param faq
     * @return a simple event.
     */
    private static HFaqEvent simple(
            final String action,
            final HUser author,
            final HFaq faq) {
        return new HFaqEvent(action, author, faq, faq.getDepartment(), null, null);
    }

    /**
     * @param author
     * @param faq
     * @return an update event.
     */
    public static HFaqEvent update(
            final HUser author,
            final HFaq faq) {
        return simple("UPDATE", author, faq);
    }

    /**
     * @param author
     * @param faq
     * @return a create event.
     */
    public static HFaqEvent create(
            final HUser author,
            final HFaq faq) {
        return simple("CREATE", author, faq);
    }

    /**
     * @param author
     * @param faq
     * @return a delete event.
     */
    public static HFaqEvent delete(
            final HUser author,
            final HFaq faq) {
        return simple("DELETE", author, faq);
    }

    /**
     * @param author
     * @param faq
     * @param fromDepartment
     * @return a moveTo event for a FAQ.
     */
    public static HFaqEvent moveTo(
            final HUser author,
            final HFaq faq,
            final HDepartment fromDepartment) {
        return new HFaqEvent(
                "MOVE_TO", author, faq, fromDepartment, null, faq.getDepartment());
    }

    /**
     * @param author
     * @param faq
     * @param fromDepartment
     * @return a moveFrom event for a FAQ.
     */
    public static HFaqEvent moveFrom(
            final HUser author,
            final HFaq faq,
            final HDepartment fromDepartment) {
        return new HFaqEvent(
                "MOVE_FROM", author, faq, faq.getDepartment(), fromDepartment, null);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Long getFaqId() {
        return faqId;
    }

    public void setFaqId(Long faqId) {
        this.faqId = faqId;
    }

    public HDepartment getToDepartment() {
        return toDepartment;
    }

    public void setToDepartment(HDepartment toDepartment) {
        this.toDepartment = toDepartment;
    }

    public HDepartment getFromDepartment() {
        return fromDepartment;
    }

    public void setFromDepartment(HDepartment fromDepartment) {
        this.fromDepartment = fromDepartment;
    }

    public HDepartment getDepartment() {
        return department;
    }

    public void setDepartment(HDepartment department) {
        this.department = department;
    }

    public HUser getAuthor() {
        return author;
    }

    public void setAuthor(HUser author) {
        this.author = author;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}