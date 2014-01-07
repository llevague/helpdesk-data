package org.esupportail.helpdesk.data.dao.repositories;

import org.esupportail.helpdesk.data.dao.entities.HTicket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HTicketRepository extends JpaRepository<HTicket, Long> {

}
