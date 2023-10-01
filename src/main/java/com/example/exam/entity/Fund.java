package com.example.exam.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * <p>
 * 基金表
 * </p>
 *
 * @author ZhongZhen
 * @since 2023-10-01
 */
@Data
@TableName("fund")
public class Fund implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 基金代码
     */
    @TableField("FUND_CODE")
    private Long fundCode;

    /**
     * 基金短期名称
     */
    @TableField("FUND_SHORT_NAME")
    private String fundShortName;

    /**
     * 结束日期
     */
    @TableField("END_DATE")
    private LocalDate endDate;

    /**
     * 单位净值
     */
    @TableField("UNIT_NET_VAL")
    private Double unitNetVal;
}
