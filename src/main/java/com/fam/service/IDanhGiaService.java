package com.fam.service;

import com.fam.dto.form.DanhGiaCreate;
import com.fam.entity.DanhGia;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author giangdm
 */
public interface IDanhGiaService {
    Page<DanhGia> getAllParentDanhGiaBySanPham(Pageable pageable, int maSP);

    Page<DanhGia> getParentDanhGias(Pageable pageable);

    List<DanhGia> getChildDanhGia(int maDanhGiaCha);

    Page<DanhGia> getChildDanhGias(Pageable pageable, int maDanhGiaCha);

    DanhGia addDanhGia(DanhGiaCreate form);

    DanhGia addQuanTriDanhGia(DanhGiaCreate form);

    DanhGia updateQuanTriDanhGia(DanhGiaCreate form, int maDanhGia);

    Integer findQuanTriReplyParent(int maDanhGia);

    boolean removeChildDanhGia(int maDanhGia);

    boolean lockChildDanhGia(int maDanhGia);

    boolean unlockChildDanhGia(int maDanhGia);

    boolean checkQuanTriReply(int maDanhGia);
}
