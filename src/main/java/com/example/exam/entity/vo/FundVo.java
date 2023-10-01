package com.example.exam.entity.vo;

import lombok.Data;

import java.time.LocalDate;

/**
 * @Author: ZhongZhen
 * @PackageName: com.example.exam.entity.vo
 * @ClassName: FundVo
 * @Time: 2023/10/1 16:04
 * @Description: TODO
 * @Version: 1.0
 */
@Data
public class FundVo {
    private Long fundCode;
    private String fundShortName;
    private LocalDate endDate;
    private String unitNetVal;

    private String weekChg;
    private String monthChg;
    private String threeMonthChg;
    private String yearChg;
    private String twoYearChg;
    private String threeYearChg;
    private String thisYearChg;
    private String establishedChg;
}
