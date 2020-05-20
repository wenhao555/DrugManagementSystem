package com.example.drugmanagementsystem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.litepal.crud.DataSupport;

import java.util.List;

public class SellFromAdapter extends ArrayAdapter<SalesFrom> {
    private int resourceId;
    public SellFromAdapter(Context context, int textViewResourceId, List<SalesFrom> objects){
        super(context,textViewResourceId,objects);
        resourceId = textViewResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        SalesFrom salesFrom = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
        TextView ypbh = (TextView) view.findViewById(R.id.ypbh_item1);
        TextView ypmc = (TextView) view.findViewById(R.id.ypmc_item1);
        TextView cgsl = (TextView) view.findViewById(R.id.cgsl_item1);
        TextView ypsj = view.findViewById(R.id.ypsj_item1);
        ypbh.setText("编号:"+salesFrom.getYpbh());
        List<Drug> drugs = DataSupport.findAll(Drug.class);
        for (Drug drug:drugs){
            if (drug.getYpbh().equals(salesFrom.getYpbh())){
                ypmc.setText("名称:"+drug.getYpmc());
                ypsj.setText("单价:"+drug.getYpsj());
            }
        }

        cgsl.setText("数量:"+String.valueOf(salesFrom.getXssl()));
        return view;
    }
}
