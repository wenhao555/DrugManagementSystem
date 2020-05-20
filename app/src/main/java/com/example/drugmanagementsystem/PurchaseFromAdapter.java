package com.example.drugmanagementsystem;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.litepal.crud.DataSupport;

import java.util.List;

public class PurchaseFromAdapter extends ArrayAdapter<PurchaseFrom> {
    private int resourceId;
    public PurchaseFromAdapter(Context context, int textViewResourceId, List<PurchaseFrom> objects){
        super(context,textViewResourceId,objects);
        resourceId = textViewResourceId;
    }

    @Override
    public View getView(int position,View convertView,ViewGroup parent) {
        PurchaseFrom purchaseFrom = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
        TextView ypbh = (TextView) view.findViewById(R.id.ypbh_item);
        TextView ypmc = (TextView) view.findViewById(R.id.ypmc_item);
        TextView cgsl = (TextView) view.findViewById(R.id.cgsl_item);
        ypbh.setText("编号:"+purchaseFrom.getYpbh());
        List<Drug> drugs = DataSupport.findAll(Drug.class);
        for (Drug drug:drugs){
            if (drug.getYpbh().equals(purchaseFrom.getYpbh())){
                ypmc.setText("名称:"+drug.getYpmc());
            }
        }

        cgsl.setText("数量:"+String.valueOf(purchaseFrom.getCgsl()));
        return view;
    }
}
