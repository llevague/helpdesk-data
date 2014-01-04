package org.esupportail.helpdesk.data.dao.entities;

import org.esupportail.helpdesk.data.dao.enums.FaqScope;

import java.util.*;
import javax.persistence.*;

/**
 * A class that represents a FAQ entry.
 *
 * @deprecated
 */
@SuppressWarnings("deprecation")
@Deprecated
@Entity
@Table(name = "h_faq_entr2")
public class HDeprecatedFaqEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * The content.
     */
    @Basic
    @Column(name = "cont", nullable = false)
    private String content;

    /**
     * The parent, or null.
     */
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "faq_id", nullable = false)
    @SuppressWarnings("deprecation")
    private HDeprecatedFaqContainer parent;

    /**
     * The effective scope.
     */
    @Basic
    @Column(name = "effe_scop", nullable = false)
    private String effectiveScope;

    /**
     * The label.
     */
    @Basic
    @Column(name = "labe", nullable = false)
    private String label;

    /**
     * Date of last modification.
     */
    @Basic
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "last_upda")
    private Date lastUpdate;

    /**
     * The order.
     */
    @Basic
    @Column(name = "orde", columnDefinition = "INT")
    private int order;

    /**
     * The scope.
     */
    @Basic
    @Column(name = "scop", nullable = false)
    private String scope;

    @OneToMany(targetEntity = HArchivedTicket.class, mappedBy = "deprecatedConnectionFaqEntry", cascade = CascadeType.MERGE)
    private Set<HArchivedTicket> archivedTickets = new HashSet<>();

    @OneToMany(targetEntity = HFaqLink.class, mappedBy = "deprecatedFaqEntry", cascade = CascadeType.MERGE)
    private Set<HFaqLink> faqLinks = new HashSet<>();

    @OneToMany(targetEntity = HTicket.class, mappedBy = "deprecatedConnectionFaqEntry", cascade = CascadeType.MERGE)
    private Set<HTicket> tickets = new HashSet<>();

    /**
     *
     */
    private HDeprecatedFaqEntry() {
        this.lastUpdate = new Date();
        this.scope = FaqScope.DEFAULT.value();
    }

    /**
     * @param fe
     */
    private HDeprecatedFaqEntry(final HDeprecatedFaqEntry fe) {
        this.id = fe.id;
        this.parent = fe.parent;
        this.label = fe.label;
        this.scope = fe.scope;
        this.order = fe.order;
        this.content = fe.content;
        this.lastUpdate = fe.lastUpdate;
        this.effectiveScope = fe.effectiveScope;
    }

    /**
     * @param deprecatedFaqEntry
     * @return
     */
    public static HDeprecatedFaqEntry deprecatedFaqEntry(final HDeprecatedFaqEntry deprecatedFaqEntry) {
        return new HDeprecatedFaqEntry(deprecatedFaqEntry);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public HDeprecatedFaqContainer getParent() {
        return parent;
    }

    public void setParent(HDeprecatedFaqContainer parent) {
        this.parent = parent;
    }

    public String getEffectiveScope() {
        return effectiveScope;
    }

    public void setEffectiveScope(String effectiveScope) {
        this.effectiveScope = effectiveScope;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public Set<HArchivedTicket> getArchivedTickets() {
        return archivedTickets;
    }

    public void setArchivedTickets(Set<HArchivedTicket> archivedTickets) {
        this.archivedTickets = archivedTickets;
    }

    public Set<HFaqLink> getFaqLinks() {
        return faqLinks;
    }

    public void setFaqLinks(Set<HFaqLink> faqLinks) {
        this.faqLinks = faqLinks;
    }

    public Set<HTicket> getTickets() {
        return tickets;
    }

    public void setTickets(Set<HTicket> tickets) {
        this.tickets = tickets;
    }
}