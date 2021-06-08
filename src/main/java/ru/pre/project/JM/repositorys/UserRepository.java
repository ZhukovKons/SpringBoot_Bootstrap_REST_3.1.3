package ru.pre.project.JM.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.pre.project.JM.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findUserByName(String name);
}