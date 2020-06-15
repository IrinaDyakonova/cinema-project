package mate.academy.cinemaproject.mapper;

import java.util.stream.Collectors;
import mate.academy.cinemaproject.dto.OrderResponseDto;
import mate.academy.cinemaproject.model.Order;
import mate.academy.cinemaproject.model.Ticket;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {

    public OrderResponseDto toDto(Order order) {
        OrderResponseDto orderResponseDto = new OrderResponseDto();
        orderResponseDto.setId(order.getId());
        orderResponseDto
                .setTicketId(order
                        .getTickets()
                        .stream()
                        .map(Ticket::getId)
                        .collect(Collectors.toList()));
        orderResponseDto.setUserId(order.getUser().getId());
        orderResponseDto.setOrderDate(order.getOrderDate());
        return orderResponseDto;
    }
}
