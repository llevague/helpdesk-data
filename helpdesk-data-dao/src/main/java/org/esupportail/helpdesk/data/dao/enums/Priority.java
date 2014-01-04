package org.esupportail.helpdesk.data.dao.enums;

public enum Priority {

    DEFAULT(0);

    private Integer value;

    private Priority(final Integer value) {
        this.value = value;
    }

    public Integer value() {
        return value;
    }
}
