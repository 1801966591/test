package com.example.exam.service.impl;

import com.example.exam.entity.Companies;
import com.example.exam.mapper.CompaniesMapper;
import com.example.exam.service.CompaniesService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 公司表 服务实现类
 * </p>
 *
 * @author ZhongZhen
 * @since 2023-10-01
 */
@Service
public class CompaniesServiceImpl extends ServiceImpl<CompaniesMapper, Companies> implements CompaniesService {

}
