package com.fam.entity;

import com.fam.entity.enumerate.TrangThaiBinhLuan;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author giangdm
 */
@Getter
@Setter
@Entity
@Table(name = "DanhGia")
public class DanhGia implements Serializable {
    @Column(name = "MaDanhGia")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int maDanhGia;

    @Column(name = "NoiDung")
    private String noiDung;

    @Column(name = "TenNguoiDanhGia")
    private String tenNguoiDanhGia;

    @Column(name = "EmailNguoiDanhGia")
    private String emailNguoiDanhGia;

    @Column(name = "SDTNguoiDanhGia")
    private String sdtNguoiDanhGia;

    @Enumerated(EnumType.STRING)
    @Column(name = "TrangThai")
    private TrangThaiBinhLuan trangThai;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "NgayTaoDanhGia")
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date ngayTaoDanhGia;

    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "MaSP")
    private SanPham sanPham;

    @ManyToOne
    @JoinColumn(name = "DanhGiaCha")
    //@JsonManagedReference
    private DanhGia danhGiaCha;

    @Column(name = "QuanTriVien")
    private Boolean quanTriVien;

//    @JsonBackReference
//    @OneToMany(mappedBy = "danhGiaCha")
//    private List<DanhGia> dGiaConList = new ArrayList<>();
}
