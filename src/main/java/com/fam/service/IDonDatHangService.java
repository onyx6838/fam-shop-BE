package com.fam.service;

import com.fam.dto.payment.PaymentDto;
import com.fam.entity.DonDatHang;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IDonDatHangService {
    void payment(PaymentDto dto);

    Page<DonDatHang> getAllDonDats(Pageable pageable);
}
