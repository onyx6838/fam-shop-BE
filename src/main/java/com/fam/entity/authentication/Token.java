package com.fam.entity.authentication;

import com.fam.entity.TaiKhoan;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table
@Data
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "Type", discriminatorType = DiscriminatorType.STRING)
public class Token {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    protected int id;

    @Column(name = "Token", nullable = false, unique = true)
    protected String token;

    @OneToOne
    @JoinColumn(name = "MaTK", nullable = false)
    protected TaiKhoan taiKhoan;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "ExpiredDate", nullable = false)
    protected Date expiredDate;

    public Token(String token, TaiKhoan taiKhoan, long expiredTime) {
        this.token = token;
        this.taiKhoan = taiKhoan;
        expiredDate = new Date(System.currentTimeMillis() + expiredTime);
    }
}
