package com.fam.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "ChiTietPNK", uniqueConstraints = @UniqueConstraint(columnNames = {"MaPNK", "MaSP"}))
public class ChiTietPNK {
    @Column(name = "MaCTPNK")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int maCTPNK;

    @Column(name = "MaLoHang")
    private Integer maLoHang;

    @Column(name = "DonVi")
    private String donVi;

    @Column(name = "SoLuong")
    private Integer soLuong;

    @Column(name = "SoThung")
    private Integer soThung;

    @Column(name = "TongTienMuc")
    private Integer tongTienMuc;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "HanSuDung")
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date hanSuDung;

    @Column(name = "GhiChu")
    private String ghiChu;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "MaPNK")
    private PhieuNhapKho phieuNhapKho;

    @ManyToOne
    @JoinColumn(name = "MaSP")
    private SanPham sanPham;

    @ManyToOne
    @JoinColumn(name = "CTDD")
    private CTDD ctdd;
}
