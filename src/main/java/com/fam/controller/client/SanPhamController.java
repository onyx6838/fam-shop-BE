package com.fam.controller.client;

import com.fam.dto.product.CategoryDto;
import com.fam.service.ISanPhamService;
import com.fam.specification.SanPhamFilter;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "SanPham REST", description = "Manage SanPham Api", tags = "API liên quan đến Sản Phẩm")
@RestController
@RequestMapping(value = "api/v1/sanphams")
@CrossOrigin("*")
public class SanPhamController {
    @Autowired
    private ISanPhamService sanPhamService;

    @ApiOperation(value = "1, Lấy tất cả các sản phẩm", notes = "Url: /api/v1/sanphams")
    @GetMapping
    public ResponseEntity<?> getAllSanPhams(Pageable pageable) {
        return new ResponseEntity<>(sanPhamService.getAllSanPhams(pageable), HttpStatus.OK);
    }

    @ApiOperation(value = "2, Lấy tất cả các sản phẩm mới nhập về", notes = "Url: /api/v1/sanphams/new")
    @GetMapping(value = "/new")
    public ResponseEntity<?> getNewSanPham(Pageable pageable) {
        return new ResponseEntity<>(sanPhamService.getNewSanPhamsOrderByThoiGian(pageable), HttpStatus.OK);
    }

    @ApiOperation(value = "3, Lấy thông tin chi tiết sản phẩm được chọn", notes = "Url: /api/v1/sanphams/{id}")
    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getGroupByID(@PathVariable(name = "id") int id) {
        return new ResponseEntity<>(sanPhamService.getById(id), HttpStatus.OK);
    }

    @ApiOperation(value = "4, Lọc sản phẩm theo đặc trưng", notes = "Url: /api/v1/sanphams/filter")
    @PostMapping(value = "/filter")
    public ResponseEntity<?> getSanPhamByDacTrung(@RequestBody SanPhamFilter sanPhamFilter, Pageable pageable) {
        return new ResponseEntity<>(sanPhamService.getByDacTrungsAndLoaiSP(sanPhamFilter, pageable), HttpStatus.OK);
    }

    @ApiOperation(value = "5, Lọc sản phẩm theo loại sản phẩm cha", notes = "Url: /api/v1/sanphams/category")
    @PostMapping(value = "/category")
    public ResponseEntity<?> getProductWithParentLoaiSanPhams(@RequestBody List<CategoryDto> categories, Pageable pageable) {
        return new ResponseEntity<>(sanPhamService.getByParentLoaiSP(categories, pageable), HttpStatus.OK);
    }

    @ApiOperation(value = "5, Danh sách sản phẩm cùng loại với spham đc chọn", notes = "Url: /api/v1/sanphams/category/relate")
    @GetMapping(value = "/category/relate/{cateId}/{maSP}")
    public ResponseEntity<?> getRelateProductWithCategory(@PathVariable(value = "cateId") int cateId,
                                                          @PathVariable(value = "maSP") int maSP) {
        return new ResponseEntity<>(sanPhamService.findByLoaiSanPham(cateId, maSP), HttpStatus.OK);
    }
}