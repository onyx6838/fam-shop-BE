package com.fam.repository;

import com.fam.entity.DacTrungSanPham;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IDacTrungSanPhamRepository extends JpaRepository<DacTrungSanPham, Integer>,
        JpaSpecificationExecutor<DacTrungSanPham> {

    @Query(value = "SELECT DISTINCT (dtsp.sanPham) FROM DacTrungSanPham dtsp WHERE dtsp.dacTrung.maDacTrung IN :dacTrungList")
    Page<DacTrungSanPham> findByDacTrungs(@Param("dacTrungList") List<Integer> dacTrungs, Pageable pageable);
}
