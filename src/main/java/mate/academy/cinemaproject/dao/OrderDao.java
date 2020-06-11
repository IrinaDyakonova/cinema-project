package mate.academy.cinemaproject.dao;

import java.util.List;
import mate.academy.cinemaproject.model.Order;
import mate.academy.cinemaproject.model.ShoppingCart;
import mate.academy.cinemaproject.model.User;

public interface OrderDao {
    Order add(Order order);

    List<Order> getOrderHistory(User user);

    Order findById(Long id);

}
