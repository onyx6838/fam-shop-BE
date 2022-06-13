package com.fam.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

/**
 * @author giangdm
 */
@Entity
@Table(name = "TheLoaiBaiViet")
@Data
public class TheLoaiBaiViet {
    @Column(name = "MaTheLoaiBaiViet")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int maTheLoaiBaiViet;

    @Column(name = "TenTheLoaiBaiViet")
    private String tenTheLoaiBaiViet;

    @Column(name = "DuongDan")
    private String duongDan;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "ThoiGianTao")
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date thoiGianTao;
}
