package com.fam.service;

import com.fam.dto.order.OrderShipperChangeDto;
import com.fam.dto.order.OrderStatusChangeDto;
import com.fam.dto.payment.PaymentDto;
import com.fam.entity.DonDatHang;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IDonDatHangService {
    void payment(PaymentDto dto);

    Page<DonDatHang> getAllDonDats(Pageable pageable);

    boolean changeStatusOrder(OrderStatusChangeDto form);

    boolean changePaymentTypeOrder(DonDatHang orderNeedToChangePaymentType, int paymentType);

    List<Integer> distinctYearDatHang();

    boolean changeShipperDonDat(OrderShipperChangeDto dto);

    Page<DonDatHang> getDonDatHangByKhachHang(String tenTK, Pageable pageable);
}
