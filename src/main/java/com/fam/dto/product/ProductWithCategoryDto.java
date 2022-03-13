package com.fam.dto.product;

import lombok.Data;

@Data
public class ProductWithCategoryDto {
    private int maSP;

    private String ten;

    private String hinhAnh;

    private String moTa;

    private int soLuong;

    private int donGiaNhap;

    private int donGiaBan;
}
