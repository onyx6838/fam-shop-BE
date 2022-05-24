package com.fam.dto.form;

import lombok.Data;

/**
 * @author giangdm
 */
@Data
public class DanhGiaCreate {
    private String tenNguoiDanhGia;

    private String emailNguoiDanhGia;

    private String sdtNguoiDanhGia;

    private String noiDung;

    private int maSPDanhGia;

    private int maDanhGiaCha;
}
