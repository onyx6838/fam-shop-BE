package com.fam.controller;

import com.fam.service.IDacTrungSanPhamService;
import com.fam.service.ISanPhamService;
import com.fam.specification.SanPhamFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1/sanphams")
@CrossOrigin("*")
public class SanPhamController {
    @Autowired
    private ISanPhamService sanPhamService;

    @Autowired
    private IDacTrungSanPhamService dacTrungSanPhamService;

    @GetMapping
    public ResponseEntity<?> getAllSanPhams(Pageable pageable) {
        return new ResponseEntity<>(sanPhamService.getAllSanPhams(pageable), HttpStatus.OK);
    }

    @GetMapping(value = "/new")
    public ResponseEntity<?> getNewSanPham(Pageable pageable) {
        return new ResponseEntity<>(sanPhamService.getNewSanPhamsOrderByThoiGian(pageable), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getGroupByID(@PathVariable(name = "id") int id) {
        return new ResponseEntity<>(sanPhamService.getById(id), HttpStatus.OK);
    }

//    @PostMapping(value = "/filter")
//    public ResponseEntity<?> getSanPhamByDacTrung(@RequestBody List<Integer> dacTrungs, Pageable pageable) {
//        return new ResponseEntity<>(dacTrungSanPhamService.findByDacTrungs(dacTrungs, pageable), HttpStatus.OK);
//    }

    @PostMapping(value = "/filter")
    public ResponseEntity<?> getSanPhamByDacTrung(@RequestBody SanPhamFilter sanPhamFilter, Pageable pageable) {
        return new ResponseEntity<>(sanPhamService.getByDacTrungsAndLoaiSP(sanPhamFilter, pageable), HttpStatus.OK);
    }
}