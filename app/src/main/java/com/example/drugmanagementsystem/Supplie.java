package com.example.drugmanagementsystem;

import org.litepal.crud.DataSupport;

public class Supplie extends DataSupport {
    private String gysbh;
    private String gysmc;
    private String gysdh;

    public String  getGysbh() {
        return gysbh;
    }

    public void setGysbh(String gysbh) {
        this.gysbh = gysbh;
    }

    public String getGysdh() {
        return gysdh;
    }

    public void setGysdh(String gysdh) {
        this.gysdh = gysdh;
    }

    public String getGysmc() {
        return gysmc;
    }

    public void setGysmc(String gysmc) {
        this.gysmc = gysmc;
    }
}
