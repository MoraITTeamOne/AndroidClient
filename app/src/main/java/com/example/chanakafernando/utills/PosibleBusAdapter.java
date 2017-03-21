package com.example.chanakafernando.utills;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.chanakafernando.activities.R;
import com.example.chanakafernando.other.PosibleBusList;
import com.example.chanakafernando.other.PosibleTrainList;

import java.util.List;

/**
 * Created by Chanaka Fernando on 3/20/2017.
 */
public class PosibleBusAdapter extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<PosibleBusList> pBusList;
    private String[] bgColors;

    public PosibleBusAdapter(Activity activity, List<PosibleBusList> pBusList) {
        this.activity = activity;
        this.pBusList = pBusList;
        bgColors = activity.getApplicationContext().getResources().getStringArray(R.array.movie_serial_bg);
    }

    @Override
    public int getCount() {
        return pBusList.size();
    }

    @Override
    public Object getItem(int location) {
        return pBusList.get(location);
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


        tvBusId.setText(pBusList.get(position).busId);
        tvSlocation.setText(pBusList.get(position).sLocation);
        tvBElocation.setText(pBusList.get(position).eLocation);
        tvBSTime.setText(pBusList.get(position).sTime);
        tvBEtime.setText(pBusList.get(position).eTime);
        tvBusRoute.setText(pBusList.get(position).bRouteNo);


        tvFrom.setText("FROM");
        tvTo.setText("TO");


     /*

        String color = bgColors[position % bgColors.length];
        serial.setBackgroundColor(Color.parseColor(color));
*/
        return convertView;
    }


}
