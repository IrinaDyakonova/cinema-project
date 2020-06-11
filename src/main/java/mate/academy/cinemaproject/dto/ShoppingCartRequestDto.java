package mate.academy.cinemaproject.dto;

import java.util.List;

public class ShoppingCartRequestDto {
    private List<Long> ticketId;
    private Long userId;

    public ShoppingCartRequestDto() {
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
