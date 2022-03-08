package com.fam.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "DacTrung", uniqueConstraints = @UniqueConstraint(columnNames = {"LoaiDacTrung", "ThuTu"}))
public class DacTrung {
    @Column(name = "MaDacTrung")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int maDacTrung;

    @Column(name = "LoaiDacTrung", length = 50)
    private String LoaiDacTrung;

    @Column(name = "ThuTu")
    private int thuTu;

    @Column(name = "GiaTri")
    private String giaTri;

    @Column(name = "DonVi")
    private String donVi;

    @Column(name = "Ten")
    private String ten;

    @Column(name = "MoTa")
    private String moTa;

    @JsonBackReference
    @OneToMany(mappedBy = "dacTrung")
    private List<DacTrungSanPham> listDacTrungSP;
}
