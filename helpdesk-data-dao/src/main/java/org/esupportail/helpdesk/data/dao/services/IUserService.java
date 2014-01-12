package org.esupportail.helpdesk.data.dao.services;

import fj.data.List;
import fj.data.Option;
import org.esupportail.helpdesk.data.dao.entities.HUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;


public interface IUserService {

    List<HUser> getUsers();

    Page<HUser> getUsers(int page, int size, Sort sort);

    Option<HUser> getUserById(final String id);

    Option<HUser> getUserByRealId(final String realId);
}
