package org.esupportail.helpdesk.data.dao.entities;

import org.esupportail.helpdesk.data.dao.enums.FaqScope;

import java.util.*;
import javax.persistence.*;

import static org.esupportail.helpdesk.data.dao.utils.Functions.filterFckInput;

/**
 * A class that represents FAQ.
 */
@Entity
@Table(name = "h_faq2")
public class HFaq {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * The content.
     */
    @Basic
    @Column(name = "cont")
    private String content;

    /**
     * The effective scope.
     */
    @Basic
    @Column(name = "effe_scop", nullable = false)
    private String effectiveScope;

    /**
     * The department (null for a global root faq).
     */
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "depa_id")
    private HDepartment department;

    /**
     * The parent, or null.
     */
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "pare_id")
    private HFaq parent;

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

    @OneToMany(targetEntity = HArchivedTicket.class, mappedBy = "connectionFaq", cascade = CascadeType.MERGE)
    private Set<HArchivedTicket> archivedTickets = new HashSet<>();

    @OneToMany(targetEntity = HFaq.class, mappedBy = "parent", cascade = CascadeType.MERGE)
    private Set<HFaq> faqs = new HashSet<>();

    @OneToMany(targetEntity = HFaqLink.class, mappedBy = "faq", cascade = CascadeType.MERGE)
    private Set<HFaqLink> faqLinks = new HashSet<>();

    @OneToMany(targetEntity = HTicket.class, mappedBy = "connectionFaq", cascade = CascadeType.MERGE)
    private Set<HTicket> tickets = new HashSet<>();

    /**
     *
     */
    private HFaq() {
        this.lastUpdate = new Date();
        this.scope = FaqScope.DEFAULT.value();
    }

    /**
     * @param faq
     */
    private HFaq(final HFaq faq) {
        this.id = faq.id;
        this.parent = faq.parent;
        this.department = faq.department;
        this.label = faq.label;
        this.scope = faq.scope;
        this.order = faq.order;
        this.content = faq.content;
        this.lastUpdate = faq.lastUpdate;
        this.effectiveScope = faq.effectiveScope;
    }

    /**
     * @param faqContainer
     * @param parent
     */
    @SuppressWarnings("deprecation")
    private HFaq(final HDeprecatedFaqContainer faqContainer, final HFaq parent) {
        this.parent = parent;
        this.department = faqContainer.getDepartment();
        this.label = faqContainer.getLabel();
        this.scope = faqContainer.getScope();
        this.content = faqContainer.getContent();
        this.lastUpdate = faqContainer.getLastUpdate();
        this.effectiveScope = faqContainer.getEffectiveScope();
    }

    /**
     * @param faqEntry
     * @param parent
     */
    @SuppressWarnings("deprecation")
    private HFaq(final HDeprecatedFaqEntry faqEntry, final HFaq parent) {
        this.parent = parent;
        this.department = faqEntry.getParent().getDepartment();
        this.label = faqEntry.getLabel();
        this.scope = faqEntry.getScope();
        this.content = faqEntry.getContent();
        this.lastUpdate = faqEntry.getLastUpdate();
        this.effectiveScope = faqEntry.getEffectiveScope();
    }

    /**
     * @param faq
     * @return
     */
    public static HFaq faq(final HFaq faq) {
        return new HFaq(faq);
    }

    /**
     * @param faqContainer
     * @param parent
     * @return
     */
    public static HFaq faq(final HDeprecatedFaqContainer faqContainer, final HFaq parent) {
        return new HFaq(faqContainer, parent);
    }

    /**
     * @param faqEntry
     * @param parent
     * @return
     */
    public static HFaq faq(final HDeprecatedFaqEntry faqEntry, final HFaq parent) {
        return new HFaq(faqEntry, parent);
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
        this.content = filterFckInput.f(content);
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

    public HFaq getParent() {
        return parent;
    }

    public void setParent(HFaq parent) {
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

    public Set<HFaq> getFaqs() {
        return faqs;
    }

    public void setFaqs(Set<HFaq> faqs) {
        this.faqs = faqs;
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