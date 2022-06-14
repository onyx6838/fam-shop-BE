package com.fam.service;

import com.fam.dto.form.TheLoaiBaiVietCreateDto;
import com.fam.entity.TheLoaiBaiViet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author giangdm
 */
public interface ITheLoaiBaiVietService {
    List<TheLoaiBaiViet> getAllTheLoaiBaiVietWithoutPage();

    Page<TheLoaiBaiViet> getAllTheLoaiBaiViet(Pageable pageable);

    boolean createTheLoaiBaiViet(TheLoaiBaiVietCreateDto form);

    boolean updateTLBV(TheLoaiBaiVietCreateDto form, int idUpdate);
}
