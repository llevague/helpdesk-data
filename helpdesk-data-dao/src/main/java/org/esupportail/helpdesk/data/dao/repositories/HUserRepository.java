package org.esupportail.helpdesk.data.dao.repositories;

import org.esupportail.helpdesk.data.dao.entities.HUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

public interface HUserRepository extends JpaRepository<HUser, String>, QueryDslPredicateExecutor<HUser> {

    HUser findByRealId(String realId);
}
