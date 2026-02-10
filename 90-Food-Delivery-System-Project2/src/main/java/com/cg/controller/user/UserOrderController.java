package com.cg.controller.user;

import com.cg.dto.CustomerDto;
import com.cg.dto.MenuItemDto;
import com.cg.dto.OrderDto;
import com.cg.enumeration.OrderStatus;
import com.cg.model.CartItem;
import com.cg.iservice.ICustomerService;
import com.cg.iservice.IMenuItemService;
import com.cg.iservice.IOrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.*;

@Controller
@RequestMapping("/user/orders")
public class UserOrderController {

    @Autowired
    private IOrderService orderService;

    @Autowired
    private ICustomerService customerService;

    @Autowired
    private IMenuItemService menuItemService;

    /* -------------------- PLACE ORDER -------------------- */
    @PostMapping("/place")
    public String placeOrder(
            @SessionAttribute("cart") Map<Long, CartItem> cart,
            @RequestParam("paymentMethod") String paymentMethod,
            Authentication auth) {

        // Logged-in customer (DTO)
        CustomerDto customer = customerService.getByEmail(auth.getName());

        List<Long> itemIds = new ArrayList<>();
        double total = 0;

        // Convert cart items into MenuItemDto → itemIds
        for (CartItem ci : cart.values()) {

            // Load fresh MenuItemDto from DB
            MenuItemDto item = menuItemService.getById(ci.getItem().getItemId());

            for (int i = 0; i < ci.getQuantity(); i++) {
                itemIds.add(item.getItemId());
            }

            total += ci.getSubtotal();
        }

        // Build OrderDto
        OrderDto orderDto = new OrderDto();
        orderDto.setCustomerId(customer.getCustomerId());
        orderDto.setOrderDate(LocalDateTime.now());
        orderDto.setOrderStatus(OrderStatus.PLACED);
        orderDto.setItemIds(itemIds);
        orderDto.setTotalAmount(total + 20); // Delivery fee

        // Place order
        orderService.place(orderDto);

        cart.clear(); // Clear session cart
        return "redirect:/user/orders/my-orders";
    }

    /* -------------------- LIST CUSTOMER ORDERS -------------------- */
    @GetMapping
    public String myOrders(Authentication auth, Model model) {
        CustomerDto customer = customerService.getByEmail(auth.getName());
        model.addAttribute("orders", orderService.getByCustomer(customer.getCustomerId()));
        return "user/orders";
    }

    /* -------------------- VIEW ORDER DETAILS -------------------- */
    @GetMapping("/{id}")
    public String orderDetails(@PathVariable Long id, Model model) {
        model.addAttribute("order", orderService.getById(id));
        return "user/order-details";
    }

    /* -------------------- CANCEL ORDER -------------------- */
    @GetMapping("/cancel/{id}")
    public String cancelOrder(@PathVariable Long id) {
        orderService.cancel(id);
        return "redirect:/user/orders";
    }

    /* -------------------- VIEW ORDERS BY EMAIL -------------------- */
    @GetMapping("/my-orders")
    public String viewMyOrders(Model model, Principal principal) {
        List<OrderDto> orders = orderService.getOrdersByCustomerEmail(principal.getName());
        model.addAttribute("orders", orders != null ? orders : Collections.emptyList());
        return "user/my-orders";
    }
}