package com.fam.controller;

import com.fam.service.IDacTrungService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api/v1/dactrung")
@CrossOrigin("*")
public class DacTrungController {
    @Autowired
    private IDacTrungService dacTrungService;

    @GetMapping
    public ResponseEntity<?> getAllDacTrungsGroupByLoai() {
        return new ResponseEntity<>(dacTrungService.getAllDacTrungs(), HttpStatus.OK);
    }
}
