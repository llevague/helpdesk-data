package org.esupportail.helpdesk.data.dao.entities;

import javax.persistence.*;

/**
 * The class that represents FAQ links.
 */
@Entity
@Table(name = "h_faq_link")
public class HFaqLink {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * The category.
     */
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "cate_id")
    private HCategory category;

    /**
     * The department.
     */
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "depa_id")
    private HDepartment department;

    /**
     * The FAQ container.
     *
     * @deprecated
     */
    @Deprecated
    @SuppressWarnings("deprecation")
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "faq_cont")
    private HDeprecatedFaqContainer deprecatedFaqContainer;

    /**
     * The FAQ.
     */
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "deprecatedFaqContainer")
    private HFaq faq;

    /**
     * The FAQ entry.
     *
     * @deprecated
     */
    @Deprecated
    @SuppressWarnings("deprecation")
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "faq_entr")
    private HDeprecatedFaqEntry deprecatedFaqEntry;

    /**
     * The order.
     */
    @Basic
    @Column(name = "orde", columnDefinition = "INT")
    private Integer order;

    /**
     *
     */
    private HFaqLink() {
    }

    /**
     * @param department
     * @param category
     * @param faq
     */
    private HFaqLink(
            final HDepartment department,
            final HCategory category,
            final HFaq faq) {
        super();
        this.department = department;
        this.category = category;
        this.faq = faq;
    }

    /**
     * @param department
     * @param faq
     * @return
     */
    public static HFaqLink faqLink(final HDepartment department, final HFaq faq) {
        return new HFaqLink(department, null, faq);
    }

    /**
     * @param category
     * @param faq
     * @return
     */
    public static HFaqLink faqLink(final HCategory category, final HFaq faq) {
        return new HFaqLink(null, category, faq);
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

    public HDepartment getDepartment() {
        return department;
    }

    public void setDepartment(HDepartment department) {
        this.department = department;
    }

    public HDeprecatedFaqContainer getDeprecatedFaqContainer() {
        return deprecatedFaqContainer;
    }

    public void setDeprecatedFaqContainer(HDeprecatedFaqContainer deprecatedFaqContainer) {
        this.deprecatedFaqContainer = deprecatedFaqContainer;
    }

    public HFaq getFaq() {
        return faq;
    }

    public void setFaq(HFaq faq) {
        this.faq = faq;
    }

    public HDeprecatedFaqEntry getDeprecatedFaqEntry() {
        return deprecatedFaqEntry;
    }

    public void setDeprecatedFaqEntry(HDeprecatedFaqEntry deprecatedFaqEntry) {
        this.deprecatedFaqEntry = deprecatedFaqEntry;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }
}