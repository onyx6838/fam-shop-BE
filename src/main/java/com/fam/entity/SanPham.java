package com.fam.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "SanPham")
public class SanPham implements Serializable {
    @Column(name = "MaSP")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int maSP;

    @Column(name = "Ten", length = 50)
    private String ten;

    @Column(name = "HinhAnh", length = 800)
    private String hinhAnh;

    @Column(name = "MoTa")
    private String moTa;

    @Column(name = "SoLuong")
    private int soLuong;

    @Column(name = "DonGiaNhap")
    private int donGiaNhap;

    @Column(name = "DonGiaBan")
    private int donGiaBan;

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

//    @ManyToOne(cascade = {CascadeType.ALL})
//    @JoinColumn(name = "SPCha")
//    @JsonManagedReference
//    private SanPham spCha;
//
//    @OneToMany(mappedBy = "spCha")
//    @JsonBackReference
//    private Set<SanPham> SPConList = new HashSet<>();
}
