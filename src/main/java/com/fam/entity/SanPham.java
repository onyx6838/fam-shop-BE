package com.fam.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "SanPham")
public class SanPham implements Serializable {
    @Column(name = "MaSP")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int maSP;

    @Column(name = "Ten")
    private String ten;

    @Column(name = "HinhAnh", length = 800)
    private String hinhAnh;

    @Column(name = "MoTa")
    private String moTa;

    @Column(name = "SoLuong")
    private int soLuong;

    @Column(name = "DonGiaNhap")
    private double donGiaNhap;

    @Column(name = "DonGiaBan")
    private double donGiaBan;

    @Column(name = "TrangThai")
    private short trangThai;

    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "MaThuongHieu")
    private ThuongHieu thuongHieu;

    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "LoaiSP")
    private LoaiSanPham loaiSanPham;

    @JsonBackReference
    @OneToMany(mappedBy = "sanPham")
    private List<DacTrungSanPham> listSPDacTrung;

    @JsonManagedReference
    @OneToMany(mappedBy = "sanPham")
    private List<SanPhamFile> sanPhamFiles;

    @ManyToOne
    @JoinColumn(name = "SPCha")
    @JsonManagedReference
    private SanPham spCha;

    @JsonBackReference
    @OneToMany(mappedBy = "spCha")
    private List<SanPham> spConList = new ArrayList<>();
}
