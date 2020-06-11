package mate.academy.cinemaproject.controllers;

import java.util.List;
import java.util.stream.Collectors;
import mate.academy.cinemaproject.dto.OrderRequestDto;
import mate.academy.cinemaproject.dto.OrderResponseDto;
import mate.academy.cinemaproject.dto.ShoppingCartResponseDto;
import mate.academy.cinemaproject.model.Order;
import mate.academy.cinemaproject.model.ShoppingCart;
import mate.academy.cinemaproject.model.User;
import mate.academy.cinemaproject.service.OrderService;
import mate.academy.cinemaproject.service.ShoppingCartService;
import mate.academy.cinemaproject.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final UserService userService;
    private final OrderService orderService;
    private final ShoppingCartService shoppingCartService;



    public OrderController(UserService userService, OrderService orderService, ShoppingCartService shoppingCartService) {
        this.userService = userService;
        this.orderService = orderService;
        this.shoppingCartService = shoppingCartService;
    }


    /*
    Complete order - POST: /orders/complete
Get orders history for user - GET: /orders?userId
     */

    @PostMapping("/complete")
    public String completeOrder(@RequestBody OrderRequestDto orderRequestDto) {
        User user = userService.findById(orderRequestDto.getUserId());
        ShoppingCart shoppingCart = shoppingCartService.getByUser(user);
        orderService.completeOrder(shoppingCart.getTickets(),user);
        return "Complete order creat";

    }

    @GetMapping
    public List<OrderResponseDto> getByUserId(@RequestParam Long userId) {
        return orderService.getOrderHistory(userService.findById(userId)).stream().map(order -> prepare(order)).collect(Collectors.toList());
    }

    private OrderResponseDto prepare(Order order) {
        OrderResponseDto orderResponseDto = new OrderResponseDto();
        orderResponseDto.setId(order.getId());
        orderResponseDto.setTicketId(order.getTickets().stream().map(t->t.getId()).collect(Collectors.toList()));
        orderResponseDto.setUserId(order.getUser().getId());
        orderResponseDto.setOrderDate(order.getOrderDate());
        return orderResponseDto;
    }
}
