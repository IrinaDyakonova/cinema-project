package mate.academy.cinemaproject.service;

import mate.academy.cinemaproject.model.Role;

public interface RoleService {

    Role add(Role role);

    Role getRoleByName(String roleName);

}
