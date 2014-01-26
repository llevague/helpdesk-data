package org.esupportail.helpdesk.data.dao.repositories;

import org.esupportail.helpdesk.data.dao.entities.HTicket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

public interface HTicketRepository extends JpaRepository<HTicket, Long>, QueryDslPredicateExecutor<HTicket> {

}
