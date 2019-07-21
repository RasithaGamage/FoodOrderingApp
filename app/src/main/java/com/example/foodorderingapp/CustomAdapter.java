package com.example.foodorderingapp;

import java.util.List;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Rasitha on 3/24/2018.
 */
public class CustomAdapter extends BaseAdapter{

    Context context;
    List<RowItem> rowItems;

    CustomAdapter(Context context, List<RowItem> rowItems) {
        this.context = context;
        this.rowItems = rowItems;
    }

    @Override
    public int getCount() {
        {
            return rowItems.size();
        }
    }

    @Override
    public Object getItem(int position) {
            return rowItems.get(position);
    }

    @Override
    public long getItemId(int position) {

            return rowItems.indexOf(getItem(position));
    }


        private class ViewHolder {
            TextView num;
            ImageView pic;
            TextView pro_name;
            TextView details;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            ViewHolder holder = null;

            LayoutInflater mInflater = (LayoutInflater) context
                    .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            if (convertView == null) {
                convertView = mInflater.inflate(R.layout.list_item, null);
                holder = new ViewHolder();

                holder.num = (TextView) convertView.findViewById(R.id.num);
                holder.pro_name = (TextView) convertView.findViewById(R.id.pro_name);
                holder.pic = (ImageView) convertView.findViewById(R.id.pic);
                holder.details = (TextView) convertView.findViewById(R.id.details);

                RowItem row_pos = rowItems.get(position);

                holder.num.setText(Integer.toString(row_pos.getNum()));
                holder.pic.setImageResource(R.drawable.bg1);
                holder.pro_name.setText(row_pos.getMember_name());
                holder.details.setText(row_pos.getDetails());


                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            return convertView;
        }



}
