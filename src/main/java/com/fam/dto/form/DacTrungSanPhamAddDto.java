package com.fam.dto.form;

import lombok.Data;

import java.util.List;

/**
 * @author giangdm
 */
@Data
public class DacTrungSanPhamAddDto {
    private int maSP;

    private List<Integer> dacTrungs;
}
