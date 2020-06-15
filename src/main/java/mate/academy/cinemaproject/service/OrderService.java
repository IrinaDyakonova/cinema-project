package mate.academy.cinemaproject.service;

import java.util.List;
import mate.academy.cinemaproject.model.Order;
import mate.academy.cinemaproject.model.Ticket;
import mate.academy.cinemaproject.model.User;

public interface OrderService {

    Order completeOrder(List<Ticket> tickets, User user);

    List<Order> getOrderHistory(User user);

    Order findById(Long id);
}
