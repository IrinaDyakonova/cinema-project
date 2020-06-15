package mate.academy.cinemaproject.dao;

import mate.academy.cinemaproject.model.Ticket;

public interface TicketDao {

    Ticket add(Ticket ticket);

    Ticket findById(Long id);
}
