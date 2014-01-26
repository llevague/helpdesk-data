package org.esupportail.helpdesk.data.dao.services;

import com.mysema.query.types.expr.BooleanExpression;
import fj.data.List;
import fj.data.Option;
import org.esupportail.helpdesk.data.dao.entities.HTicket;
import org.esupportail.helpdesk.data.dao.entities.QHTicket;
import org.esupportail.helpdesk.data.dao.repositories.HTicketRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

import static fj.data.List.iterableList;
import static fj.data.Option.*;

@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public class TicketServiceImpl implements ITicketService {

    @Inject
    private HTicketRepository ticketRepository;

    private final QHTicket hTicket = QHTicket.hTicket;

    @Override
    public List<HTicket> getTickets() {
        return iterableList(ticketRepository.findAll());
    }

    @Override
    public Page<HTicket> getTickets(int page, int size, Sort sort) {
        return ticketRepository.findAll(new PageRequest(page, size, sort));
    }

    @Override
    public Option<HTicket> getTicketById(final Long id) {
        return fromNull(ticketRepository.findOne(id));
    }

    @Override
    public List<HTicket> getOwnerTickets(final String userId) {
        final BooleanExpression isOwner = hTicket.owner.id.eq(userId);

        return iterableList(ticketRepository.findAll(isOwner));
    }

    @Override
    public Page<HTicket> getOwnerTickets(final String userId, final int page, final int size, final Sort sort) {
        final BooleanExpression isOwner = hTicket.manager.id.eq(userId);

        return ticketRepository.findAll(isOwner, new PageRequest(page, size, sort));
    }

    @Override
    public List<HTicket> getManagerTickets(final String userId) {
        final BooleanExpression isManager = hTicket.manager.id.eq(userId);

        return iterableList(ticketRepository.findAll(isManager));
    }

    @Override
    public Page<HTicket> getManagerTickets(final String userId, final int page, final int size, final Sort sort) {
        final BooleanExpression isManager = hTicket.owner.id.eq(userId);

        return ticketRepository.findAll(isManager, new PageRequest(page, size, sort));
    }

    @Override
    public List<HTicket> getCreatorTickets(final String userId) {
        final BooleanExpression isCreator = hTicket.creator.id.eq(userId);

        return iterableList(ticketRepository.findAll(isCreator));
    }

    @Override
    public Page<HTicket> getCreatorTickets(final String userId, final int page, final int size, final Sort sort) {
        final BooleanExpression isCreator = hTicket.creator.id.eq(userId);

        return ticketRepository.findAll(isCreator, new PageRequest(page, size, sort));
    }
}
