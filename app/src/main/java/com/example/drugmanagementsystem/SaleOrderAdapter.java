package com.example.drugmanagementsystem;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.litepal.crud.DataSupport;

import java.util.List;

public class SaleOrderAdapter extends ArrayAdapter<SalesFroms> {
    private int resourceId;
    public SaleOrderAdapter(Context context, int textViewResourceId, List<SalesFroms> objects){
        super(context,textViewResourceId,objects);
        resourceId = textViewResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        SalesFroms salesFroms = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
        TextView ypbh = (TextView) view.findViewById(R.id.ypbh_item2);
        TextView ypmc = (TextView) view.findViewById(R.id.ypmc_item2);
        TextView cgsl = (TextView) view.findViewById(R.id.cgsl_item2);
        TextView ypsj = view.findViewById(R.id.ypsj_item2);
        TextView zjg = view.findViewById(R.id.zjg_item2);
        double sum = 0;
        Log.d("SaleOrderAdapter", "看这里："+salesFroms.getYpbh());

        List<Drug> drugs = DataSupport.findAll(Drug.class);
        for (Drug drug:drugs){
            if (drug.getYpbh().equals(salesFroms.getYpbh())){
                ypmc.setText("名称:"+drug.getYpmc());
                sum = sum+drug.getYpsj()*salesFroms.getXssl();
                ypsj.setText("单价:"+drug.getYpsj());

            }
        }
        ypbh.setText("编号:"+salesFroms.getYpbh());
        zjg.setText("该药品总价:"+sum);
        cgsl.setText("数量:"+String.valueOf(salesFroms.getXssl()));
        return view;
    }
}