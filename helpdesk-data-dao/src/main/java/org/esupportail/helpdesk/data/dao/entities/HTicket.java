package org.esupportail.helpdesk.data.dao.entities;

import org.esupportail.helpdesk.data.dao.enums.Priority;

import java.util.*;
import javax.persistence.*;

import static org.esupportail.helpdesk.data.dao.enums.Priority.DEFAULT;
import static org.esupportail.helpdesk.data.dao.enums.TicketStatus.FREE;

/**
 * A class to store tickets.
 */
@Entity
@Table(name = "h_tick")
public class HTicket {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * The time before the ticket is taken in charge (in seconds).
     */
    @Basic
    @Column(name = "char_time", columnDefinition = "INT")
    private Integer chargeTime;

    /**
     * The time before the ticket is closed (in seconds).
     */
    @Basic
    @Column(name = "clos_time", columnDefinition = "INT")
    private Integer closureTime;

    /**
     * The computer of the ticket.
     */
    @Basic
    @Column(name = "comp")
    private String computer;

    /**
     * Creation date of ticket.
     */
    @Basic
    @Column(name = "crea_date", nullable = false)
    private Date creationDate;

    /**
     * The creation day.
     */
    @Basic
    @Column(name = "crea_day", columnDefinition = "INT")
    private Integer creationDay;

    /**
     * The creation day of week.
     */
    @Basic
    @Column(name = "crea_dow", columnDefinition = "INT")
    private Integer creationDow;

    /**
     * The creation hour.
     */
    @Basic
    @Column(name = "crea_hour", columnDefinition = "INT")
    private Integer creationHour;

    /**
     * The creation month.
     */
    @Basic
    @Column(name = "crea_month", columnDefinition = "INT")
    private Integer creationMonth;

    /**
     * The creation year.
     */
    @Basic
    @Column(name = "crea_year", columnDefinition = "INT")
    private Integer creationYear;

    /**
     * The effective scope.
     */
    @Basic
    @Column(name = "effe_scop")
    private String effectiveScope;

    /**
     * An archived ticket link, null if the ticket is not connected to another ticket.
     */
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "conn_arch_tick_id")
    private HArchivedTicket connectionArchivedTicket;

    /**
     * The category of the ticket.
     */
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "cate_id")
    private HCategory category;

    /**
     * HDepartment of the ticket.
     */
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "depa_id", nullable = false)
    private HDepartment department;

    /**
     * The creation department in order to ease statistics.
     */
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "crea_depa_id")
    private HDepartment creationDepartment;

    /**
     * A link to a FAQ container.
     *
     * @deprecated
     */
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "conn_faq_id")
    private HDeprecatedFaqContainer deprecatedConnectionFaqContainer;

    /**
     * A link to a FAQ.
     */
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "conn_faq2_id")
    private HFaq connectionFaq;

    /**
     * A link to an old FaqEntry, null if the ticket is not connected to a faqEntry.
     */
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "conn_faq_entr_id")
    private HOldFaqEntry connectionOldFaqEntry;

    /**
     * A link to a FAQ entry.
     *
     * @deprecated
     */
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "conn_faq_entr2_id")
    private HDeprecatedFaqEntry deprecatedConnectionFaqEntry;

    /**
     * A link to an old FAQ part.
     */
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "conn_faq_part_id")
    private HOldFaqPart connectionOldFaqPart;

    /**
     * A ticket link, null if the ticket is not connected to another ticket.
     */
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "conn_tick_id")
    private HTicket connectionTicket;

    /**
     * Owner of ticket.
     */
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "owne_id", nullable = false)
    private HUser owner;

    /**
     * Manager of ticket.
     */
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "mana_id")
    private HUser manager;

    /**
     * The creator of the ticket, independent of the owner.
     */
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "creator_id")
    private HUser creator;

    /**
     * The label of the ticket.
     */
    @Basic
    @Column(name = "labe", nullable = false)
    private String label;

    /**
     * Date of last action on the ticket.
     */
    @Basic
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "last_acti_date", nullable = false)
    private Date lastActionDate;

    /**
     * The manager's display name.
     */
    @Basic
    @Column(name = "mana_disp_name")
    private String managerDisplayName;

    /**
     * Origin of ticket.
     */
    @Basic
    @Column(name = "orig_id", nullable = false)
    private String origin;

    /**
     * Level of priority.
     */
    @Basic
    @Column(name = "prio_leve", columnDefinition = "INT")
    private int priorityLevel;

    /**
     * The date when the ticket should be recalled.
     */
    @Basic
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "reca_date")
    private Date recallDate;

    /**
     * The scope.
     */
    @Basic
    @Column(name = "scop", nullable = false)
    private String scope;

    /**
     * Spent time on manage ticket (in minutes).
     */
    @Basic
    @Column(name = "spen_time")
    private Long spentTime;

    /**
     * status of ticket.
     */
    @Basic
    @Column(name = "stat", nullable = false)
    private String status;

    @OneToMany(targetEntity = HArchivedTicket.class, mappedBy = "connectionTicket", cascade = CascadeType.MERGE)
    private Set<HArchivedTicket> archivedTickets = new HashSet<>();

    @OneToMany(targetEntity = HBookmark.class, mappedBy = "ticket", cascade = CascadeType.MERGE)
    private Set<HBookmark> bookmarks = new HashSet<>();

    @OneToMany(targetEntity = HAction.class, mappedBy = "ticket", cascade = CascadeType.MERGE)
    private Set<HAction> actions = new HashSet<>();

    @OneToMany(targetEntity = HAction.class, mappedBy = "oldConnectionAfter", cascade = CascadeType.MERGE)
    private Set<HAction> oldConnectionsAfter = new HashSet<>();

    @OneToMany(targetEntity = HFileInfo.class, mappedBy = "ticket", cascade = CascadeType.MERGE)
    private Set<HFileInfo> fileInfos = new HashSet<>();

    @OneToMany(targetEntity = HHistoryItem.class, mappedBy = "ticket", cascade = CascadeType.MERGE)
    private Set<HHistoryItem> historyItems = new HashSet<>();

    @OneToMany(targetEntity = HInvitation.class, mappedBy = "ticket", cascade = CascadeType.MERGE)
    private Set<HInvitation> invitations = new HashSet<>();

    @OneToMany(targetEntity = HTicketMonitoring.class, mappedBy = "ticket", cascade = CascadeType.MERGE)
    private Set<HTicketMonitoring> ticketMonitorings = new HashSet<>();

    @OneToMany(targetEntity = HTicketView.class, mappedBy = "ticket", cascade = CascadeType.MERGE)
    private Set<HTicketView> ticketViews = new HashSet<>();

    @OneToMany(targetEntity = HTicket.class, mappedBy = "connectionTicket", cascade = CascadeType.MERGE)
    private Set<HTicket> ticketConnections = new HashSet<>();

    /**
     *
     */
    private HTicket() {
        setCreationDate(new Date());
        this.priorityLevel = DEFAULT.value();
        this.spentTime = -1L;
    }

    /**
     * Constructor.
     *
     * @param t
     */
    private HTicket(final HTicket t) {
        this();
        this.id = t.id;
        this.owner = t.owner;
        this.origin = t.origin;
        this.manager = t.manager;
        this.department = t.department;
        this.computer = t.computer;
        this.label = t.label;
        this.priorityLevel = t.priorityLevel;
        this.effectiveScope = t.effectiveScope;
        this.connectionTicket = t.connectionTicket;
        this.connectionArchivedTicket = t.connectionArchivedTicket;
        this.connectionFaq = t.connectionFaq;
        this.creationDate = t.creationDate;
        this.spentTime = t.spentTime;
        this.creationDepartment = t.creationDepartment;
        this.creator = t.creator;
        this.chargeTime = t.chargeTime;
        this.closureTime = t.closureTime;
        this.creationYear = t.creationYear;
        this.creationMonth = t.creationMonth;
        this.creationDay = t.creationDay;
        this.creationDow = t.creationDow;
        this.creationHour = t.creationHour;
        this.status = t.status;
        this.scope = t.scope;
        this.connectionOldFaqPart = t.connectionOldFaqPart;
        this.connectionOldFaqEntry = t.connectionOldFaqEntry;
        this.lastActionDate = t.lastActionDate;
        this.category = t.category;
        this.recallDate = t.recallDate;
        this.managerDisplayName = t.managerDisplayName;
    }

    /**
     * Constructor.
     *
     * @param owner
     * @param origin
     * @param creationDepartment
     * @param computer
     * @param label
     * @param priorityLevel
     * @param scope
     * @param category
     */
    private HTicket(
            final HUser owner,
            final String origin,
            final HDepartment creationDepartment,
            final HCategory category,
            final String label,
            final String computer,
            final int priorityLevel,
            final String scope) {
        this();
        setOwner(owner);
        setOrigin(origin);
        setCreationDepartment(creationDepartment);
        setDepartment(category.getDepartment());
        setLabel(label);
        setComputer(computer);
        setPriorityLevel(priorityLevel);
        setCreator(owner);
        setCategory(category);
        setScope(scope);
        setLastActionDate(getCreationDate());
        setStatus(FREE.value());
    }

    /**
     * @param t
     * @return
     */
    public static HTicket ticket(final HTicket t) {
        return new HTicket(t);
    }

    /**
     * @param owner
     * @param origin
     * @param creationDepartment
     * @param category
     * @param label
     * @param computer
     * @param priorityLevel
     * @param scope
     * @return
     */
    public static HTicket ticket(final HUser owner, final String origin, final HDepartment creationDepartment,
                                final HCategory category, final String label, final String computer, final Priority priorityLevel,
                                final String scope) {
        return new HTicket(owner, origin, creationDepartment, category, label, computer, priorityLevel.value(), scope);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getChargeTime() {
        return chargeTime;
    }

    public void setChargeTime(Integer chargeTime) {
        this.chargeTime = chargeTime;
    }

    public Integer getClosureTime() {
        return closureTime;
    }

    public void setClosureTime(Integer closureTime) {
        this.closureTime = closureTime;
    }

    public String getComputer() {
        return computer;
    }

    public void setComputer(String computer) {
        this.computer = computer;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
        if (creationDate != null) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(creationDate);
            this.creationYear = cal.get(Calendar.YEAR);
            this.creationMonth = cal.get(Calendar.MONTH);
            this.creationDay = cal.get(Calendar.DAY_OF_MONTH);
            this.creationDow = cal.get(Calendar.DAY_OF_WEEK);
            this.creationHour = cal.get(Calendar.HOUR_OF_DAY);
        }
    }

    public Integer getCreationDay() {
        return creationDay;
    }

    public void setCreationDay(Integer creationDay) {
        this.creationDay = creationDay;
    }

    public Integer getCreationDow() {
        return creationDow;
    }

    public void setCreationDow(Integer creationDow) {
        this.creationDow = creationDow;
    }

    public Integer getCreationHour() {
        return creationHour;
    }

    public void setCreationHour(Integer creationHour) {
        this.creationHour = creationHour;
    }

    public Integer getCreationMonth() {
        return creationMonth;
    }

    public void setCreationMonth(Integer creationMonth) {
        this.creationMonth = creationMonth;
    }

    public Integer getCreationYear() {
        return creationYear;
    }

    public void setCreationYear(Integer creationYear) {
        this.creationYear = creationYear;
    }

    public String getEffectiveScope() {
        return effectiveScope;
    }

    public void setEffectiveScope(String effectiveScope) {
        this.effectiveScope = effectiveScope;
    }

    public HArchivedTicket getConnectionArchivedTicket() {
        return connectionArchivedTicket;
    }

    public void setConnectionArchivedTicket(HArchivedTicket connectionArchivedTicket) {
        this.connectionArchivedTicket = connectionArchivedTicket;
    }

    public HCategory getCategory() {
        return category;
    }

    public void setCategory(HCategory category) {
        this.category = category;
    }

    public HDepartment getDepartment() {
        return department;
    }

    public void setDepartment(HDepartment department) {
        this.department = department;
    }

    public HDepartment getCreationDepartment() {
        return creationDepartment;
    }

    public void setCreationDepartment(HDepartment creationDepartment) {
        this.creationDepartment = creationDepartment;
    }

    public HDeprecatedFaqContainer getDeprecatedConnectionFaqContainer() {
        return deprecatedConnectionFaqContainer;
    }

    public void setDeprecatedConnectionFaqContainer(HDeprecatedFaqContainer deprecatedConnectionFaqContainer) {
        this.deprecatedConnectionFaqContainer = deprecatedConnectionFaqContainer;
    }

    public HFaq getConnectionFaq() {
        return connectionFaq;
    }

    public void setConnectionFaq(HFaq connectionFaq) {
        this.connectionFaq = connectionFaq;
    }

    public HOldFaqEntry getConnectionOldFaqEntry() {
        return connectionOldFaqEntry;
    }

    public void setConnectionOldFaqEntry(HOldFaqEntry connectionOldFaqEntry) {
        this.connectionOldFaqEntry = connectionOldFaqEntry;
    }

    public HDeprecatedFaqEntry getDeprecatedConnectionFaqEntry() {
        return deprecatedConnectionFaqEntry;
    }

    public void setDeprecatedConnectionFaqEntry(HDeprecatedFaqEntry deprecatedConnectionFaqEntry) {
        this.deprecatedConnectionFaqEntry = deprecatedConnectionFaqEntry;
    }

    public HOldFaqPart getConnectionOldFaqPart() {
        return connectionOldFaqPart;
    }

    public void setConnectionOldFaqPart(HOldFaqPart connectionOldFaqPart) {
        this.connectionOldFaqPart = connectionOldFaqPart;
    }

    public HTicket getConnectionTicket() {
        return connectionTicket;
    }

    public void setConnectionTicket(HTicket connectionTicket) {
        this.connectionTicket = connectionTicket;
    }

    public HUser getOwner() {
        return owner;
    }

    public void setOwner(HUser owner) {
        this.owner = owner;
    }

    public HUser getManager() {
        return manager;
    }

    public void setManager(HUser manager) {
        this.manager = manager;
    }

    public HUser getCreator() {
        return creator;
    }

    public void setCreator(HUser creator) {
        this.creator = creator;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Date getLastActionDate() {
        return lastActionDate;
    }

    public void setLastActionDate(Date lastActionDate) {
        this.lastActionDate = lastActionDate;
    }

    public String getManagerDisplayName() {
        return managerDisplayName;
    }

    public void setManagerDisplayName(String managerDisplayName) {
        this.managerDisplayName = managerDisplayName;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public int getPriorityLevel() {
        return priorityLevel;
    }

    public void setPriorityLevel(int priorityLevel) {
        this.priorityLevel = priorityLevel;
    }

    public Date getRecallDate() {
        return recallDate;
    }

    public void setRecallDate(Date recallDate) {
        this.recallDate = recallDate;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public Long getSpentTime() {
        return spentTime;
    }

    public void setSpentTime(Long spentTime) {
        this.spentTime = spentTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Set<HArchivedTicket> getArchivedTickets() {
        return archivedTickets;
    }

    public void setArchivedTickets(Set<HArchivedTicket> archivedTickets) {
        this.archivedTickets = archivedTickets;
    }

    public Set<HBookmark> getBookmarks() {
        return bookmarks;
    }

    public void setBookmarks(Set<HBookmark> bookmarks) {
        this.bookmarks = bookmarks;
    }

    public Set<HAction> getActions() {
        return actions;
    }

    public void setActions(Set<HAction> actions) {
        this.actions = actions;
    }

    public Set<HAction> getOldConnectionsAfter() {
        return oldConnectionsAfter;
    }

    public void setOldConnectionsAfter(Set<HAction> oldConnectionsAfter) {
        this.oldConnectionsAfter = oldConnectionsAfter;
    }

    public Set<HFileInfo> getFileInfos() {
        return fileInfos;
    }

    public void setFileInfos(Set<HFileInfo> fileInfos) {
        this.fileInfos = fileInfos;
    }

    public Set<HHistoryItem> getHistoryItems() {
        return historyItems;
    }

    public void setHistoryItems(Set<HHistoryItem> historyItems) {
        this.historyItems = historyItems;
    }

    public Set<HInvitation> getInvitations() {
        return invitations;
    }

    public void setInvitations(Set<HInvitation> invitations) {
        this.invitations = invitations;
    }

    public Set<HTicketMonitoring> getTicketMonitorings() {
        return ticketMonitorings;
    }

    public void setTicketMonitorings(Set<HTicketMonitoring> ticketMonitorings) {
        this.ticketMonitorings = ticketMonitorings;
    }

    public Set<HTicketView> getTicketViews() {
        return ticketViews;
    }

    public void setTicketViews(Set<HTicketView> ticketViews) {
        this.ticketViews = ticketViews;
    }

    public Set<HTicket> getTicketConnections() {
        return ticketConnections;
    }

    public void setTicketConnections(Set<HTicket> ticketConnections) {
        this.ticketConnections = ticketConnections;
    }
}