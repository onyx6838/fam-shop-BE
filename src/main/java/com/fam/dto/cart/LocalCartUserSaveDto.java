package com.fam.dto.cart;

import lombok.Data;

import java.util.List;

/**
 * @author giangdm
 */
@Data
public class LocalCartUserSaveDto {
    private String tenTK;

    private List<CartItemDto> cart;
}
