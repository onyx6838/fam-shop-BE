package com.fam.entity;

import com.fam.entity.enumerate.TrangThaiTK;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
    private int maTK;

    @JsonIgnore
    @Column(name = "MatKhau")
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

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "TrangThai", nullable = false)
    private TrangThaiTK trangThai = TrangThaiTK.NOT_ACTIVE;

}
