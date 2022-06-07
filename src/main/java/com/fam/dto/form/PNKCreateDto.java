package com.fam.dto.form;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author giangdm
 */
@Data
public class PNKCreateDto {
    private ReceiptDto order;

    private List<ReceiptProductItem> cart;

    @Data
    @NoArgsConstructor
    public static class ReceiptDto {
        private int customerId;

        private String username;

        private Double totalPrice;
    }

    @Data
    @NoArgsConstructor
    public static class ReceiptProductItem {
        private int id;

        private Integer quantity;
    }
}
