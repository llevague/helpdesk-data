package org.esupportail.helpdesk.data.dao.entities;

import org.esupportail.helpdesk.data.dao.enums.FaqScope;

import java.util.*;
import javax.persistence.*;

/**
 * A class that handles the old FAQ parts, for upgrade only.
 *
 * @deprecated
 */
@Deprecated
@SuppressWarnings("deprecation")
@Entity
@Table(name = "h_faq_part")
public class HOldFaqPart {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * The content.
     */
    @Basic
    @Column(name = "cont")
    private String content;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "faq_id", nullable = false)
    private HDeprecatedFaqContainer deprecatedFaqContainer;

    /**
     * The label.
     */
    @Basic
    @Column(name = "labe")
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
    @Column(name = "scop")
    private String scope;

    @OneToMany(targetEntity = HAction.class, mappedBy = "oldFaqPartConnectionAfter", cascade = CascadeType.MERGE)
    private Set<HAction> actions = new HashSet<>();

    @OneToMany(targetEntity = HOldFaqEntry.class, mappedBy = "oldFaqPart", cascade = CascadeType.MERGE)
    private Set<HOldFaqEntry> oldFaqEntries = new HashSet<>();

    @OneToMany(targetEntity = HTicket.class, mappedBy = "connectionOldFaqPart", cascade = CascadeType.MERGE)
    private Set<HTicket> tickets = new HashSet<>();

    /**
     *
     */
    private HOldFaqPart() {
        this.lastUpdate = new Date();
        this.scope = FaqScope.DEFAULT.value();
    }

    /**
     * @return
     */
    public static HOldFaqPart oldFaqPart() {
        return new HOldFaqPart();
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

    public HDeprecatedFaqContainer getDeprecatedFaqContainer() {
        return deprecatedFaqContainer;
    }

    public void setDeprecatedFaqContainer(HDeprecatedFaqContainer deprecatedFaqContainer) {
        this.deprecatedFaqContainer = deprecatedFaqContainer;
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

    public Set<HAction> getActions() {
        return actions;
    }

    public void setActions(Set<HAction> actions) {
        this.actions = actions;
    }

    public Set<HOldFaqEntry> getOldFaqEntries() {
        return oldFaqEntries;
    }

    public void setOldFaqEntries(Set<HOldFaqEntry> oldFaqEntries) {
        this.oldFaqEntries = oldFaqEntries;
    }

    public Set<HTicket> getTickets() {
        return tickets;
    }

    public void setTickets(Set<HTicket> tickets) {
        this.tickets = tickets;
    }
}