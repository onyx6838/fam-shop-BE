package com.fam.dto.product;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author giangdm
 */
@Data
public class ParentCategory {
    private int maLoai;

    private String ten;

    private LoaiSanPham loaiSPCha;

    @Data
    @NoArgsConstructor
    public static class LoaiSanPham {
        private int maLoai;

        private String ten;
    }
}
