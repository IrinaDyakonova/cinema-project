package mate.academy.cinemaproject.dao;

import mate.academy.cinemaproject.model.ShoppingCart;
import mate.academy.cinemaproject.model.User;

public interface ShoppingCartDao {

    ShoppingCart add(ShoppingCart shoppingCart);

    ShoppingCart getByUser(User user);

    void update(ShoppingCart shoppingCart);

    ShoppingCart findById(Long id);

}
