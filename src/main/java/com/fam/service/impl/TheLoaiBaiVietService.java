package com.fam.service.impl;

import com.fam.dto.form.TheLoaiBaiVietCreateDto;
import com.fam.entity.TheLoaiBaiViet;
import com.fam.repository.ITheLoaiBaiVietRepository;
import com.fam.service.ITheLoaiBaiVietService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author giangdm
 */
@Service
public class TheLoaiBaiVietService implements ITheLoaiBaiVietService {
    @Autowired
    private ITheLoaiBaiVietRepository theLoaiBaiVietRepository;

    @Override
    public List<TheLoaiBaiViet> getAllTheLoaiBaiVietWithoutPage() {
        return theLoaiBaiVietRepository.findAll();
    }

    @Override
    public Page<TheLoaiBaiViet> getAllTheLoaiBaiViet(Pageable pageable) {
        return theLoaiBaiVietRepository.findAll(pageable);
    }

    @Override
    public boolean createTheLoaiBaiViet(TheLoaiBaiVietCreateDto form) {
        try {
            TheLoaiBaiViet theLoaiBaiViet = new TheLoaiBaiViet();
            theLoaiBaiViet.setTenTheLoaiBaiViet(form.getTenTheLoaiBaiViet());
            theLoaiBaiViet.setDuongDan(form.getDuongDan());
            theLoaiBaiViet.setThoiGianTao(new Date());
            theLoaiBaiVietRepository.save(theLoaiBaiViet);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean updateTLBV(TheLoaiBaiVietCreateDto form, int idUpdate) {
        try {
            TheLoaiBaiViet tlbv = theLoaiBaiVietRepository.findById(idUpdate).get();
            tlbv.setTenTheLoaiBaiViet(form.getTenTheLoaiBaiViet());
            tlbv.setDuongDan(form.getDuongDan());
            theLoaiBaiVietRepository.save(tlbv);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
