package ru.pre.project.JM.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.pre.project.JM.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}
