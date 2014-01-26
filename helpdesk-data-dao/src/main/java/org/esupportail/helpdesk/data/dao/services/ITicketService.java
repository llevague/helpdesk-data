package org.esupportail.helpdesk.data.dao.services;

import fj.data.List;
import fj.data.Option;
import org.esupportail.helpdesk.data.dao.entities.HTicket;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

public interface ITicketService {

    List<HTicket> getTickets();

    Page<HTicket> getTickets(int page, int size, Sort sort);

    Option<HTicket> getTicketById(final Long id);

    List<HTicket> getOwnerTickets(String userId);

    Page<HTicket> getOwnerTickets(String userId, int page, int size, Sort sort);

    List<HTicket> getManagerTickets(String userId);

    Page<HTicket> getManagerTickets(String userId, int page, int size, Sort sort);

    List<HTicket> getCreatorTickets(String userId);

    Page<HTicket> getCreatorTickets(String userId, int page, int size, Sort sort);
}
