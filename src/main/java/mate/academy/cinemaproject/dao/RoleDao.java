package mate.academy.cinemaproject.dao;

import mate.academy.cinemaproject.model.Role;

public interface RoleDao {

    Role add(Role role);

    Role getRoleByName(String roleName);
}
