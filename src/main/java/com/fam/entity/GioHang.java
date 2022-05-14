package com.fam.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

/**
 * @author giangdm
 */
@Entity
@Table(name = "GioHang")
@Getter
@Setter
public class GioHang {
    @Column(name = "MaGioHang")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int maGioHang;

    @ManyToOne
    @JoinColumn(name = "MaTK")
    private TaiKhoan khachHang;

    @Column(name = "TongTien")
    private Double tongTien;

    @JsonManagedReference
    @OneToMany(mappedBy = "gioHang")
    private List<ChiMucGioHang> chiMucGioHangList;
}
