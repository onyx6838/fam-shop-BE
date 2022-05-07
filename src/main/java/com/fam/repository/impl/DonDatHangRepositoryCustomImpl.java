package com.fam.repository.impl;

import com.fam.repository.DonDatHangRepositoryCustom;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
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
}
