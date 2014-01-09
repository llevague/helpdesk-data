package org.esupportail.helpdesk.data.dao.services;

import fj.F;
import fj.data.List;
import fj.data.Option;
import org.esupportail.helpdesk.data.dao.entities.HTicket;
import org.esupportail.helpdesk.data.dao.entities.HUser;
import org.esupportail.helpdesk.data.dao.repositories.HTicketRepository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

import static fj.data.List.iterableList;
import static fj.data.Option.*;

@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public class TicketServiceImpl implements ITicketService {

    @Inject
    private HTicketRepository ticketRepository;

    @Override
    public List<HTicket> getTickets() {
        return iterableList(ticketRepository.findAll());
    }

    @Override
    public Option<HTicket> getTicketById(final Long id) {
        return fromNull(ticketRepository.findOne(id));
    }
}
