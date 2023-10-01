package com.example.exam.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.exam.entity.Fund;
import com.example.exam.entity.dto.FundDto;
import com.example.exam.entity.vo.FundVo;
import com.example.exam.mapper.FundMapper;
import com.example.exam.service.FundService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 基金表 服务实现类
 * </p>
 *
 * @author ZhongZhen
 * @since 2023-10-01
 */
@Service
public class FundServiceImpl extends ServiceImpl<FundMapper, Fund> implements FundService {

    @Autowired
    private FundMapper fundMapper;

    @Override
    public FundDto getPage(int pageNum, int pageSize, String sortField, String sortDirection) {

        int pageNo = (pageNum - 1) * pageSize;
        int i = 1;
        DecimalFormat decimalFormat = new DecimalFormat("#.##");

        List<FundVo> list = new ArrayList<>();
        List<Fund> fundList;

        //查询所有的基金的最新数据，并按条件排序
        if (sortField.equals("END_DATE") && sortDirection.equalsIgnoreCase("ASC")){
            fundList = fundMapper.getPageDateASC(pageNo, pageSize);
        }else if (sortField.equals("END_DATE") && sortDirection.equalsIgnoreCase("DESC")){
            fundList = fundMapper.getPageDateDESC(pageNo, pageSize);
        }else if (sortField.equals("UNIT_NET_VAL") && sortDirection.equalsIgnoreCase("ASC")){
            fundList = fundMapper.getPageUnitNetValASC(pageNo, pageSize);
        }else if (sortField.equals("UNIT_NET_VAL") && sortDirection.equalsIgnoreCase("DESC")){
            fundList = fundMapper.getPageUnitNetValDESC(pageNo, pageSize);
        }else {
            throw new RuntimeException();
        }
        //查出总数
        Long total = fundMapper.getTotal();

        for (Fund fund : fundList){
            LocalDate today = LocalDate.now();
            LocalDate weekAgo = today.minusWeeks(1);
            LocalDate oneMonthAgo = today.minusMonths(1);
            LocalDate threeMonthAgo = today.minusMonths(3);
            LocalDate oneYearAgo = today.minusYears(1);
            LocalDate twoYearAgo = today.minusYears(2);
            LocalDate threeYearAgo = today.minusYears(3);
            Long fundCode = fund.getFundCode();
            FundVo fundVo = new FundVo();
            BeanUtils.copyProperties(fund,fundVo);
            fundVo.setUnitNetVal(decimalFormat.format(fund.getUnitNetVal()));

            //找到成立时的数据
            Fund minDate = fundMapper.getMinDate(fundCode);
            //成立日期
            LocalDate startDate = minDate.getEndDate();
            //最早的单位净值
            Double unitNetVal = minDate.getUnitNetVal();

            //找出当前的单位净值，如果没有，则找出最近的单位净值，如果时间早于成立日期，则单位净值为成立时的单位净值
            Double latest = fundMapper.getUnitNetVal(fundCode, today);
            while (latest == null && startDate.isBefore(today)){
                today = today.minusDays(i);
                latest = fundMapper.getUnitNetVal(fundCode,today);
            }
            if (latest == null){
                latest = unitNetVal;
            }
            //公式：(当前单位净值 - 一周/X月/X年前单位净值) * 100 / 一周/X月/X年前单位净值
            //找出一周前的单位净值，如果没有，则往前推，如果时间早于成立日期，则单位净值为成立时的单位净值
            Double week = fundMapper.getUnitNetVal(fundCode, weekAgo);
            while (week == null && startDate.isBefore(weekAgo)) {
                weekAgo = weekAgo.minusDays(i);
                week = fundMapper.getUnitNetVal(fundCode, weekAgo);
            }
            if (week == null) {
                week = unitNetVal;
            }
            String weekChg = decimalFormat.format((latest - week) * 100 / week);
            fundVo.setWeekChg(weekChg + "%");

            //找出一个月前的单位净值，如果没有，则往前推，如果时间早于成立日期，则单位净值为成立时的单位净值
            Double oneMonth = fundMapper.getUnitNetVal(fundCode,oneMonthAgo);
            while (oneMonth == null && startDate.isBefore(oneMonthAgo)){
                oneMonthAgo = oneMonthAgo.minusDays(i);
                oneMonth = fundMapper.getUnitNetVal(fundCode,oneMonthAgo);
            }
            if (oneMonth == null){
                oneMonth = unitNetVal;
            }
            String monthChg = decimalFormat.format((latest - oneMonth) * 100 / oneMonth);
            fundVo.setMonthChg(monthChg + "%");

            //找出三个月前的单位净值，如果没有，则往前推，如果时间早于成立日期，则单位净值为成立时的单位净值
            Double threeMonth = fundMapper.getUnitNetVal(fundCode,threeMonthAgo);
            while (threeMonth == null && startDate.isBefore(threeMonthAgo)){
                threeMonthAgo = threeMonthAgo.minusDays(i);
                threeMonth = fundMapper.getUnitNetVal(fundCode,threeMonthAgo);
            }
            if (threeMonth == null){
                threeMonth = unitNetVal;
            }
            String threeMonthChg = decimalFormat.format((latest - threeMonth) * 100 / threeMonth);
            fundVo.setThreeMonthChg(threeMonthChg + "%");

            //找出一年前的单位净值，如果没有，则往前推，如果时间早于成立日期，则单位净值为成立时的单位净值
            Double oneYear = fundMapper.getUnitNetVal(fundCode,oneYearAgo);
            while (oneYear == null && startDate.isBefore(oneYearAgo)) {
                oneYearAgo = oneYearAgo.minusDays(i);
                oneYear = fundMapper.getUnitNetVal(fundCode,oneYearAgo);
            }
            if (oneYear == null){
                oneYear = unitNetVal;
            }
            String oneYearChg = decimalFormat.format((latest - oneYear) * 100 / oneYear);
            fundVo.setYearChg(oneYearChg + "%");

            //找出两年前的单位净值，如果没有，则往前推
            Double twoYear = fundMapper.getUnitNetVal(fundCode,twoYearAgo);
            while (twoYear == null && startDate.isBefore(twoYearAgo)) {
                twoYearAgo = twoYearAgo.minusDays(i);
                twoYear = fundMapper.getUnitNetVal(fundCode,twoYearAgo);
            }
            if (twoYear == null) {
                twoYear = unitNetVal;
            }
            String twoYearChg = decimalFormat.format((latest - twoYear) * 100 / twoYear);
            fundVo.setTwoYearChg(twoYearChg + "%");

            //找出三年前的单位净值，如果没有，则往前推
            Double threeYear = fundMapper.getUnitNetVal(fundCode,threeYearAgo);
            while (threeYear == null && startDate.isBefore(threeYearAgo)) {
                threeYearAgo = threeYearAgo.minusDays(i);
                threeYear = fundMapper.getUnitNetVal(fundCode,threeYearAgo);
            }
            if (threeYear == null){
                threeYear = unitNetVal;
            }
            String threeYearChg = decimalFormat.format((latest - threeYear) * 100 / threeYear);
            fundVo.setThreeYearChg(threeYearChg + "%");
            //找出今年1月1日的单位净值，如果没有，则往后推，如果时间早于成立日期，则单位净值为成立时的单位净值
            int year = today.getYear();
            LocalDate date = LocalDate.of(year,1,1);
            Double thisYear = fundMapper.getUnitNetVal(fundCode, date);
            while (thisYear == null && startDate.isBefore(date)){
                date = date.plusDays(i);
                thisYear = fundMapper.getUnitNetVal(fundCode,date);
            }
            if (thisYear == null){
                thisYear = unitNetVal;
            }
            String thisYearChg = decimalFormat.format((latest - thisYear) * 100 / thisYear);
            fundVo.setThisYearChg(thisYearChg + "%");


            //成立来，即最早的一条记录
            String establishedChg = decimalFormat.format((latest - unitNetVal) * 100 / unitNetVal);
            fundVo.setEstablishedChg(establishedChg + "%");

            list.add(fundVo);
        }
        FundDto dto = new FundDto();
        dto.setRecords(list);
        dto.setTotal(total);

        return dto;
    }
}
