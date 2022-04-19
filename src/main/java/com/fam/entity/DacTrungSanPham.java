package com.fam.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "DacTrungSanPham")
public class DacTrungSanPham implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MaDacTrungSP")
    private int MaDacTrungSP;

    @ManyToOne
    @JoinColumn(name = "MaSP")
    private SanPham sanPham;

    @ManyToOne
    @JoinColumn(name = "MaDacTrung")
    private DacTrung dacTrung;
}
