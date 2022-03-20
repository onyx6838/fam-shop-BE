package com.fam.specification;

import com.fam.entity.SanPham;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;

@AllArgsConstructor
public class SanPhamSpecification implements Specification<SanPham> {
    private final String operator;
    private final String field;
    private final SanPhamFilter sanPhamFilter;

    @Override
    public Predicate toPredicate(Root<SanPham> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        if (operator.equalsIgnoreCase("IN")) {
            if (field.equalsIgnoreCase("MaDacTrung.LEFT")) {
                query.distinct(true);
                return root.join("listSPDacTrung", JoinType.LEFT).get("dacTrung").in(sanPhamFilter.getDacTrungs());
            } else if (field.equalsIgnoreCase("loaiSanPham")) {
                return root.get(field).in(sanPhamFilter.getLoaiSPList());
            } else {
                return builder.and();
            }
        }
        if (operator.equalsIgnoreCase("EQUALS")) {
            if (field.equalsIgnoreCase("loaiSanPham")) {
                return builder.equal(root.get(field), sanPhamFilter.getLoaiSP());
            }
            else if(field.equalsIgnoreCase("thuongHieu")){
                return builder.equal(root.get(field), sanPhamFilter.getThuongHieu());
            }
            else {
                return builder.and();
            }
        }
        if (operator.equalsIgnoreCase("LIKE")) {
            if (field.equalsIgnoreCase("ten")) {
                return builder.like(root.get(field), "%" + sanPhamFilter.getTenSP() + "%");
            } else {
                return builder.and();
            }
        }
        return null;
    }
}
