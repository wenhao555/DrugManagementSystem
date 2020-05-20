package com.example.drugmanagementsystem;

import org.litepal.crud.DataSupport;

import java.util.List;

public class PurchaseFroms extends DataSupport {
    private String ypbh;
    private int cgsl;


    public void setCgsl(int cgsl) {
        this.cgsl = cgsl;
    }


    public void setYpbh(String ypbh) {
        this.ypbh = ypbh;
    }


    public int getCgsl() {
        return cgsl;
    }

    public String getYpbh() {
        return ypbh;
    }
}
