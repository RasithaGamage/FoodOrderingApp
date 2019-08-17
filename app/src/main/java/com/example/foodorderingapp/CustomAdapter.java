package com.example.foodorderingapp;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.support.annotation.RequiresApi;
import android.util.ArraySet;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.auth.data.model.Resource;

/**
 * Created by Rasitha on 3/24/2018.
 */
public class CustomAdapter extends BaseAdapter{

    Context context;
    List<RowItem> rowItems;
    LayoutInflater layoutInflater;
    Set<View> viewSet;
    List <CartItem> shoppingCartArray;

    @RequiresApi(api = Build.VERSION_CODES.M)
    CustomAdapter(Context context, List<RowItem> rowItems) {
        this.context = context;
        this.rowItems = rowItems;
        viewSet = new ArraySet<View>();
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
            Button button;
            Button button_plus;
            Button button_minus;
            TextView num;
            ImageView pic;
            TextView pro_name;
            TextView details;
            TextView count;
            int itemCount;
        }



        @SuppressLint("ResourceType")
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {


            //LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

            if (convertView == null) {
                layoutInflater = LayoutInflater.from(this.context);
                if(context.getClass().getSimpleName().toString().trim().equals("FoodList")){convertView = layoutInflater.inflate(R.layout.list_item, null);}
                if(context.getClass().getSimpleName().toString().trim().equals("SnackList")){convertView = layoutInflater.inflate(R.layout.list_item_snack, null);}
                if(context.getClass().getSimpleName().toString().trim().equals("Cart")){convertView = layoutInflater.inflate(R.layout.list_item_snack, null);}
            }

                ViewHolder holder = null;
                holder = new ViewHolder();
                convertView.setClickable(true);
//               final ViewHolder finalHolder = holder;
//                convertView.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                       // Toast.makeText(context,""+ finalHolder.num.getText().toString(),Toast.LENGTH_SHORT).show();
//                    }
//                });

                shoppingCartArray = new ArrayList<>();

                holder.num = (TextView) convertView.findViewById(R.id.num);
                holder.pro_name = (TextView) convertView.findViewById(R.id.pro_name);
                holder.pic = (ImageView) convertView.findViewById(R.id.pic);
                holder.details = (TextView) convertView.findViewById(R.id.details);
                holder.count = (TextView) convertView.findViewById(R.id.count);
                holder.button = convertView.findViewById(R.id.btnOrder);
                holder.button_plus = convertView.findViewById(R.id.plus_btn);
                holder.button_minus = convertView.findViewById(R.id.minus_btn);
                final ViewHolder finalHolder1 = holder;
                holder.button.setTag(finalHolder1.num.getText());
                holder.itemCount = 0;
                final ViewHolder finalHolder5 = holder;
                final ViewHolder finalHolder6 = holder;

                if(!context.getClass().getSimpleName().toString().trim().equals("FoodList")){
                    holder.button_plus.setTag(finalHolder1.num.getText());
                    holder.button_minus.setTag(finalHolder1.num.getText());
                    final ViewHolder finalHolder2 = holder;
                    final ViewHolder finalHolder3 = holder;
                    holder.button_plus.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            finalHolder3.itemCount ++;
//                        Toast.makeText(context,Integer.toString(finalHolder3.itemCount),Toast.LENGTH_SHORT).show();
                            finalHolder2.count.setText(Integer.toString(finalHolder3.itemCount));
                        }
                    });

                    final ViewHolder finalHolder = holder;
                    final ViewHolder finalHolder4 = holder;
                    holder.button_minus.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            finalHolder4.itemCount --;
//                        Toast.makeText(context,Integer.toString(finalHolder4.itemCount),Toast.LENGTH_SHORT).show();
                            finalHolder.count.setText(Integer.toString(finalHolder4.itemCount));
                        }
                    });
                }
                holder.button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        String button_text = finalHolder1.button.getText().toString();
                       // Toast.makeText(context,""+ button_text,Toast.LENGTH_SHORT).show();

                        if(button_text.equals("Order")){
                            finalHolder1.button.setText("Cancel");
                            finalHolder1.button.getBackground().setColorFilter(finalHolder1.button.getContext().getResources().getColor(R.color.colorAccent), PorterDuff.Mode.MULTIPLY);
                            Toast.makeText(context,"You ordered"+ finalHolder1.pro_name.getText().toString(),Toast.LENGTH_SHORT).show();

                            //int pro_id, String pro_name, String cat, String sub_cat, String brand, int qty, double price, String details, String img,int buying_amount

                           String img_string = drawableToBase64(finalHolder5.pic);
                            CartItem c_item = new CartItem(Integer.parseInt(finalHolder5.num.getText().toString()),finalHolder5.pro_name.getText().toString(),"","","",0,0,finalHolder5.details.getText().toString(),img_string,Integer.parseInt(finalHolder5.count.getText().toString()));
                            shoppingCartArray.add(c_item);
//                            for(CartItem i:shoppingCartArray
//                            ) {
//                                System.out.println(i.getItemId()+"<------------------------------getItemId");
//                                System.out.println(i.getItemAmount()+"<------------------------------getItemAmount");
//                            }
                            ShoppingCart s = ShoppingCart.getInstance();
                            s.setShoppingCartArray(shoppingCartArray);

                        }
                        if(button_text.equals("Cancel")){
                            finalHolder1.button.setText("Order");
                            finalHolder1.button.getBackground().setColorFilter(finalHolder1.button.getContext().getResources().getColor(R.color.colorPrimary), PorterDuff.Mode.MULTIPLY);
                            Toast.makeText(context,"Order canceled",Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                RowItem row_pos = rowItems.get(position);

                holder.num.setText(Integer.toString(row_pos.getNum()));
                byte[]  ds =  Base64.decode(row_pos.getPic(), Base64.DEFAULT);
                Bitmap decodedByte = BitmapFactory.decodeByteArray(ds, 0, ds.length);
                holder.pic.setImageBitmap(decodedByte);
                //holder.pic.setImageResource(R.id.logo);
                holder.pro_name.setText(row_pos.getPro_name());
                holder.details.setText(row_pos.getDetails());
                convertView.setTag(holder);

            if(context.getClass().getSimpleName().toString().trim().equals("Cart")){
                holder.count.setText(Integer.toString(row_pos.getAmount()));
            }


            return convertView;
        }

public void addtoCart(String itemID,String amount){


    }

public String drawableToBase64(ImageView d){
    BitmapDrawable bitmapOrg = (BitmapDrawable) d.getDrawable();
    Bitmap bm = bitmapOrg.getBitmap();
    ByteArrayOutputStream bao = new ByteArrayOutputStream();
    bm.compress(Bitmap.CompressFormat.JPEG, 100, bao);
    byte [] ba = bao.toByteArray();
    String ba1=Base64.encodeToString(ba,Base64.DEFAULT);

    return ba1;
}

}
