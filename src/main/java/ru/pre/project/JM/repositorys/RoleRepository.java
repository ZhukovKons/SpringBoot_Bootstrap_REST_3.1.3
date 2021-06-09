package ru.pre.project.JM.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.pre.project.JM.models.Role;

@Repository
//public interface RoleRepository extends CrudRepository <Role, Long> {
public interface RoleRepository extends JpaRepository<Role, Long> {
    //Role findRoleByRole(String role); //todo
}
