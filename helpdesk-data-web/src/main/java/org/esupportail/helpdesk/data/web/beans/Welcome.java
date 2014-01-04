package org.esupportail.helpdesk.data.web.beans;

import org.springframework.hateoas.ResourceSupport;

import java.io.Serializable;

public class Welcome extends ResourceSupport implements Serializable {

    private static final long serialVersionUID = 1128170420762073721L;

    private Welcome() {
        super();
    }

    public static Welcome welcome() {
        return new Welcome();
    }
}
