package com.fam.dto.order;

import lombok.Data;

/**
 * @author giangdm
 */
@Data
public class OrderStatusChangeDto {
    private int id; // id đơn hàng thay đổi trạng thái

    private int orderStatus;

    private int paymentType;
}
