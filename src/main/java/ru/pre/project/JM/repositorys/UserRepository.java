package ru.pre.project.JM.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.pre.project.JM.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findUserByEmail(String name);
}
