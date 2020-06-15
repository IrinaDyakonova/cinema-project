package mate.academy.cinemaproject.mapper;

import java.util.stream.Collectors;
import mate.academy.cinemaproject.dto.ShoppingCartResponseDto;
import mate.academy.cinemaproject.model.ShoppingCart;
import mate.academy.cinemaproject.model.Ticket;
import org.springframework.stereotype.Component;

@Component
public class ShoppingCartMapper {

    public ShoppingCartResponseDto toDto(ShoppingCart shoppingCart) {
        ShoppingCartResponseDto shoppingCartResponseDto = new ShoppingCartResponseDto();
        shoppingCartResponseDto.setId(shoppingCart.getId());
        shoppingCartResponseDto
                .setTicketId(shoppingCart
                        .getTickets()
                        .stream()
                        .map(Ticket::getId)
                        .collect(Collectors.toList()));
        shoppingCartResponseDto.setUserId(shoppingCart.getUser().getId());
        return shoppingCartResponseDto;
    }
}
