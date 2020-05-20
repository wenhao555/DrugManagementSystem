package com.example.drugmanagementsystem;

import org.litepal.crud.DataSupport;

public class Drug extends DataSupport {
    private String ypbh;
    private String ypmc;
    private double ypjj;
    private double ypsj;
    private String gysbh;


    public String getYpbh() {
        return ypbh;
    }
    public String getYpmc() {
        return ypmc;
    }

    public double getYpjj() {
        return ypjj;
    }

    public double getYpsj() {
        return ypsj;
    }
    public String getGysbh() {
        return gysbh;
    }




    public void setYpbh(String ypbh) {
        this.ypbh = ypbh;
    }

    public void setYpmc(String ypmc) {
        this.ypmc = ypmc;
    }

    public void setYpjj(double ypjj) {
        this.ypjj = ypjj;
    }

    public void setYpsj(double ypsj) {
        this.ypsj = ypsj;
    }

    public void setGysbh(String gysbh) {
        this.gysbh = gysbh;
    }
}
