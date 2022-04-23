package com.fam.dto.form;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author giangdm
 */
@Data
public class DacTrungCreateDto {
    private String ten;

    private String moTa;

    private String giaTri;

    private String donVi;

    @JsonProperty("loaiDacTrung")
    private String LoaiDacTrung;
}
