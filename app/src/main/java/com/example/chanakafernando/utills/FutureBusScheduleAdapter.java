package com.example.chanakafernando.utills;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.chanakafernando.activities.R;
import com.example.chanakafernando.other.FutureBusSchedule;


import java.util.List;

public class FutureBusScheduleAdapter extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<FutureBusSchedule> bScheduleList;
    private String[] bgColors;

    public FutureBusScheduleAdapter(Activity activity, List<FutureBusSchedule> schedules) {
        this.activity = activity;
        this.bScheduleList = schedules;
        bgColors = activity.getApplicationContext().getResources().getStringArray(R.array.movie_serial_bg);
    }

    @Override
    public int getCount() {
        return bScheduleList.size();
    }

    @Override
    public Object getItem(int location) {
        return bScheduleList.get(location);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = inflater.inflate(R.layout.posible_bus_list, null);

        TextView tvSlocation =(TextView) convertView.findViewById(R.id.tvBStartPlace);
        TextView tvBSTime =(TextView) convertView.findViewById(R.id.tvBstime);
        TextView tvBElocation =(TextView) convertView.findViewById(R.id.tvBusELocation);
        TextView tvBEtime =(TextView)convertView.findViewById(R.id.tvBEtime);
        TextView tvBusId=(TextView) convertView.findViewById(R.id.tvBusId);
        TextView tvBusRoute =(TextView)convertView.findViewById(R.id.tvBusRoute);
        TextView tvFrom=(TextView)convertView.findViewById(R.id.tvBFrom);
        TextView tvTo =(TextView)convertView.findViewById(R.id.tvBTo);


        tvBusId.setText(bScheduleList.get(position).busId);
        tvSlocation.setText(bScheduleList.get(position).sLocation);
        tvBElocation.setText(bScheduleList.get(position).eLocation);
        tvBSTime.setText(bScheduleList.get(position).sTime);
        tvBEtime.setText(bScheduleList.get(position).eTime);
        tvBusRoute.setText(bScheduleList.get(position).bRouteNo);


        tvFrom.setText("FROM");
        tvTo.setText("TO");


     /*

        String color = bgColors[position % bgColors.length];
        serial.setBackgroundColor(Color.parseColor(color));
*/
        return convertView;
    }


}
