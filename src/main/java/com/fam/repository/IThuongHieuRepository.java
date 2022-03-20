package com.fam.repository;

import com.fam.entity.ThuongHieu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IThuongHieuRepository extends JpaRepository<ThuongHieu, Integer> {
}
