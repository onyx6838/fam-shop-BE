package com.fam.entity;

import com.fam.entity.enumerate.HinhThucTToan;
import com.fam.entity.enumerate.TrangThaiDonDat;
import com.fam.entity.enumerate.TrangThaiTToan;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "DonDatHang", uniqueConstraints = @UniqueConstraint(columnNames = {"MaTK", "ThoiGianDat"}))
public class DonDatHang implements Serializable {
    @Column(name = "MaDonDat")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int maDonDat;

    @Enumerated(EnumType.STRING)
    @Column(name = "TrangThai")
    private TrangThaiDonDat trangThai;

    @Enumerated(EnumType.STRING)
    @Column(name = "HinhThucTToan")
    private HinhThucTToan hinhThucTToan;

    @Enumerated(EnumType.STRING)
    @Column(name = "TrangThaiTToan")
    private TrangThaiTToan trangThaiTToan;

    @Column(name = "TongTien")
    private Integer tongTien;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "ThoiGianDat")
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date thoiGianDat;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "ThoiGianNhanHang")
    @Temporal(TemporalType.TIMESTAMP)
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

    @JsonManagedReference
    @OneToMany(mappedBy = "donDatHang")
    private List<CTDD> listCTDD;
}
