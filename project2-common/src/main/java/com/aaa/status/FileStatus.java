package com.aaa.status;

/**
 * @Company: AAA软件教育
 * @Author: Cancer:栗仁杰
 * @Date: Created in 2020/7/17 9:48
 * @Description:
 */
public enum  FileStatus {
    UPLOAD_SUCCESS("25001", "上传成功"),
    DOWNLOAD_SUCCESS("25001", "下载成功"),
    UPLOAD_FAILED("15001", "上传失败"),
    DOWNLOAD_FAILED("15001", "下载失败"),



    TEST("11111", "测试一下");


    FileStatus(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private String code;
    private String msg;

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

}
