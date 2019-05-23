/**
 * xinYan.com.cn Inc.
 * Copyright (c) 2011-2020 All Rights Reserved.
 */
package com.wmli.eureka.consumer.utils;

/**
 * 统一日志信息格式
 * <p>
 * 请求日志信息格式
 * 请求日志信息格式 - 多个
 * 响应日志信息格式
 * 响应日志信息格式 - 耗时统计
 * 异常日志信息格式
 * 异常日志信息格式 - 请求信息
 * 执行日志信息格式
 * </p>
 *
 * @author thank_wd
 * @version 5.0
 * @date 2017/1/10
 */
public class LogFormatUtil {

    /**
     * 公共日志信息格式
     */
    private static final String LOG_CALL = "call {} ";
    private static final String LOG_SUCCESS = "success {} ";
    private static final String LOG_FAILED = "failed {} ";
    private static final String LOG_EXECUTE = "execute {} ";
    private static final String LOG_WARN = "warn {}";

    private static final String LOG_PARAM = "PARAM:{} ";
    private static final String LOG_TIME = "耗时:";
    private static final String LOG_RESULT = "RESULT:{} ";
    private static final String LOG_EXCEPTION = "EXCEPTION:{} ";

    /**
     * 请求日志信息格式
     */
    public static String call() {

        return LOG_CALL.concat(LOG_PARAM);
    }

    /**
     * 请求日志信息格式 - 多个
     *
     * @param param 请求参数个数,大于1
     */
    public static String call(int param) {

        String callString = "";

        for (int i = 1; i < param; i++) {
            callString = callString.concat(",{}");
        }
        return call().concat(callString);
    }

    /**
     * 响应日志信息格式
     */
    public static String success() {

        return LOG_SUCCESS.concat(LOG_RESULT);
    }

    /**
     * 响应日志信息格式 - 耗时统计
     */
    public static String success(long startTime) {

        return LOG_SUCCESS.concat(LOG_TIME).concat((System.currentTimeMillis() - startTime) + ", ").
                concat(LOG_RESULT);
    }

    /**
     * 响应日志信息格式 - 耗时统计
     */
    public static String successTime(long startTime) {

        return LOG_SUCCESS.concat(LOG_TIME).concat((System.currentTimeMillis() - startTime) + "");
    }

    /**
     * 异常日志信息格式
     */
    public static String failed() {

        return LOG_FAILED.concat(LOG_EXCEPTION);
    }

    /**
     * 异常日志信息格式 - 请求信息
     */
    public static String failedParam() {

        return LOG_FAILED.concat(LOG_PARAM).concat(LOG_EXCEPTION);
    }

    /**
     * 执行日志信息格式
     */
    public static String execute() {

        return LOG_EXECUTE.concat(LOG_PARAM);
    }

    /**
     * 执行日志信息格式
     */
    public static String warn(int param) {
        String callString = "";

        for (int i = 1; i < param; i++) {
            callString = callString.concat("{}");
        }
        return LOG_WARN.concat(callString);
    }

    /**
     * 执行日志信息格式
     */
    public static String execute(int param) {

        String callString = "";

        for (int i = 1; i < param; i++) {
            callString = callString.concat(",{}");
        }
        return execute().concat(callString);
    }
}
