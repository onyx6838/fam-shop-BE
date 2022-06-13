package com.fam.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

/**
 * @author giangdm
 */
@Data
@Entity
@Table(name = "BaiViet")
public class BaiViet {
    @Column(name = "MaBaiViet")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int maBaiViet;

    @Column(name = "TieuDe")
    private String tieuDe;

    @Column(name = "MoTaNgan")
    private String moTaNgan;

    @Column(name = "NoiDung")
    private String noiDung;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "ThoiGianTao")
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date thoiGianTao;

    @ManyToOne
    @JoinColumn(name = "MaTheLoaiBaiViet")
    private TheLoaiBaiViet theLoaiBaiViet;

    @ManyToOne
    @JoinColumn(name = "TacGia")
    private TaiKhoan tacGia;
}
