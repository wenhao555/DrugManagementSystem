package com.example.drugmanagementsystem;

import org.litepal.crud.DataSupport;

public class SalesFrom extends DataSupport {
    private String ypbh;
    private int xssl;

    public String getYpbh() {
        return ypbh;
    }

    public int getXssl() {
        return xssl;
    }

    public void setYpbh(String ypbh) {
        this.ypbh = ypbh;
    }

    public void setXssl(int xssl) {
        this.xssl = xssl;
    }
}
