package com.fam.entity;

import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "DonDatHang", uniqueConstraints = @UniqueConstraint(columnNames = {"MaTK", "ThoiGianDat"}))
public class DonDatHang implements Serializable {
    @Column(name = "MaDonDat")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int maDonDat;

    @Column(name = "TrangThai", length = 50)
    private String trangThai;

    @Column(name = "TrangThaiTToan", length = 50)
    private String trangThaiTToan;

    @Column(name = "TongTien")
    private int tongTien;

    @Column(name = "ThoiGianDat")
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date thoiGianDat;

    @Column(name = "ThoiGianNhanHang")
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date thoiGianNhanHang;

    @Column(name = "DiaChi", length = 50)
    private String diaChi;

    @Column(name = "SDTNhanHang", length = 50)
    private String sdtNhanHang;

    @ManyToOne
    @JoinColumn(name = "MaTK")
    private TaiKhoan khachHang;

    @ManyToOne
    @JoinColumn(name = "MaNhanVien")
    private TaiKhoan nhanVien;
}
