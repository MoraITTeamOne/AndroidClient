package com.example.chanakafernando.utills;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.chanakafernando.activities.R;
import com.example.chanakafernando.other.PosibleTrainList;

import java.util.List;
import java.util.StringTokenizer;

/**
 * Created by Chanaka Fernando on 3/20/2017.
 */
public class PosibleTrainListAdapter extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<PosibleTrainList> pTrainList;
    private String[] bgColors;

    public PosibleTrainListAdapter(Activity activity, List<PosibleTrainList> pTrainList) {
        this.activity = activity;
        this.pTrainList = pTrainList;
        bgColors = activity.getApplicationContext().getResources().getStringArray(R.array.movie_serial_bg);
    }

    @Override
    public int getCount() {
        return pTrainList.size();
    }

    @Override
    public Object getItem(int location) {
        return pTrainList.get(location);
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
            convertView = inflater.inflate(R.layout.posible_train_row, null);

        TextView tvTrainName =(TextView) convertView.findViewById(R.id.tvTrainName);
        TextView tvSlocation =(TextView) convertView.findViewById(R.id.tvSlocation);
        TextView tvSTime =(TextView) convertView.findViewById(R.id.tvStime);
        TextView tvElocation =(TextView) convertView.findViewById(R.id.tvEloc);
        TextView tvEtime =(TextView)convertView.findViewById(R.id.tvEtime);
        TextView tvTrainId=(TextView) convertView.findViewById(R.id.tvTrainId);
        TextView tvType =(TextView)convertView.findViewById(R.id.tvType);
        TextView tvFrom =(TextView) convertView.findViewById(R.id.tvFrom);
        TextView tvTo =(TextView) convertView.findViewById(R.id.tvTo);


        tvTrainName.setText(pTrainList.get(position).trinName);
        tvTrainId.setText(pTrainList.get(position).trainId);
        tvElocation.setText(pTrainList.get(position).eLocation);
        tvEtime.setText(pTrainList.get(position).eTime);
        tvSlocation.setText(pTrainList.get(position).sLocation);
        tvSTime.setText(pTrainList.get(position).sTime);
        tvType.setText(pTrainList.get(position).type);
        tvFrom.setText("FROM");
        tvTo.setText("TO");


     /*

        String color = bgColors[position % bgColors.length];
        serial.setBackgroundColor(Color.parseColor(color));
*/
        return convertView;
    }


}
