package com.apace.ydimall.bean;

import java.io.Serializable;

/**
 * <pre>
 *     author : HJianFei
 *     e-mail : 190766172@qq.com
 *     time   : 2017-08-09
 *     desc   :
 *     version: 1.0
 * </pre>
 */

public class ResultBean implements Serializable {

    public int code;
    public String msg;

    public ResultBean(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ResultBean() {
    }

    @Override
    public String toString() {
        return "ResultCode{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                '}';
    }
}
