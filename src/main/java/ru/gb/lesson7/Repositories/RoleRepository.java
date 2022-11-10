package ru.gb.lesson7.Repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.gb.lesson7.Entities.Role;

@Repository
public interface RoleRepository extends CrudRepository<Role, Integer> {
}
