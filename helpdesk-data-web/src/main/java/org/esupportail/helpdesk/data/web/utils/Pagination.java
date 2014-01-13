package org.esupportail.helpdesk.data.web.utils;

import fj.F;
import fj.P4;
import org.springframework.hateoas.Link;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

public final class Pagination {

    public static final F<P4<Integer, Integer, String, String>, Link> buildPaginationLink =
            new F<P4<Integer, Integer, String, String>, Link>() {
                public Link f(P4<Integer, Integer, String, String> p) {
                    String path = ServletUriComponentsBuilder
                            .fromCurrentRequestUri()
                            .queryParam("page", p._1())
                            .queryParam("size", p._2())
                            .queryParam("sort", p._3())
                            .build()
                            .toUriString();
                    Link link = new Link(path, p._4());
                    return link;
                }
            };
}
