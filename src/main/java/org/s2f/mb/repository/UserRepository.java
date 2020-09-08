package org.s2f.mb.repository;

import org.s2f.mb.model.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, String> {

    User findByLogin(String name);

    User findByUuid(String uuid);

    User save(User user);

    boolean existsByLogin(String login);

}
