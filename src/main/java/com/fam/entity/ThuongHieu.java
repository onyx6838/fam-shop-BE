package com.fam.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "ThuongHieu")
public class ThuongHieu {
    @Column(name = "MaThuongHieu")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int maThuongHieu;

    @Column(name = "TenThuongHieu")
    private String tenThuongHieu;

    @Column(name = "HinhAnh", length = 800)
    private String hinhAnh;

    @JsonBackReference
    @OneToMany(mappedBy = "thuongHieu")
    private List<SanPham> sanPhamList;
}
