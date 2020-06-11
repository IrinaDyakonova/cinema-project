package mate.academy.cinemaproject.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import mate.academy.cinemaproject.dao.OrderDao;
import mate.academy.cinemaproject.model.Order;
import mate.academy.cinemaproject.model.Ticket;
import mate.academy.cinemaproject.model.User;
import mate.academy.cinemaproject.service.OrderService;
import mate.academy.cinemaproject.service.ShoppingCartService;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

    private OrderDao orderDao;
    private ShoppingCartService shoppingCartService;

    public OrderServiceImpl(OrderDao orderDao, ShoppingCartService shoppingCartService) {
        this.orderDao = orderDao;
        this.shoppingCartService = shoppingCartService;
    }

    @Override
    public Order completeOrder(List<Ticket> tickets, User user) {
        List<Ticket> ticketList = new ArrayList<>(tickets);
        Order order = new Order(ticketList, user);
        order.setOrderDate(LocalDateTime.now());
        shoppingCartService.clear(shoppingCartService.getByUser(user));
        return orderDao.add(order);
    }

    @Override
    public List<Order> getOrderHistory(User user) {
        return orderDao.getOrderHistory(user);
    }

    @Override
    public Order findById(Long id) {
        return orderDao.findById(id);
    }
}
