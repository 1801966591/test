package com.example.exam.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.exam.entity.Companies;
import com.example.exam.service.CompaniesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 公司表 前端控制器
 * </p>
 *
 * @author ZhongZhen
 * @since 2023-10-01
 */
@RestController
@Slf4j
public class CompaniesController {

    @Autowired
    private CompaniesService companiesService;

    /**
     * GET /companies 查询出所有的热门企业 按照时间排序 最近创建的排在前面
     * @return
     */
    @GetMapping("/companies")
    public List<Companies> list(){

        LambdaQueryWrapper<Companies> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderByDesc(Companies::getOrgEstDate);

        return companiesService.list(queryWrapper);
    }

    /**
     * POST /companies 新增企业
     * POST http://localhost:9999/companies
     * Content-Type: application/json
     *
     * {
     *     "orgUniCode": "1",
     *     "orgChiName": "1",
     *     "induSmaPar": "1",
     *     "orgDele": "1",
     *     "regCap": "1",
     *     "orgEstDate": "2023-10-1 08:00:00"
     *   }
     * @param companies
     * @return
     */
    @PostMapping("/companies")
    public String save(@RequestBody Companies companies){

        boolean flag = companiesService.save(companies);

        if (flag){
            return "新增成功";
        } else {
            return "新增失败";
        }
    }

    /**
     * GET /companies/{comId} 查询指定企业
     * GET http://localhost:9999/companies/200000157
     * @param comId
     * @return
     */
    @GetMapping("/companies/{comId}")
    public Companies getByComId(@PathVariable("comId") Long comId){
        LambdaQueryWrapper<Companies> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Companies::getOrgUniCode,comId);

        return companiesService.getOne(queryWrapper);
    }

    /**
     * PUT /companies/{comId} 更新指定企业
     * PUT http://localhost:9999/companies/200000157
     * Content-Type: application/json
     *
     * {
     *   "orgChiName": "漳州片仔癀药业股份有限公司1",
     *   "induSmaPar": "医药制造业",
     *   "orgDele": "林纬奇",
     *   "regCap": "60331.721",
     *   "orgEstDate": "1999-12-28 00:00:00"
     * }
     * @param comId
     * @param companies
     * @return
     */
    @PutMapping("/companies/{comId}")
    public String updateByComId(@PathVariable("comId") Long comId,@RequestBody Companies companies){
        LambdaQueryWrapper<Companies> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Companies::getOrgUniCode,comId);
        boolean flag = companiesService.update(companies, queryWrapper);

        if (flag){
            return "更新成功";
        } else {
            return "更新失败";
        }
    }

    /**
     * DELETE /companies/{comId} 删除指定企业
     * DELETE http://localhost:9999/companies/200000157
     * @param comId
     * @return
     */
    @DeleteMapping("/companies/{comId}")
    public String deleteByComId(@PathVariable("comId") Long comId){

        LambdaQueryWrapper<Companies> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Companies::getOrgUniCode,comId);
        boolean flag = companiesService.remove(queryWrapper);

        if (flag){
            return "删除成功";
        } else {
            return "删除失败";
        }

    }



}
