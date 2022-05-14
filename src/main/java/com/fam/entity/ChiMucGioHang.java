package com.fam.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;

/**
 * @author giangdm
 */
@Entity
@Table(name = "ChiMucGioHang")
@Data
public class ChiMucGioHang {
    @Column(name = "MaChiMuc")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int maChiMuc;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "MaGioHang")
    private GioHang gioHang;

    @Column(name = "SoLuong")
    private int soLuong;

    @ManyToOne
    @JoinColumn(name = "MaSP")
    private SanPham sanPham;
}
