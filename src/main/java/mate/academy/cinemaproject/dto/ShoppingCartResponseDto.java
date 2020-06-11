package mate.academy.cinemaproject.dto;

import java.util.List;

public class ShoppingCartResponseDto {
    private Long id;
    private List<Long> ticketId;
    private Long userId;

    public ShoppingCartResponseDto() {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Long> getTicketId() {
        return ticketId;
    }

    public void setTicketId(List<Long> ticketId) {
        this.ticketId = ticketId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
