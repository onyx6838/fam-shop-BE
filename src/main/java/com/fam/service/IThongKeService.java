package com.fam.service;

import com.fam.dto.statistic.CategorySoldWithOrder;
import com.fam.dto.statistic.OrderPerMonthByYear;
import com.fam.dto.statistic.SummaryByYear;

import java.util.List;
import java.util.Map;

/**
 * @author giangdm
 */
public interface IThongKeService {
    int totalRevenue();

    int countOrderWithType(String trangThaiDonDat);

    int totalProductSold();

    int countCustomerBuyOrderDone(int year);

    List<OrderPerMonthByYear> statisticOrderByYear(int year, String trangThaiDonDat);

    List<CategorySoldWithOrder> categorySoldWithOrder();

    Map<Integer, List<Object>> productSoldPerMonthInYear(int year);

    List<SummaryByYear> summaryByYear(int year);
}
