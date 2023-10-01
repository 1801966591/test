package com.example.exam.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.exam.entity.Fund;
import com.example.exam.entity.dto.FundDto;

/**
 * <p>
 * 基金表 服务类
 * </p>
 *
 * @author ZhongZhen
 * @since 2023-10-01
 */
public interface FundService extends IService<Fund> {

    FundDto getPage(int pageNum, int pageSize, String sortField, String sortDirection);

}
