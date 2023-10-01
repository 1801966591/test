package com.example.exam.entity.dto;

import com.example.exam.entity.vo.FundVo;
import lombok.Data;

import java.util.List;

/**
 * @Author: ZhongZhen
 * @PackageName: com.example.exam.entity.dto
 * @ClassName: FundDto
 * @Time: 2023/10/1 13:01
 * @Description: TODO
 * @Version: 1.0
 */
@Data
public class FundDto{
    private List<FundVo> records;
    private Long total;
}
