package com.fam.dto.product;

import lombok.Data;

import javax.validation.constraints.Min;

@Data
public class CategoryDto {
    @Min(value = 0)
    private int maLoai;

    private String ten;

    private String moTa;

    private String hinhAnh;
}
