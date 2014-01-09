package org.esupportail.helpdesk.data.dao.services;

import fj.data.List;
import fj.data.Option;
import org.esupportail.helpdesk.data.dao.entities.HTicket;
import org.esupportail.helpdesk.data.dao.entities.HUser;

public interface ITicketService {

    List<HTicket> getTickets();

    Option<HTicket> getTicketById(final Long id);
}
