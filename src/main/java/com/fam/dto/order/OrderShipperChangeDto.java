package com.fam.dto.order;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author giangdm
 */
@Data
public class OrderShipperChangeDto {
    @JsonProperty("ma-shipper")
    private int maTK;

    private int maDonDat;
}
