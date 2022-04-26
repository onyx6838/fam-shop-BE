package com.fam.service.impl;

import com.fam.entity.PhieuNhapKho;
import com.fam.repository.IPhieuNhapKhoRepository;
import com.fam.service.IPhieuNhapKhoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * @author giangdm
 */
@Service
public class PhieuNhapKhoService implements IPhieuNhapKhoService {
    @Autowired
    private IPhieuNhapKhoRepository phieuNhapKhoRepository;

    @Override
    public Page<PhieuNhapKho> getAllPNK(Pageable pageable) {
        return phieuNhapKhoRepository.findAll(pageable);
    }
}
