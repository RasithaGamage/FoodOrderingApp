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
            TextView place;
            ImageView profile_pic;
            TextView member_name;
            TextView status;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            ViewHolder holder = null;

            LayoutInflater mInflater = (LayoutInflater) context
                    .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            if (convertView == null) {
                convertView = mInflater.inflate(R.layout.list_item, null);
                holder = new ViewHolder();

                holder.place = (TextView) convertView.findViewById(R.id.place);

                holder.member_name = (TextView) convertView.findViewById(R.id.member_name);

                holder.profile_pic = (ImageView) convertView.findViewById(R.id.profile_pic);

                holder.status = (TextView) convertView.findViewById(R.id.status);

                RowItem row_pos = rowItems.get(position);

                holder.place.setText(row_pos.getPlace());
                holder.profile_pic.setImageResource(row_pos.getProfile_pic_id());
                holder.member_name.setText(row_pos.getMember_name());
                holder.status.setText(row_pos.getStatus());


                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            return convertView;
        }



}
