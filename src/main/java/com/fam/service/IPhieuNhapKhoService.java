package com.fam.service;

import com.fam.entity.PhieuNhapKho;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author giangdm
 */
public interface IPhieuNhapKhoService {
    Page<PhieuNhapKho> getAllPNK(Pageable pageable);
}
