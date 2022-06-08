package com.fam.dto.form;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * @author giangdm
 */
@Data
public class PNKCreateDto {
    private ReceiptDto receipt;

    private List<ReceiptProductItem> ctpnks;

    @Data
    @NoArgsConstructor
    public static class ReceiptDto {
        private String tenTKQuanTri;

        private String loaiPhieu;
    }

    @Data
    @NoArgsConstructor
    public static class ReceiptProductItem {
        private int maSP;

        private Integer soLuong;

        private Integer tongTienMuc;

        private Date hanSuDung;

        private Integer donGiaNhap;
    }
}
