package by.varaksa.webproject.repository;

import by.varaksa.webproject.entity.UserRole;

public interface UserRoleRepository extends CrudRepository<Long, UserRole> {
    UserRole find(Long id);

    UserRole save(UserRole userRole);

    UserRole update(Long id);

    UserRole delete(Long id);
}
