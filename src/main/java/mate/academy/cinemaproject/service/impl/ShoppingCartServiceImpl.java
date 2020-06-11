package mate.academy.cinemaproject.service.impl;

import mate.academy.cinemaproject.dao.ShoppingCartDao;
import mate.academy.cinemaproject.dao.TicketDao;
import mate.academy.cinemaproject.model.MovieSession;
import mate.academy.cinemaproject.model.ShoppingCart;
import mate.academy.cinemaproject.model.Ticket;
import mate.academy.cinemaproject.model.User;
import mate.academy.cinemaproject.service.ShoppingCartService;
import org.springframework.stereotype.Service;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
    private ShoppingCartDao shoppingCartDao;
    private TicketDao ticketDao;

    public ShoppingCartServiceImpl(ShoppingCartDao shoppingCartDao, TicketDao ticketDao) {
        this.shoppingCartDao = shoppingCartDao;
        this.ticketDao = ticketDao;
    }

    @Override
    public void addSession(MovieSession movieSession, User user) {
        Ticket ticket = new Ticket();
        ticket.setMovieSession(movieSession);
        ticket.setUser(user);
        Ticket ticketDB = ticketDao.add(ticket);
        ShoppingCart shoppingCart = getByUser(user);
        shoppingCart.getTickets().add(ticketDB);
        shoppingCartDao.update(shoppingCart);
    }

    @Override
    public ShoppingCart getByUser(User user) {
        return shoppingCartDao.getByUser(user);
    }

    @Override
    public void registerNewShoppingCart(User user) {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setUser(user);
        shoppingCartDao.add(shoppingCart);
        return;
    }

    @Override
    public void clear(ShoppingCart shoppingCart) {
        shoppingCart.getTickets().clear();
        shoppingCartDao.update(shoppingCart);
    }

    @Override
    public ShoppingCart findById(Long id) {
        return shoppingCartDao.findById(id);
    }
}
