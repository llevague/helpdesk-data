package org.esupportail.helpdesk.data.web.beans;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.Wither;
import org.springframework.hateoas.ResourceSupport;

import java.io.Serializable;
import java.util.Date;

@Getter
@Wither
@ToString
@AllArgsConstructor(staticName = "ticket")
public class Ticket extends ResourceSupport implements Serializable {

    private Long pk;

    private Integer chargeTime;

    private Integer closureTime;

    private String computer;

    private Date creationDate;

    private Integer creationDay;

    private Integer creationDow;

    private Integer creationHour;

    private Integer creationMonth;

    private Integer creationYear;

    private String effectiveScope;

//    private HArchivedTicket connectionArchivedTicket;
//
//    private HCategory category;
//
//    private HDepartment department;
//
//    private HDepartment creationDepartment;
//
//    private HDeprecatedFaqContainer deprecatedConnectionFaqContainer;
//
//    private HFaq connectionFaq;
//
//    private HOldFaqEntry connectionOldFaqEntry;
//
//    private HDeprecatedFaqEntry deprecatedConnectionFaqEntry;
//
//    private HOldFaqPart connectionOldFaqPart;

    private Ticket connectionTicket;

    private String label;

    private Date lastActionDate;

    private String managerDisplayName;

    private String origin;

    private int priorityLevel;

    private Date recallDate;

    private String scope;

    private Long spentTime;

    private String status;

    private Ticket() {}

    private Ticket(final String label) {
        this.label = label;
    }

    public static Ticket ticket(final String label) {
        return new Ticket(label);
    }
}
