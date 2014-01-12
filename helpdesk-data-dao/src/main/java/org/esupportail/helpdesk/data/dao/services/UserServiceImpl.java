package org.esupportail.helpdesk.data.dao.services;

import fj.F;
import fj.data.List;
import fj.data.Option;
import org.esupportail.helpdesk.data.dao.entities.HTicket;
import org.esupportail.helpdesk.data.dao.entities.HUser;
import org.esupportail.helpdesk.data.dao.repositories.HUserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

import static fj.data.List.iterableList;
import static fj.data.Option.fromNull;

@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public class UserServiceImpl implements IUserService {

    @Inject
    private HUserRepository userRepository;

    @Override
    public List<HUser> getUsers() {
        return iterableList(userRepository.findAll());
    }

    @Override
    public Page<HUser> getUsers(final int page, final int size, final Sort sort) {
        return userRepository.findAll(new PageRequest(page, size, sort));
    }

    @Override
    public Option<HUser> getUserById(final String id) {
        return fromNull(userRepository.findOne(id));
    }

    @Override
    public Option<HUser> getUserByRealId(final String realId) {
        return fromNull(userRepository.findByRealId(realId));
    }


}
