package com.fam.repository.impl;

import com.fam.repository.DonDatHangRepositoryCustom;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.List;

/**
 * @author giangdm
 */
@Component
public class DonDatHangRepositoryCustomImpl implements DonDatHangRepositoryCustom {
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Object[]> statisticOrderByYear(int selectedYear, String trangThaiDonDat) {
        StoredProcedureQuery query = em.createStoredProcedureQuery("statistic_ord_per_month")
                .registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter(2, String.class, ParameterMode.IN)
                .setParameter(1, selectedYear)
                .setParameter(2, trangThaiDonDat);
        query.execute();
        return (List<Object[]>) query.getResultList();
    }

    @Override
    public List<Integer> getDistinctYear() {
        Query query = em.createQuery("SELECT distinct YEAR(d.thoiGianDat) from DonDatHang d");
        return (List<Integer>) query.getResultList();
    }
}
