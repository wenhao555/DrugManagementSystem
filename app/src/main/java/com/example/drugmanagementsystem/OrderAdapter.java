package com.example.drugmanagementsystem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.litepal.crud.DataSupport;

import java.util.List;

public class OrderAdapter extends ArrayAdapter<PurchaseFroms> {
    private int resourceId;
    public OrderAdapter(Context context, int textViewResourceId, List<PurchaseFroms> objects){
        super(context,textViewResourceId,objects);
        resourceId = textViewResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        PurchaseFroms purchaseFroms = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
        TextView ypbh = (TextView) view.findViewById(R.id.ypbh_item);
        TextView ypmc = (TextView) view.findViewById(R.id.ypmc_item);
        TextView cgsl = (TextView) view.findViewById(R.id.cgsl_item);
        ypbh.setText("编号:"+purchaseFroms.getYpbh());
        List<Drug> drugs = DataSupport.findAll(Drug.class);
        for (Drug drug:drugs){
            if (drug.getYpbh().equals(purchaseFroms.getYpbh())){
                ypmc.setText("名称:"+drug.getYpmc());
            }
        }

        cgsl.setText("数量:"+String.valueOf(purchaseFroms.getCgsl()));
        return view;
    }
}
