package mate.academy.cinemaproject.dao;

import mate.academy.cinemaproject.model.Ticket;
import mate.academy.cinemaproject.model.User;

public interface TicketDao {

    Ticket add(Ticket ticket);

    Ticket findById(Long id);


}
