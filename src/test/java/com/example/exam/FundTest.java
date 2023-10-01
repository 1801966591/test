package com.example.exam;

import com.example.exam.entity.Fund;
import com.example.exam.mapper.FundMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.DecimalFormat;
import java.time.LocalDate;

/**
 * @Author: ZhongZhen
 * @PackageName: com.example.exam
 * @ClassName: FundTest
 * @Time: 2023/10/1 14:37
 * @Description: TODO
 * @Version: 1.0
 */
@SpringBootTest
public class FundTest {

    @Autowired
    private FundMapper fundMapper;

    @Test
    void testFund() {
        LocalDate today = LocalDate.of(2023,9,8);
        LocalDate weekAgo = today.minusWeeks(1);
        LocalDate oneMonthAgo = today.minusMonths(1);
        LocalDate threeMonthAgo = today.minusMonths(3);
        LocalDate oneYearAgo = today.minusYears(1);
        LocalDate twoYearAgo = today.minusYears(2);
        LocalDate threeYearAgo = today.minusYears(3);
        int i = 1;
        Fund minDate = fundMapper.getMinDate(17972L);
        LocalDate startDate = minDate.getEndDate();
        Double unitNetVal = minDate.getUnitNetVal();
        Double latest = fundMapper.getUnitNetVal(17972L, today);
        while (latest == null && startDate.isBefore(today)){
            today = today.minusDays(i);
            latest = fundMapper.getUnitNetVal(17972L,today);
        }
        if (latest == null){
            latest = unitNetVal;
        }
        Double week = fundMapper.getUnitNetVal(17972L, weekAgo);
        while (week == null  && startDate.isBefore(weekAgo)) {
            weekAgo = weekAgo.minusDays(i);
            week = fundMapper.getUnitNetVal(17972L, weekAgo);
        }
        if (week == null) {
            week = unitNetVal;
        }
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        String weekChg = decimalFormat.format((latest - week) * 100 / week);
        Double oneMonth = fundMapper.getUnitNetVal(17972L,oneMonthAgo);
        while (oneMonth == null){
            oneMonthAgo = oneMonthAgo.minusDays(i);
            oneMonth = fundMapper.getUnitNetVal(17972L,oneMonthAgo);
        }
        Double threeMonth = fundMapper.getUnitNetVal(17972L,threeMonthAgo);
        while (threeMonth == null){
            threeMonthAgo = threeMonthAgo.minusDays(i);
            threeMonth = fundMapper.getUnitNetVal(17972L,threeMonthAgo);
        }
        Double oneYear = fundMapper.getUnitNetVal(17972L,oneYearAgo);
        while (oneYear == null) {
            oneYearAgo = oneYearAgo.minusDays(i);
            oneYear = fundMapper.getUnitNetVal(17972L,oneYearAgo);
        }
        Double twoYear = fundMapper.getUnitNetVal(17972L,twoYearAgo);
        while (twoYear == null) {
            twoYearAgo = twoYearAgo.minusDays(i);
            twoYear = fundMapper.getUnitNetVal(17972L,twoYearAgo);
        }
        Double threeYear = fundMapper.getUnitNetVal(17972L,threeYearAgo);
        while (threeYear == null && startDate.isBefore(threeYearAgo)) {
            threeYearAgo = threeYearAgo.minusDays(i);
            threeYear = fundMapper.getUnitNetVal(17972L,threeYearAgo);
        }
        if (threeYear == null){
            threeYear = unitNetVal;
        }


    }
}
