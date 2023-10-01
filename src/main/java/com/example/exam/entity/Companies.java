package com.example.exam.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 公司表
 * </p>
 *
 * @author ZhongZhen
 * @since 2023-10-01
 */
@Data
@TableName("companies")
public class Companies implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 公司代码
     */
    @TableId("orgUniCode")
    private Long orgUniCode;

    /**
     * 公司中文名
     */
    @TableField("orgChiName")
    private String orgChiName;

    /**
     * 经营类型
     */
    @TableField("induSmaPar")
    private String induSmaPar;

    /**
     * 创始人
     */
    @TableField("orgDele")
    private String orgDele;

    /**
     * 注册金额
     */
    @TableField("regCap")
    private String regCap;

    /**
     * 创建时间
     */
    @TableField("orgEstDate")
    private String orgEstDate;
}
