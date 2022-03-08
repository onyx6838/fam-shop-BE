package com.fam.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "LoaiSanPham")
public class LoaiSanPham implements Serializable {
    @Column(name = "MaLoai")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int maLoai;

    @Column(name = "ten", length = 50)
    private String ten;

    @Column(name = "MoTa")
    private String moTa;

    @Column(name = "HinhAnh")
    private String hinhAnh;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "LoaiSPCha")
    @JsonBackReference
    private LoaiSanPham loaiSPCha;

    @JsonManagedReference
    @OneToMany(mappedBy = "loaiSPCha")
    private List<LoaiSanPham> loaiSPConList = new ArrayList<>();
}