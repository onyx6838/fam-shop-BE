package com.fam.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "ThuongHieu")
public class ThuongHieu {
    @Column(name = "MaThuongHieu")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int maThuongHieu;

    @Column(name = "TenThuongHieu")
    private String tenThuongHieu;

    @JsonBackReference
    @OneToMany(mappedBy = "thuongHieu")
    private List<SanPham> sanPhamList;
}
