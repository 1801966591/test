package com.example.exam.mapper;

import com.example.exam.entity.Companies;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 公司表 Mapper 接口
 * </p>
 *
 * @author ZhongZhen
 * @since 2023-10-01
 */
@Mapper
public interface CompaniesMapper extends BaseMapper<Companies> {

}
