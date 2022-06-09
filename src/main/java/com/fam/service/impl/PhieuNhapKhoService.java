package com.fam.service.impl;

import com.fam.dto.form.PNKCreateDto;
import com.fam.entity.*;
import com.fam.repository.ICTDDRepository;
import com.fam.repository.IChiTietPNKRepository;
import com.fam.repository.IPhieuNhapKhoRepository;
import com.fam.repository.ISanPhamRepository;
import com.fam.service.IPhieuNhapKhoService;
import com.fam.service.ISanPhamService;
import com.fam.service.ITaiKhoanService;
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

    @Autowired
    private IChiTietPNKRepository chiTietPNKRepository;

    @Autowired
    private ITaiKhoanService taiKhoanService;

    @Autowired
    private ISanPhamService sanPhamService;

    @Autowired
    private ISanPhamRepository sanPhamRepository;

    @Override
    public Page<PhieuNhapKho> getAllPNK(Pageable pageable) {
        return phieuNhapKhoRepository.findAll(pageable);
    }

    @Override
    public boolean insertPNK(PNKCreateDto dto) {
        try {
            TaiKhoan tk = taiKhoanService.getTaiKhoanByTenTK(dto.getReceipt().getTenTKQuanTri());
            if (tk != null) {
                PhieuNhapKho pnk = new PhieuNhapKho();
                pnk.setLoaiPhieu(dto.getReceipt().getLoaiPhieu());
                pnk.setNhanVien(tk);    // sdung tkhoan quan tri (thay cho nhan vien)
                int total = dto.getCtpnks().stream().reduce(0, (accu, item) -> accu + (item.getSoLuong() * item.getDonGiaNhap()), Integer::sum);
                pnk.setTongTien(total);
                phieuNhapKhoRepository.save(pnk);
                dto.getCtpnks().forEach(x -> {
                    ChiTietPNK chiTietPNK = new ChiTietPNK();
                    chiTietPNK.setPhieuNhapKho(pnk);
                    SanPham sp = sanPhamService.getById(x.getMaSP());
                    chiTietPNK.setSanPham(sp);
                    chiTietPNK.setTongTienMuc(x.getSoLuong() * x.getDonGiaNhap());
                    chiTietPNK.setHanSuDung(x.getHanSuDung());
                    chiTietPNK.setSoLuong(x.getSoLuong());
                    chiTietPNKRepository.save(chiTietPNK);
                    sp.setSoLuong(sp.getSoLuong() + x.getSoLuong());
                    sanPhamRepository.save(sp);
                });
            } else return false;
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
