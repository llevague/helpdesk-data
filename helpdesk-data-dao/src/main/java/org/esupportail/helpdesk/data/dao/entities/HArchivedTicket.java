package org.esupportail.helpdesk.data.dao.entities;

import org.esupportail.helpdesk.data.dao.enums.Priority;

import java.util.*;
import javax.persistence.*;

/**
 * A class to store archived tickets.
 */
@Entity
@Table(name = "h_arch_tick")
public class HArchivedTicket {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * Date of last action on the ticket.
     */
    @Basic
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "arch_date", nullable = false)
    private Date archivingDate;

    /**
     * The category of the ticket.
     */
    @Basic
    @Column(name = "cate_labe", nullable = false)
    private String categoryLabel;

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
    @Temporal(TemporalType.TIMESTAMP)
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
    @Deprecated
    private HDeprecatedFaqContainer deprecatedConnectionFaqContainer;

    /**
     * A link to a FAQ.
     */
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "conn_faq2_id")
    private HFaq connectionFaq;

    /**
     * A link to a FAQ entry.
     *
     * @deprecated
     */
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "conn_faq_entr_id")
    @SuppressWarnings("deprecation")
    @Deprecated
    private HDeprecatedFaqEntry deprecatedConnectionFaqEntry;

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
     * Spent time on manage ticket (in minutes).
     */
    @Basic
    @Column(name = "spen_time")
    private Long spentTime;

    /**
     * The id of the ticket before archived.
     */
    @Basic
    @Column(name = "tick_id")
    private Long ticketId;

    @OneToMany(targetEntity = HArchivedAction.class, mappedBy = "archivedTicket", cascade = CascadeType.MERGE)
    private Set<HArchivedAction> archivedActions = new HashSet<>();

    @OneToMany(targetEntity = HArchivedFileInfo.class, mappedBy = "archivedTicket", cascade = CascadeType.MERGE)
    private Set<HArchivedFileInfo> archivedFileInfos = new HashSet<>();

    @OneToMany(targetEntity = HArchivedInvitation.class, mappedBy = "archivedTicket", cascade = CascadeType.MERGE)
    private Set<HArchivedInvitation> archivedInvitations = new HashSet<>();

    @OneToMany(targetEntity = HArchivedTicket.class, mappedBy = "connectionArchivedTicket", cascade = CascadeType.MERGE)
    private Set<HArchivedTicket> archivedTickets = new HashSet<>();

    @OneToMany(targetEntity = HBookmark.class, mappedBy = "archivedTicket", cascade = CascadeType.MERGE)
    private Set<HBookmark> bookmarks = new HashSet<>();

    @OneToMany(targetEntity = HHistoryItem.class, mappedBy = "archivedTicket", cascade = CascadeType.MERGE)
    private Set<HHistoryItem> historyItems = new HashSet<>();

    @OneToMany(targetEntity = HTicket.class, mappedBy = "connectionArchivedTicket", cascade = CascadeType.MERGE)
    private Set<HTicket> tickets = new HashSet<>();

    /**
     *
     */
    private HArchivedTicket() {
        setCreationDate(new Date());
        this.priorityLevel = Priority.DEFAULT.value();
        this.spentTime = -1L;
    }

    /**
     * @param t
     */
    private HArchivedTicket(final HArchivedTicket t) {
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
        this.ticketId = t.ticketId;
        this.archivingDate = t.archivingDate;
        this.categoryLabel = t.categoryLabel;
    }

    /**
     * @param ticket
     */
    private HArchivedTicket(final HTicket ticket) {
        this();
        setArchivingDate(new Date());
        setCategoryLabel(ticket.getCategory().getLabel());
        setComputer(ticket.getComputer());
        if (ticket.getConnectionFaq() != null) {
            setConnectionFaq(ticket.getConnectionFaq());
        }
        if (ticket.getConnectionTicket() != null) {
            setConnectionTicket(ticket.getConnectionTicket());
        }
        if (ticket.getConnectionArchivedTicket() != null) {
            setConnectionArchivedTicket(ticket.getConnectionArchivedTicket());
        }
        setCreationDate(ticket.getCreationDate());
        setCreationDepartment(ticket.getCreationDepartment());
        setCreator(ticket.getCreator());
        setDepartment(ticket.getDepartment());
        setEffectiveScope(ticket.getEffectiveScope());
        setLabel(ticket.getLabel());
        setManager(ticket.getManager());
        setOrigin(ticket.getOrigin());
        setOwner(ticket.getOwner());
        setPriorityLevel(ticket.getPriorityLevel());
        setSpentTime(ticket.getSpentTime());
        setTicketId(ticket.getId());
        setChargeTime(ticket.getChargeTime());
        setClosureTime(ticket.getClosureTime());
    }

    /**
     * @param archivedTicket
     * @return
     */
    public static HArchivedTicket archivedTicket(final HArchivedTicket archivedTicket) {
        return new HArchivedTicket(archivedTicket);
    }

    /**
     * @param ticket
     * @return
     */
    public static HArchivedTicket archivedTicket(final HTicket ticket) {
        return new HArchivedTicket(ticket);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getArchivingDate() {
        return archivingDate;
    }

    public void setArchivingDate(Date archivingDate) {
        this.archivingDate = archivingDate;
    }

    public String getCategoryLabel() {
        return categoryLabel;
    }

    public void setCategoryLabel(String categoryLabel) {
        this.categoryLabel = categoryLabel;
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

    public HDeprecatedFaqEntry getDeprecatedConnectionFaqEntry() {
        return deprecatedConnectionFaqEntry;
    }

    public void setDeprecatedConnectionFaqEntry(HDeprecatedFaqEntry deprecatedConnectionFaqEntry) {
        this.deprecatedConnectionFaqEntry = deprecatedConnectionFaqEntry;
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

    public Long getSpentTime() {
        return spentTime;
    }

    public void setSpentTime(Long spentTime) {
        this.spentTime = spentTime;
    }

    public Long getTicketId() {
        return ticketId;
    }

    public void setTicketId(Long ticketId) {
        this.ticketId = ticketId;
    }

    public Set<HArchivedAction> getArchivedActions() {
        return archivedActions;
    }

    public void setArchivedActions(Set<HArchivedAction> archivedActions) {
        this.archivedActions = archivedActions;
    }

    public Set<HArchivedFileInfo> getArchivedFileInfos() {
        return archivedFileInfos;
    }

    public void setArchivedFileInfos(Set<HArchivedFileInfo> archivedFileInfos) {
        this.archivedFileInfos = archivedFileInfos;
    }

    public Set<HArchivedInvitation> getArchivedInvitations() {
        return archivedInvitations;
    }

    public void setArchivedInvitations(Set<HArchivedInvitation> archivedInvitations) {
        this.archivedInvitations = archivedInvitations;
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

    public Set<HHistoryItem> getHistoryItems() {
        return historyItems;
    }

    public void setHistoryItems(Set<HHistoryItem> historyItems) {
        this.historyItems = historyItems;
    }

    public Set<HTicket> getTickets() {
        return tickets;
    }

    public void setTickets(Set<HTicket> tickets) {
        this.tickets = tickets;
    }
}