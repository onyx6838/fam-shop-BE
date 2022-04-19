package com.fam.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "CTDD", uniqueConstraints = @UniqueConstraint(columnNames = {"MaDonDat", "MaSP"}))
public class CTDD {
    @Column(name = "MaCTDDH")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int maCTDDH;

    @Column(name = "SoLuong")
    private int soLuong;

    @Column(name = "TongTienMuc")
    private int tongTienMuc;

    @Column(name = "GhiChu")
    private String ghiChu;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "MaDonDat")
    private DonDatHang donDatHang;

    @ManyToOne
    @JoinColumn(name = "MaSP")
    private SanPham sanPham;
}
