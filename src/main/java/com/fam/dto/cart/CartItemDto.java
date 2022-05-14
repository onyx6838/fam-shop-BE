package com.fam.dto.cart;

import lombok.Data;

/**
 * @author giangdm
 */
@Data
public class CartItemDto {
    private int maSP;

    private String ten;

    private String hinhAnh;

    private String moTa;

    private int donGiaBan;

    private int qty;
}
