package com.fam.service;

import com.fam.dto.statistic.OrderPerMonthByYear;

import java.util.List;

/**
 * @author giangdm
 */
public interface IThongKeService {
    int totalRevenue();

    int countOrderWithType(String trangThaiDonDat);

    int totalProductSold();

    List<OrderPerMonthByYear> statisticOrderByYear(int year, String trangThaiDonDat);
}
