package com.fam.dto.payment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author giangdm
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PrecinctDto {
    private int id;
    private String name;
    private int districtID;
}