package com.fam.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "PhieuNhapKho", uniqueConstraints = @UniqueConstraint(columnNames = {"MaTK2", "ThoiGian"}))
public class PhieuNhapKho implements Serializable {
    @Column(name = "MaPNK")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int maPNK;

    @Column(name = "LoaiPhieu", length = 50)
    private String loaiPhieu;

    @Column(name = "TongTien")
    private int tongTien;

    @Column(name = "ThoiGian")
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date thoiGian;

    @ManyToOne
    @JoinColumn(name = "MaTK1")
    private TaiKhoan nhanVien;

    @ManyToOne
    @JoinColumn(name = "MaTK2")
    private TaiKhoan nhaCungCap;
}
