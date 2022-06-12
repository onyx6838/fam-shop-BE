package com.fam.service.impl;

import com.fam.dto.order.OrderShipperChangeDto;
import com.fam.dto.order.OrderStatusChangeDto;
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
import com.fam.repository.ISanPhamRepository;
import com.fam.service.IDonDatHangService;
import com.fam.service.ISanPhamService;
import com.fam.service.ITaiKhoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

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

    @Autowired
    private ISanPhamRepository sanPhamRepository;

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

    @Override
    public Page<DonDatHang> getAllDonDats(Pageable pageable) {
        return donDatHangRepository.findAll(pageable);
    }

    @Override
    public boolean changeStatusOrder(OrderStatusChangeDto form) {
        DonDatHang donNeedToChange = donDatHangRepository.findById(form.getId()).get();
        donNeedToChange.setTrangThai(TrangThaiDonDat.values()[form.getOrderStatus()]);
        if (TrangThaiDonDat.values()[form.getOrderStatus()] == TrangThaiDonDat.VAN_DON) {
            donNeedToChange.getListCTDD().forEach(x -> {
                x.getSanPham().setSoLuong(x.getSanPham().getSoLuong() - x.getSoLuong());
                sanPhamRepository.save(x.getSanPham());
            });
        }
        if (TrangThaiDonDat.values()[form.getOrderStatus()] == TrangThaiDonDat.HUY_DON
                && donNeedToChange.getHinhThucTToan() == HinhThucTToan.NHAN_HANG
                && donNeedToChange.getTrangThai() == TrangThaiDonDat.VAN_DON) {
            donNeedToChange.getListCTDD().forEach(x -> {
                x.getSanPham().setSoLuong(x.getSanPham().getSoLuong() + x.getSoLuong());
                sanPhamRepository.save(x.getSanPham());
            });
        }
        donDatHangRepository.save(donNeedToChange);
        return true;
    }

    @Override
    public boolean changeStatusPaymentOrder(OrderStatusChangeDto form) {
        try {
            DonDatHang donNeedToChange = donDatHangRepository.findById(form.getId()).get();
            donNeedToChange.setTrangThaiTToan(TrangThaiTToan.values()[form.getPaymentType()]);
            donDatHangRepository.save(donNeedToChange);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public List<Integer> distinctYearDatHang() {
        return donDatHangRepository.getDistinctYear();
    }

    @Override
    public boolean changeShipperDonDat(OrderShipperChangeDto dto) {
        try {
            DonDatHang needToChange = donDatHangRepository.findById(dto.getMaDonDat()).get();
            TaiKhoan tk = taiKhoanService.findById(dto.getMaTK());
            needToChange.setNhanVien(tk);
            donDatHangRepository.save(needToChange);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Page<DonDatHang> getDonDatHangByKhachHang(String tenTK, Pageable pageable) {
        TaiKhoan tk = taiKhoanService.getTaiKhoanByTenTK(tenTK);
        return donDatHangRepository.getDonDatHangByKhachHang(tk, pageable);
    }
}
