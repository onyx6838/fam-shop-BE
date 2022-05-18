package com.fam.service.impl;

import com.fam.dto.statistic.CategorySoldWithOrder;
import com.fam.dto.statistic.OrderPerMonthByYear;
import com.fam.dto.statistic.SummaryByYear;
import com.fam.entity.enumerate.TrangThaiDonDat;
import com.fam.repository.ICTDDRepository;
import com.fam.repository.IDonDatHangRepository;
import com.fam.service.IThongKeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

    @Override
    public List<CategorySoldWithOrder> categorySoldWithOrder() {
        List<Object[]> sttData = donDatHangRepository.categoryMostSold();
        List<CategorySoldWithOrder> finalSttData = sttData.stream().map(x -> {
            CategorySoldWithOrder or = new CategorySoldWithOrder();
            or.setMaLoai((Integer) x[0]);
            or.setTenLoai(x[1].toString());
            or.setSoSPBan(((BigInteger) x[2]).intValue());
            return or;
        }).collect(Collectors.toList());
        return finalSttData;
    }

    @Override
    public Map<Integer, List<Object>> productSoldPerMonthInYear(int year) {
        List<Object[]> sttData = donDatHangRepository.productSoldPerMonthInYear(year);

        Map<Integer, List<Object>> splist2 = new HashMap<>();
        sttData.forEach(x -> {
            Object[] objs = x;
            List<Object> spList = new ArrayList<>();
            spList.add(objs[1].toString());
            for (int i = 2; i < objs.length; i++) {
                spList.add(((BigDecimal) objs[i]).toBigInteger().intValueExact());
            }
            splist2.put((Integer) objs[0], spList);
        });
        return splist2;
    }

    @Override
    public List<SummaryByYear> summaryByYear(int year) {
        List<Object[]> sttData = donDatHangRepository.summaryByYear(year);
        List<SummaryByYear> finalSttData = sttData.stream().map(x -> {
            SummaryByYear or = new SummaryByYear();
            or.setMonth(((BigInteger) x[0]).intValue());
            or.setPrdSold(((BigDecimal) x[1]).toBigInteger().intValueExact());
            or.setTotal((Double) x[2]);
            return or;
        }).collect(Collectors.toList());
        return finalSttData;
    }
}
