package com.wmli.eureka.consumer.model;

import lombok.Data;

import java.io.Serializable;

/**
 * 测试实体
 *
 * @author yingmuhuadao
 * @date 2019/5/23
 */
@Data
public class ModelDO implements Serializable {
    /**
     * 序列化ID
     */
    private static final long serialVersionUID = 6857550816289382525L;

    private String token;

    private String xyid;

    private String operate;

    private String unitType;

    private String sysVer;

}
