package com.fam.controller.client;

import com.fam.dto.payment.DistrictDto;
import com.fam.dto.payment.PrecinctDto;
import com.fam.service.IXmlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author giangdm
 */
@RestController
@RequestMapping(value = "/api/v1/files")
public class ConfigController {
    @Autowired
    private IXmlService xmlService;

    @GetMapping(value = "/xml/districts")
    public ResponseEntity<?> getDistrictFromXML() {
        List<DistrictDto> districtList = xmlService.getDistrictFromXML();
        return new ResponseEntity<>(districtList, HttpStatus.OK);
    }

    @GetMapping(value = "/xml/districts/{id}")
    public ResponseEntity<?> getPrecinctByDistrictIDFromXML(@PathVariable(name = "id") int districtID) {
        List<PrecinctDto> precinctList = xmlService.getPrecinctByDistrictIDFromXML(districtID);
        return new ResponseEntity<>(precinctList, HttpStatus.OK);
    }
}
