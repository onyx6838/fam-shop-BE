package com.fam.service.impl;

import com.fam.dto.payment.PaymentDto;
import com.fam.entity.CTDD;
import com.fam.entity.DonDatHang;
import com.fam.entity.SanPham;
import com.fam.entity.TaiKhoan;
import com.fam.entity.enumerate.HinhThucTToan;
import com.fam.entity.enumerate.TrangThaiDonDat;
import com.fam.entity.enumerate.TrangThaiTToan;
import com.fam.repository.ICTDDRepository;
import com.fam.repository.IDonDatHangRepository;
import com.fam.service.IDonDatHangService;
import com.fam.service.ISanPhamService;
import com.fam.service.ITaiKhoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class DonDatHangService implements IDonDatHangService {
    @Autowired
    private IDonDatHangRepository donDatHangRepository;

    @Autowired
    private ICTDDRepository ctddRepository;

    @Autowired
    private ITaiKhoanService taiKhoanService;

    @Autowired
    private ISanPhamService sanPhamService;

    @Override
    public void payment(PaymentDto dto) {
        DonDatHang donDatHang = new DonDatHang();
        TaiKhoan tk = taiKhoanService.getTaiKhoanByTenTK(dto.getOrder().getUsername());

        donDatHang.setKhachHang(tk);
        donDatHang.setThoiGianDat(new Date());
        donDatHang.setThoiGianNhanHang(dto.getOrder().getDateDelivery());
        donDatHang.setDiaChi(dto.getOrder().getShipAddress());
        donDatHang.setSdtNhanHang(dto.getOrder().getPhone());
        donDatHang.setTrangThai(TrangThaiDonDat.DON_DAT);
        donDatHang.setTrangThaiTToan(TrangThaiTToan.CHUA_TT);
        donDatHang.setHinhThucTToan(HinhThucTToan.values()[Integer.parseInt(dto.getOrder().getPaymentType())]);
        donDatHang.setTongTien(dto.getOrder().getTotalPrice());

        donDatHangRepository.save(donDatHang);

        dto.getCart().forEach(x -> {
            CTDD ctdd = new CTDD();
            ctdd.setDonDatHang(donDatHang);
            SanPham sp = sanPhamService.getById(x.getMaSP());
            ctdd.setTongTienMuc(x.getQty() * x.getDonGiaBan());
            ctdd.setSanPham(sp);
            ctdd.setSoLuong(x.getQty());
            ctddRepository.save(ctdd);
        });

    }
}
