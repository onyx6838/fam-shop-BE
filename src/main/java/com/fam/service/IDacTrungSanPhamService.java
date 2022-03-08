package com.fam.service;

import com.fam.entity.DacTrungSanPham;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IDacTrungSanPhamService {
    Page<DacTrungSanPham> findByDacTrungs(List<Integer> dacTrungs, Pageable pageable);
}
