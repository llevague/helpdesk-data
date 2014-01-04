package org.esupportail.helpdesk.data.dao.repositories;

import org.esupportail.helpdesk.data.dao.entities.HUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HUserRepository extends JpaRepository<HUser, String> {

    HUser findByRealId(String realId);
}
