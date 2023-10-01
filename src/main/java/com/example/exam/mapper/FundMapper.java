package com.example.exam.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.exam.entity.Fund;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDate;
import java.util.List;

/**
 * <p>
 * 基金表 Mapper 接口
 * </p>
 *
 * @author ZhongZhen
 * @since 2023-10-01
 */
@Mapper
public interface FundMapper extends BaseMapper<Fund> {

    @Select("""
            SELECT FUND_CODE, FUND_SHORT_NAME, MAX(END_DATE) AS END_DATE, MAX(UNIT_NET_VAL) AS UNIT_NET_VAL
            FROM fund
            GROUP BY FUND_CODE, FUND_SHORT_NAME
            ORDER BY END_DATE
            LIMIT #{pageNo},#{pageSize}
            """)
    List<Fund> getPageDateASC(@Param("pageNo") int pageNo,
                                    @Param("pageSize") int pageSize);
    @Select("""
            SELECT FUND_CODE, FUND_SHORT_NAME, MAX(END_DATE) AS END_DATE, MAX(UNIT_NET_VAL) AS UNIT_NET_VAL
            FROM fund
            GROUP BY FUND_CODE, FUND_SHORT_NAME
            ORDER BY END_DATE desc
            LIMIT #{pageNo},#{pageSize}
            """)
    List<Fund> getPageDateDESC(@Param("pageNo") int pageNo,
                                     @Param("pageSize") int pageSize);
    @Select("""
            SELECT FUND_CODE, FUND_SHORT_NAME, MAX(END_DATE) AS END_DATE, MAX(UNIT_NET_VAL) AS UNIT_NET_VAL
            FROM fund
            GROUP BY FUND_CODE, FUND_SHORT_NAME
            ORDER BY UNIT_NET_VAL
            LIMIT #{pageNo},#{pageSize}
            """)
    List<Fund> getPageUnitNetValASC(@Param("pageNo") int pageNo,
                                    @Param("pageSize") int pageSize);
    @Select("""
            SELECT FUND_CODE, FUND_SHORT_NAME, MAX(END_DATE) AS END_DATE, MAX(UNIT_NET_VAL) AS UNIT_NET_VAL
            FROM fund
            GROUP BY FUND_CODE, FUND_SHORT_NAME
            ORDER BY UNIT_NET_VAL desc
            LIMIT #{pageNo},#{pageSize}
            """)
    List<Fund> getPageUnitNetValDESC(@Param("pageNo") int pageNo,
                                     @Param("pageSize") int pageSize);

    @Select("""
            select count(distinct FUND_CODE) from fund
            """)
    Long getTotal();

    @Select("""
            select * from fund
            where FUND_CODE=#{fundCode} AND END_DATE=#{endDate}
            """)
    Fund getByCode(@Param("fundCode") Long fundCode, @Param("endDate") LocalDate endDate);

    @Select("""
            SELECT UNIT_NET_VAL
            FROM fund
            WHERE FUND_CODE =#{fundCode}
              AND END_DATE = #{endDate};
            """)
    Double getUnitNetVal(@Param("fundCode") Long fundCode, @Param("endDate") LocalDate endDate);

    @Select("""
            SELECT *
            FROM fund
            where FUND_CODE=#{fundCode}
            ORDER BY END_DATE
            LIMIT 0,1
            """)
    Fund getMinDate(@Param("fundCode") Long fundCode);

}
