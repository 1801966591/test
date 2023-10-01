package com.example.exam.controller;

import com.example.exam.entity.dto.FundDto;
import com.example.exam.service.FundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 基金表 前端控制器
 * </p>
 *
 * @author ZhongZhen
 * @since 2023-10-01
 */
@RestController
public class FundController {

    @Autowired
    private FundService fundService;

    @GetMapping("/fund")
    public FundDto page(int pageNum, int pageSize, @RequestParam(defaultValue = "UNIT_NET_VAL") String sortField, @RequestParam(defaultValue = "ASC") String sortDirection){
        return fundService.getPage(pageNum,pageSize,sortField,sortDirection);
    }

}
