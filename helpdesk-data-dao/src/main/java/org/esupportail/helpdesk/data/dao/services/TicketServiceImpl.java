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

    @Override
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public Option<String> getTicketOwner(final Long id) {
        return join(getTicketById(id).map(new F<HTicket, Option<String>>() {
            public Option<String> f(HTicket hTicket) {
                return fromNull(hTicket.getOwner()).map(new F<HUser, String>() {
                    public String f(HUser hUser) {
                        return hUser.getId();
                    }
                });
            }
        }));
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public Option<String> getTicketManager(final Long id) {
        return join(getTicketById(id).map(new F<HTicket, Option<String>>() {
            public Option<String> f(HTicket hTicket) {
                return fromNull(hTicket.getManager()).map(new F<HUser, String>() {
                    public String f(HUser hUser) {
                        return hUser.getId();
                    }
                });
            }
        }));
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public Option<String> getTicketCreator(final Long id) {
        return join(getTicketById(id).map(new F<HTicket, Option<String>>() {
            public Option<String> f(HTicket hTicket) {
                return fromNull(hTicket.getCreator()).map(new F<HUser, String>() {
                    public String f(HUser hUser) {
                        return hUser.getId();
                    }
                });
            }
        }));
    }
}
