package org.esupportail.helpdesk.data.dao.entities;

import javax.persistence.*;

/**
 * A class to store old ticket templates, used to upgrade only.
 *
 * @deprecated
 */
@Deprecated
@Entity
@Table(name = "h_tick_temp")
public class HOldTicketTemplate {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * The category.
     */
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "cate_id", nullable = false)
    private HCategory category;

    /**
     * Short description.
     */
    @Basic
    @Column(name = "labe", nullable = false)
    private String label;

    /**
     * The order of the member in the category.
     */
    @Basic
    @Column(name = "orde", columnDefinition = "INT")
    private Integer order;

    /**
     * The label of the future ticket.
     */
    @Basic
    @Column(name = "tick_temp_labe")
    private String ticketTemplateLabel;

    /**
     * The message of the future ticket.
     */
    @Basic
    @Column(name = "tick_temp_mess")
    private String ticketTemplateMessage;

    /**
     * The priority level of the future ticket.
     */
    @Basic
    @Column(name = "tick_temp_prio_leve", columnDefinition = "INT")
    private Integer ticketTemplatePriorityLevel;

    /**
     * Defines if the ticket template uses the default message of the category.
     */
    @Basic
    @Column(name = "use_cate_mess")
    private Boolean useCategoryMessage;

    /**
     * Defines if the ticket template uses the default priority of the category.
     */
    @Basic
    @Column(name = "use_cate_prio")
    private Boolean useCategoryPriority;

    /**
     * Extended description.
     */
    @Basic
    @Column(name = "xlab", nullable = false)
    private String xlabel;

    /**
     *
     */
    private HOldTicketTemplate() {
    }

    /**
     * @return
     */
    public static HOldTicketTemplate oldTicketTemplate() {
        return new HOldTicketTemplate();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public HCategory getCategory() {
        return category;
    }

    public void setCategory(HCategory category) {
        this.category = category;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public String getTicketTemplateLabel() {
        return ticketTemplateLabel;
    }

    public void setTicketTemplateLabel(String ticketTemplateLabel) {
        this.ticketTemplateLabel = ticketTemplateLabel;
    }

    public String getTicketTemplateMessage() {
        return ticketTemplateMessage;
    }

    public void setTicketTemplateMessage(String ticketTemplateMessage) {
        this.ticketTemplateMessage = ticketTemplateMessage;
    }

    public Integer getTicketTemplatePriorityLevel() {
        return ticketTemplatePriorityLevel;
    }

    public void setTicketTemplatePriorityLevel(Integer ticketTemplatePriorityLevel) {
        this.ticketTemplatePriorityLevel = ticketTemplatePriorityLevel;
    }

    public Boolean getUseCategoryMessage() {
        return useCategoryMessage;
    }

    public void setUseCategoryMessage(Boolean useCategoryMessage) {
        this.useCategoryMessage = useCategoryMessage;
    }

    public Boolean getUseCategoryPriority() {
        return useCategoryPriority;
    }

    public void setUseCategoryPriority(Boolean useCategoryPriority) {
        this.useCategoryPriority = useCategoryPriority;
    }

    public String getXlabel() {
        return xlabel;
    }

    public void setXlabel(String xlabel) {
        this.xlabel = xlabel;
    }
}