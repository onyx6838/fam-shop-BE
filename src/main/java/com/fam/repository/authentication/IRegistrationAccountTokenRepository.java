package com.fam.repository.authentication;

import com.fam.entity.authentication.RegistrationUserToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
public interface IRegistrationAccountTokenRepository extends JpaRepository<RegistrationUserToken, Integer> {
    RegistrationUserToken findByToken(String token);

    boolean existsByToken(String token);


    @Query("	SELECT 	token	"
            + "	FROM 	RegistrationUserToken "
            + " WHERE 	taiKhoan.maTK = :accountId")
    String findByAccountId(int accountId);

    @Transactional
    @Modifying
    @Query("	DELETE 							"
            + "	FROM 	RegistrationUserToken 	"
            + " WHERE 	taiKhoan.maTK = :accountId")
    void deleteByAccountId(int accountId);
}
