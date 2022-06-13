package com.fam.service;

import com.fam.dto.payment.DistrictDto;
import com.fam.dto.payment.PrecinctDto;

import java.util.List;

/**
 * @author giangdm
 */
public interface IXmlService {
    List<DistrictDto> getDistrictFromXML();

    List<PrecinctDto> getPrecinctByDistrictIDFromXML(int districtID);
}
