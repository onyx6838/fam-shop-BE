package com.fam.repository;

import java.util.List;

/**
 * @author giangdm
 */
public interface DonDatHangRepositoryCustom {
    List<Object[]> statisticOrderByYear(int selectedYear, String trangThaiDonDat);

    List<Integer> getDistinctYear();
}
