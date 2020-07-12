package com.yao.memorandum.entity;

import java.io.Serializable;

public class DataEntity implements Serializable {

    /**
     * 日期-桌面显示
     */
    private String date;
    /**
     * 备忘信息
     */
    private String msg;
    /**
     * 备注
     */
    private String bz;

    /**
     * 标记完成 0：默认，1：置顶，2：完成；
     */
    private int mark;
    /**
     * 创建时间
     */
    private Long cDt;
    /**
     * 更新时间
     */
    private Long uDt;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getBz() {
        return bz;
    }

    public void setBz(String bz) {
        this.bz = bz;
    }

    public Long getcDt() {
        return cDt;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    public void setcDt(Long cDt) {
        this.cDt = cDt;
    }

    public Long getuDt() {
        return uDt;
    }

    public void setuDt(Long uDt) {
        this.uDt = uDt;
    }
}
