package org.esupportail.helpdesk.data.dao.utils;

import fj.F;
import fj.F2;
import fj.data.Option;

import static fj.data.Option.fromString;
import static org.esupportail.helpdesk.data.dao.enums.ActionScope.DEFAULT;
import static org.esupportail.helpdesk.data.dao.enums.ActionScope.OWNER;
import static org.esupportail.helpdesk.data.dao.enums.TicketScope.PUBLIC;

public final class Functions {

    /**
     * Computes the effective scope according to the current scope and the ticket's one.
     */
    public static F2<String, String, String> effectiveScope = new F2<String, String, String>() {
        public String f(String scope, String ticketEffectiveScope) {
            if (!DEFAULT.value().equals(scope)) {
                return scope;
            }
            if (PUBLIC.value().equals(ticketEffectiveScope)) {
                return PUBLIC.value();
            }
            return OWNER.value();
        }
    };

    /**
     * Returns null if the input string is empty.
     */
    public static F<String, String> filterFckInput = new F<String, String>() {
        public String f(final String input) {
            return fromString(input).filter(new F<String, Boolean>() {
                public Boolean f(String s) {
                    return !"<p>&#160;</p>".equals(input) && !"<br />".equals(input);
                }
            }).map(new F<String, String>() {
                public String f(String s) {
                    return s.trim();
                }
            }).toNull();
        }
    };
}
