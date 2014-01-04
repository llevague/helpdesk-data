package org.esupportail.helpdesk.data.dao.entities;

import org.esupportail.helpdesk.data.dao.enums.FaqScope;

import java.util.*;
import javax.persistence.*;

/**
 * A class that represents a FAQ container.
 *
 * @deprecated
 */
@SuppressWarnings("deprecation")
@Deprecated
@Entity
@Table(name = "h_faq")
public class HDeprecatedFaqContainer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * The old v2 content.
     */
    @Basic
    @Column(name = "cont")
    private String oldContent;

    /**
     * The content.
     */
    @Basic
    @Column(name = "cont2")
    private String content;

    /**
     * The effective scope.
     */
    @Basic
    @Column(name = "effe_scop", nullable = false)
    private String effectiveScope;

    /**
     * The department, or null.
     */
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "depa_id")
    private HDepartment department;

    /**
     * The parent, or null.
     */
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "pare_id")
    @SuppressWarnings("deprecation")
    private HDeprecatedFaqContainer parent;

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

    @OneToMany(targetEntity = HArchivedTicket.class, mappedBy = "deprecatedConnectionFaqContainer", cascade = CascadeType.MERGE)
    private Set<HArchivedTicket> archivedTickets = new HashSet<>();

    @OneToMany(targetEntity = HDeprecatedFaqEntry.class, mappedBy = "parent", cascade = CascadeType.MERGE)
    private Set<HDeprecatedFaqEntry> deprecatedFaqEntries = new HashSet<>();

    @OneToMany(targetEntity = HOldFaqEntry.class, mappedBy = "parent", cascade = CascadeType.MERGE)
    private Set<HOldFaqEntry> oldFaqEntries = new HashSet<>();

    @OneToMany(targetEntity = HFaqLink.class, mappedBy = "deprecatedFaqContainer", cascade = CascadeType.MERGE)
    private Set<HFaqLink> faqLinks = new HashSet<>();

    @OneToMany(targetEntity = HOldFaqPart.class, mappedBy = "deprecatedFaqContainer", cascade = CascadeType.MERGE)
    private Set<HOldFaqPart> oldFaqParts = new HashSet<>();

    @OneToMany(targetEntity = HDeprecatedFaqContainer.class, mappedBy = "parent", cascade = CascadeType.MERGE)
    private Set<HDeprecatedFaqContainer> deprecatedFaqContainers = new HashSet<>();

    @OneToMany(targetEntity = HTicket.class, mappedBy = "deprecatedConnectionFaqContainer", cascade = CascadeType.MERGE)
    private Set<HTicket> tickets = new HashSet<>();

    /**
     *
     */
    private HDeprecatedFaqContainer() {
        this.lastUpdate = new Date();
        this.scope = FaqScope.DEFAULT.value();
        this.oldContent = " ";
    }

    /**
     * @param fc
     */
    private HDeprecatedFaqContainer(final HDeprecatedFaqContainer fc) {
        this.id = fc.id;
        this.parent = fc.parent;
        this.label = fc.label;
        this.scope = fc.scope;
        this.order = fc.order;
        this.content = fc.content;
        this.lastUpdate = fc.lastUpdate;
        this.effectiveScope = fc.effectiveScope;
        this.department = fc.department;
        this.oldContent = " ";
    }

    /**
     * @param deprecatedFaqContainer
     * @return
     */
    public static HDeprecatedFaqContainer deprecatedFaqContainer(final HDeprecatedFaqContainer deprecatedFaqContainer) {
        return new HDeprecatedFaqContainer(deprecatedFaqContainer);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOldContent() {
        return oldContent;
    }

    public void setOldContent(String oldContent) {
        this.oldContent = oldContent;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getEffectiveScope() {
        return effectiveScope;
    }

    public void setEffectiveScope(String effectiveScope) {
        this.effectiveScope = effectiveScope;
    }

    public HDepartment getDepartment() {
        return department;
    }

    public void setDepartment(HDepartment department) {
        this.department = department;
    }

    public HDeprecatedFaqContainer getParent() {
        return parent;
    }

    public void setParent(HDeprecatedFaqContainer parent) {
        this.parent = parent;
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

    public Set<HDeprecatedFaqEntry> getDeprecatedFaqEntries() {
        return deprecatedFaqEntries;
    }

    public void setDeprecatedFaqEntries(Set<HDeprecatedFaqEntry> deprecatedFaqEntries) {
        this.deprecatedFaqEntries = deprecatedFaqEntries;
    }

    public Set<HOldFaqEntry> getOldFaqEntries() {
        return oldFaqEntries;
    }

    public void setOldFaqEntries(Set<HOldFaqEntry> oldFaqEntries) {
        this.oldFaqEntries = oldFaqEntries;
    }

    public Set<HFaqLink> getFaqLinks() {
        return faqLinks;
    }

    public void setFaqLinks(Set<HFaqLink> faqLinks) {
        this.faqLinks = faqLinks;
    }

    public Set<HOldFaqPart> getOldFaqParts() {
        return oldFaqParts;
    }

    public void setOldFaqParts(Set<HOldFaqPart> oldFaqParts) {
        this.oldFaqParts = oldFaqParts;
    }

    public Set<HDeprecatedFaqContainer> getDeprecatedFaqContainers() {
        return deprecatedFaqContainers;
    }

    public void setDeprecatedFaqContainers(Set<HDeprecatedFaqContainer> deprecatedFaqContainers) {
        this.deprecatedFaqContainers = deprecatedFaqContainers;
    }

    public Set<HTicket> getTickets() {
        return tickets;
    }

    public void setTickets(Set<HTicket> tickets) {
        this.tickets = tickets;
    }
}