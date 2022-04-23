package com.fam.service.impl;

import com.fam.dto.form.DacTrungSanPhamAddDto;
import com.fam.dto.form.DacTrungSanPhamDeleteDto;
import com.fam.entity.DacTrung;
import com.fam.entity.DacTrungSanPham;
import com.fam.entity.SanPham;
import com.fam.repository.IDacTrungRepository;
import com.fam.repository.IDacTrungSanPhamRepository;
import com.fam.service.IDacTrungSanPhamService;
import com.fam.service.ISanPhamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * @author giangdm
 */
@Service
public class DacTrungSanPhamService implements IDacTrungSanPhamService {
    @Autowired
    private IDacTrungSanPhamRepository dacTrungSanPhamRepository;

    @Autowired
    private IDacTrungRepository dacTrungRepository;

    @Autowired
    private ISanPhamService sanPhamService;

    @Override
    public Page<DacTrungSanPham> getAllDacTrungSP(Pageable pageable) {
        return dacTrungSanPhamRepository.findAll(pageable);
    }

    @Override
    public Page<DacTrung> getDacTrungBySanPham(int maSP, Pageable pageable) {
        return dacTrungSanPhamRepository.getDacTrungBySanPham(maSP, pageable);
    }

    @Override
    public boolean addDacTrungToSP(DacTrungSanPhamAddDto form) {
        try {
            SanPham sp = sanPhamService.getById(form.getMaSP());
            form.getDacTrungs().forEach(x -> {
                DacTrungSanPham dtsp = new DacTrungSanPham();
                dtsp.setSanPham(sp);
                DacTrung dt = dacTrungRepository.findById(x).get();
                dtsp.setDacTrung(dt);
                dacTrungSanPhamRepository.save(dtsp);
            });
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public DacTrungSanPham findBySanPhamAndDacTrung(DacTrungSanPhamDeleteDto dto) {
        return dacTrungSanPhamRepository.findBySanPhamAndDacTrung(dto.getMaSP(), dto.getMaDacTrung());
    }

    @Override
    public boolean deleteDacTrungSP(DacTrungSanPhamDeleteDto dto) {
        try {
            DacTrungSanPham find = findBySanPhamAndDacTrung(dto);
            dacTrungSanPhamRepository.delete(find);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
