package com.fam.dto.cart;

import lombok.Data;

/**
 * @author giangdm
 */
@Data
public class AddToGioHangDto {
    private String email;

    private int maSP;

    private int qty;
}
