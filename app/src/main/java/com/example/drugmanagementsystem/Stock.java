package com.example.drugmanagementsystem;

import org.litepal.crud.DataSupport;

public class Stock extends DataSupport {
    private String ypbh;
    private int ypsl;

    public String getYpbh() {
        return ypbh;
    }

    public int getYpsl() {
        return ypsl;
    }

    public void setYpbh(String ypbh) {
        this.ypbh = ypbh;
    }

    public void setYpsl(int ypsl) {
        this.ypsl = ypsl;
    }
}
