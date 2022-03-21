package com.fam.dto.payment;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
public class PaymentDto {
    private OrderDto order;

    private List<CartItemDto> cart;

    @Data
    @NoArgsConstructor
    public static class CartItemDto {
        private Integer maSP;

        private Integer donGiaBan;

        private Integer qty;
    }

    @Data
    @NoArgsConstructor
    public static class OrderDto {
        @NotNull
        private String name;

        @NotNull
        private String username;

        private String phone;

        private String email;

        @NotNull
        private String address;

        private String shipAddress;

        private Date dateDelivery;

        private String paymentType;

        @NotNull
        private Integer totalPrice;
    }
}
