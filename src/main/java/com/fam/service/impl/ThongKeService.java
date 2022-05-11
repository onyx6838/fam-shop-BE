package com.fam.service.impl;

import com.fam.dto.statistic.OrderPerMonthByYear;
import com.fam.entity.enumerate.TrangThaiDonDat;
import com.fam.repository.ICTDDRepository;
import com.fam.repository.IDonDatHangRepository;
import com.fam.service.IThongKeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author giangdm
 */
@Service
public class ThongKeService implements IThongKeService {
    @Autowired
    private IDonDatHangRepository donDatHangRepository;

    @Autowired
    private ICTDDRepository ctddRepository;

    @Override
    public int totalRevenue() {
        return donDatHangRepository.totalRevenue();
    }

    @Override
    public int countOrderWithType(String trangThaiDonDat) {
        TrangThaiDonDat enumTTDT = Enum.valueOf(TrangThaiDonDat.class, trangThaiDonDat);
        return donDatHangRepository.countOrderWithType(enumTTDT);
    }

    @Override
    public int totalProductSold() {
        return ctddRepository.totalProductSold();
    }

    @Override
    public int countCustomerBuyOrderDone(int year) {
        return donDatHangRepository.countCustomerBuyOrderDone(year);
    }

    @Override
    public List<OrderPerMonthByYear> statisticOrderByYear(int year, String trangThaiDonDat) {
        List<Object[]> sttData = donDatHangRepository.statisticOrderByYear(year, trangThaiDonDat);
        List<OrderPerMonthByYear> finalSttData = sttData.stream().map(x -> {
            OrderPerMonthByYear or = new OrderPerMonthByYear();
            or.setMonth(((BigInteger) x[0]).intValue());
            or.setOrd_per_month(((BigInteger) x[1]).intValue());
            return or;
        }).collect(Collectors.toList());
        return finalSttData;
    }
}
