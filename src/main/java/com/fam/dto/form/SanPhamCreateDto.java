package com.fam.dto.form;

import lombok.Data;

/**
 * @author giangdm
 */
@Data
public class SanPhamCreateDto {
    private String ten;

    private String moTa;

    private double donGiaNhap;

    private double donGiaBan;

    private int soLuong;

    private int parentSP;

    private int childCategory;

    private int brand;
}
