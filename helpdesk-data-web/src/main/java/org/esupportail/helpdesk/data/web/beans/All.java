package org.esupportail.helpdesk.data.web.beans;

import org.springframework.hateoas.ResourceSupport;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class All<T> extends ResourceSupport implements Serializable {

    private static final long serialVersionUID = -6214924238862582926L;

    private List<T> list = Collections.unmodifiableList(new ArrayList<T>());

    private All() {
        super();
    }

    private All(final List<T> list) {
        this();
        this.list = Collections.unmodifiableList(new ArrayList<>(list));
    }

    public static <T> All<T> all() {
        return new All<>();
    }

    public static <T> All<T> all(final List<T> list) {
        return new All<>(list);
    }

    public final void addElement(final T element) {
        List<T> modifiableList = new ArrayList<>(list);
        modifiableList.add(element);
        list = Collections.unmodifiableList(modifiableList);
    }

    public final void removeElement(final T element) {
        List<T> modifiableList = new ArrayList<>(list);
        modifiableList.remove(element);
        list = Collections.unmodifiableList(modifiableList);
    }

    public final void clearElements() {
        list = Collections.unmodifiableList(new ArrayList<T>());
    }

    public List<T> getList() {
        return list;
    }
}
