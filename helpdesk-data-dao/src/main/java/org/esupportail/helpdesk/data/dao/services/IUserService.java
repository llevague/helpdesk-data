package org.esupportail.helpdesk.data.dao.services;

import fj.data.List;
import fj.data.Option;
import org.esupportail.helpdesk.data.dao.entities.HTicket;
import org.esupportail.helpdesk.data.dao.entities.HUser;


public interface IUserService {

    List<HUser> getUsers();

    Option<HUser> getUserById(final String id);

    Option<HUser> getUserByRealId(final String realId);
}
