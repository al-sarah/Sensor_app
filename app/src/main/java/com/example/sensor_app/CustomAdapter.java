package com.example.sensor_app;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class CustomAdapter extends ArrayAdapter<Member> {
    private Activity context;
    private List<Member> calclist;

    public CustomAdapter(Activity context,List<Member> calclist) {
        super(context, R.layout.sample_view, calclist);
        this.context = context;
        this.calclist = calclist;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater=context.getLayoutInflater();
        View view=layoutInflater.inflate(R.layout.sample_view,null,true);
        Member calc=calclist.get(position);
        TextView t1=view.findViewById(R.id.Value1id);
        TextView t2=view.findViewById(R.id.Value2id);
        TextView t3=view.findViewById(R.id.resultid);
        t1.setText("Value of X: "+calc.getX());
        t2.setText("Value of Y: "+calc.getY());
        t3.setText("Value of Z:"+calc.getZ());

        return view;
    }


}
