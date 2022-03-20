package com.fam.repository;

import com.fam.entity.DonDatHang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDonDatHangRepository extends JpaRepository<DonDatHang, Integer> {

}
