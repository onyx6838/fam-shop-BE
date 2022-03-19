package com.fam.repository.authentication;

import com.fam.entity.authentication.ResetPasswordToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Repository
public interface IResetPasswordTokenRepository extends JpaRepository<ResetPasswordToken, Integer> {
    ResetPasswordToken findByToken(String token);

    boolean existsByToken(String token);

    boolean existsByTokenAndExpiredDateGreaterThan(String token, Date expiredDate);

    @Transactional
    @Modifying
    @Query("	DELETE 						"
            + "	FROM 	ResetPasswordToken 	"
            + " WHERE 	taiKhoan.maTK = :accountId")
    void deleteByUserId(int accountId);
}
