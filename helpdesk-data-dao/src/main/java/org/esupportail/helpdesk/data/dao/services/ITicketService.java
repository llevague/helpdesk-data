package org.esupportail.helpdesk.data.dao.services;

import fj.data.List;
import fj.data.Option;
import org.esupportail.helpdesk.data.dao.entities.HTicket;
import org.esupportail.helpdesk.data.dao.entities.HUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

public interface ITicketService {

    List<HTicket> getTickets();

    Page<HTicket> getTickets(int page, int size, Sort sort);

    Option<HTicket> getTicketById(final Long id);
}
