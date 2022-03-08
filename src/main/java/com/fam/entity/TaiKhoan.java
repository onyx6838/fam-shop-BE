package com.fam.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "taikhoan")
public class TaiKhoan implements Serializable {
    @Column(name = "MaTK")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int maDonDat;

    @Column(name = "MatKhau", length = 50)
    private String matKhau;

    @Column(name = "TenTK", unique = true, length = 50)
    private String tenTK;

    @Column(name = "HoTen", length = 50)
    private String hoTen;

    @Column(name = "Email", unique = true, length = 50)
    private String email;

    @Column(name = "SDT", unique = true, length = 20)
    private String sdt;

    @Column(name = "LoaiTK",length = 50)
    private String loaiTK;

}
