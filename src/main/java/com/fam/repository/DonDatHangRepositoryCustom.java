package com.fam.repository;

import java.util.List;

/**
 * @author giangdm
 */
public interface DonDatHangRepositoryCustom {
    List<Object[]> statisticOrderByYear(int selectedYear, String trangThaiDonDat);

    List<Integer> getDistinctYear();

    // loai sp ban dc nhieu hang nhat (top 4)
    List<Object[]> categoryMostSold();

    List<Object[]> productSoldPerMonthInYear(int year);

    List<Object[]> summaryByYear(int year);
}
