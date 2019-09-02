package com.example.foodorderingapp;

import java.io.ByteArrayOutputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
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
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Rasitha on 3/24/2018.
 */
public class CustomAdapter extends BaseAdapter{

    Context context;
    List<RowItem> rowItems;
    LayoutInflater layoutInflater;
    Set<View> viewSet;
    List <CartItem> shoppingCartArray;
    TextView tv;

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
            TextView price;
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
                if(context.getClass().getSimpleName().toString().trim().equals("Orders")){convertView = layoutInflater.inflate(R.layout.list_item_snack, null);}
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
                holder.price = (TextView) convertView.findViewById(R.id.price);
                holder.count = (TextView) convertView.findViewById(R.id.count);
                holder.button = convertView.findViewById(R.id.btnOrder);

                if(context.getClass().getSimpleName().toString().trim().equals("Cart")) {
                    holder.button.setVisibility(View.GONE);

                    //calculating total in the cart
                    tv = (TextView) convertView.findViewById(R.id.total_amount);
                }

            holder.button_plus = convertView.findViewById(R.id.plus_btn);
            holder.button_minus = convertView.findViewById(R.id.minus_btn);

            if(context.getClass().getSimpleName().toString().trim().equals("Orders")){
                holder.button_plus.setVisibility(View.GONE);
                holder.button_minus.setVisibility(View.GONE);
                holder.num.setVisibility(View.GONE);

                holder.button.setText("Cancel");
                holder.button.getBackground().setColorFilter(holder.button.getContext().getResources().getColor(R.color.colorAccent), PorterDuff.Mode.MULTIPLY);

                //and turn order button into cancel button
            }
                final ViewHolder finalHolder1 = holder;
                holder.button.setTag(finalHolder1.num.getText());
                holder.itemCount = 0;
                final ViewHolder finalHolder5 = holder;
                final ViewHolder finalHolder6 = holder;

                if(! context.getClass().getSimpleName().toString().trim().equals("FoodList")) {

                    holder.button_plus.setTag(finalHolder1.num.getText());
                    holder.button_minus.setTag(finalHolder1.num.getText());
                    final ViewHolder finalHolder2 = holder;
                    final View finalConvertView = convertView;

                    holder.button_plus.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            if( finalHolder2.itemCount == 0){
                                finalHolder2.itemCount = Integer.parseInt(finalHolder2.count.getText().toString());
                            }
                            finalHolder2.itemCount ++;
//                          Toast.makeText(context,Integer.toString(finalHolder3.itemCount),Toast.LENGTH_SHORT).show();
                            finalHolder2.count.setText(Integer.toString(finalHolder2.itemCount));

                            int product_id = Integer.parseInt(finalHolder2.num.getText().toString());
                            //search this id in shoppingcart array
                            //and updated its count
                            ShoppingCart s = ShoppingCart.getInstance();
                            for (CartItem item: s.getShoppingCartArray())
                            {
                                if(item.getPro_id()== product_id){
                                    item.setBuying_amount(finalHolder2.itemCount);
                                }
                            }
                            if(context.getClass().getSimpleName().toString().trim().equals("Cart")) {
                                calcPrice(finalHolder2,"add");
                        }
                        }
                    });

                    final ViewHolder finalHolder = holder;
                    final ViewHolder finalHolder4 = holder;
                    holder.button_minus.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            if( finalHolder4.itemCount == 0){
                                finalHolder4.itemCount = Integer.parseInt(finalHolder2.count.getText().toString());
                            }

                            finalHolder4.itemCount --;
//                            Toast.makeText(context,Integer.toString(finalHolder4.itemCount),Toast.LENGTH_SHORT).show();
                            finalHolder.count.setText(Integer.toString(finalHolder4.itemCount));

                            int product_id = Integer.parseInt(finalHolder4.num.getText().toString());
                            //search this id in shoppingcart array
                            //and updated its count
                            ShoppingCart s = ShoppingCart.getInstance();
                            for (CartItem item: s.getShoppingCartArray())
                            {
                                if(item.getPro_id()== product_id){
                                    item.setBuying_amount(finalHolder4.itemCount);
                                }
                            }

                            if(context.getClass().getSimpleName().toString().trim().equals("Cart")) {
                                calcPrice(finalHolder2,"deduct");
                            }
                        }
                    });
                }

            final View finalConvertView1 = convertView;
            final View finalConvertView2 = convertView;
            holder.button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        String button_text = finalHolder1.button.getText().toString();
                       // Toast.makeText(context,""+ button_text,Toast.LENGTH_SHORT).show();
                        ListView mylistview;
                        Handler handler;
                        List<RowItem> rowItems;

                        if(context.getClass().getSimpleName().toString().trim().equals("Orders")){

                            handler = new Handler(Looper.getMainLooper()) {
                                @Override
                                public void handleMessage(Message inputMessage) {

                                }
                            };

                            mylistview = finalConvertView1.findViewById(R.id.list);
                            BackgroundWorker bw = new BackgroundWorker(context,handler,mylistview);
                            UserData ud = UserData.getInstance();

                            String order_prod_id = finalHolder1.num.getText().toString();
                            String[] parts = order_prod_id.split(":");
                            String order_id = parts[0];
                            String prod_id = parts[1];
                            //order_id is taken as brand
                            bw.execute("remove_order",order_id,prod_id);

                        }
                        else{
                            if(button_text.equals("Order")){
                                finalHolder1.button.setText("Cancel");
                                finalHolder1.button.getBackground().setColorFilter(finalHolder1.button.getContext().getResources().getColor(R.color.colorAccent), PorterDuff.Mode.MULTIPLY);
                                Toast.makeText(context,"You ordered"+ finalHolder1.pro_name.getText().toString(),Toast.LENGTH_SHORT).show();

                                //int pro_id, String pro_name, String cat, String sub_cat, String brand, int qty, double price, String details, String img,int buying_amount

                                String img_string = drawableToBase64(finalHolder5.pic);
                                CartItem c_item;
                                if(context.getClass().getSimpleName().toString().trim().equals("FoodList")){

                                    c_item = new CartItem(Integer.parseInt(finalHolder5.num.getText().toString()),finalHolder5.pro_name.getText().toString(),"","","",0,Double.parseDouble(finalHolder5.price.getText().toString()),finalHolder5.details.getText().toString(),img_string,1);ShoppingCart s = ShoppingCart.getInstance();
                                    s.getShoppingCartArray().add(c_item);

                                    //add orders by sending this to the db
                                    handler = new Handler(Looper.getMainLooper()) {
                                        @Override
                                        public void handleMessage(Message inputMessage) {

                                        }
                                    };

                                    //YYYY-MM-DD hh:mm:ss
                                    String pattern = "yyyy-MM-dd HH:mm:ss";
                                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
                                    String date_time = simpleDateFormat.format(new Date());

                                    UserData ud = UserData.getInstance();
                                    mylistview = finalConvertView2.findViewById(R.id.list);
                                    BackgroundWorker bw = new BackgroundWorker(context,handler,mylistview);
                                    bw.execute("place_order",ud.getUserID(),date_time,"salary");
                                    //params[0] place_order
                                    //params[1] emp_id
                                    //params[2} date_time
                                    //params[3} salary or cash

                                    Intent newActivityLoad = new Intent(context,Orders.class);
                                    context.startActivity(newActivityLoad);
                                    Toast.makeText(context,"Amount will be deducted from your salary",Toast.LENGTH_LONG).show();

                                }
                                else {
                                    c_item = new CartItem(Integer.parseInt(finalHolder5.num.getText().toString()),finalHolder5.pro_name.getText().toString(),"","","",0,Double.parseDouble(finalHolder5.price.getText().toString()),finalHolder5.details.getText().toString(),img_string,Integer.parseInt(finalHolder5.count.getText().toString()));
                                    ShoppingCart s = ShoppingCart.getInstance();
                                    s.getShoppingCartArray().add(c_item);
                               }


                                if(context.getClass().getSimpleName().toString().trim().equals("FoodList")){
                                    //disable all other elements

                                    ListView listLayout = (ListView)  ((Activity)context).findViewById(R.id.list);
                                    for (int i = 0; i < listLayout.getChildCount(); i++) {
                                        View child = listLayout.getChildAt(i);

                                        TextView t = (TextView) child.findViewById(R.id.pro_name);
                                        TextView t1 = (TextView) child.findViewById(R.id.num);
                                        TextView t3 = (TextView) child.findViewById(R.id.price);
                                        TextView t4 = (TextView) child.findViewById(R.id.details);
                                        TextView t5 = (TextView) child.findViewById(R.id.txt2);
                                        ImageView iv = (ImageView) child.findViewById(R.id.pic);
                                        Button b = (Button) child.findViewById(R.id.btnOrder);

                                        RelativeLayout relativeLayout = (RelativeLayout) ((Activity)context).findViewById(R.id.x);
                                        if(! (t.getText().toString()==finalHolder5.pro_name.getText().toString())){
                                            t.setTextColor(R.color.colorPrimary);
                                            t1.setTextColor(R.color.colorPrimary);
                                            t3.setTextColor(R.color.colorPrimary);
                                            t4.setTextColor(R.color.colorPrimary);
                                            t5.setTextColor(R.color.colorPrimary);
                                            b.getBackground().setColorFilter(b.getContext().getResources().getColor(R.color.common_google_signin_btn_text_light_disabled), PorterDuff.Mode.MULTIPLY);
                                            iv.setAlpha(50);
                                            b.setEnabled(false);
                                        }
                                    }
                                }
                            }
                            if(button_text.equals("Cancel")){


                                if(context.getClass().getSimpleName().toString().trim().equals("FoodList")){
                                    //disable all other elements

                                    ListView listLayout = (ListView)  ((Activity)context).findViewById(R.id.list);
                                    for (int i = 0; i < listLayout.getChildCount(); i++) {
                                        View child = listLayout.getChildAt(i);

                                        TextView t = (TextView) child.findViewById(R.id.pro_name);
                                        TextView t1 = (TextView) child.findViewById(R.id.num);
                                        TextView t3 = (TextView) child.findViewById(R.id.price);
                                        TextView t4 = (TextView) child.findViewById(R.id.details);
                                        TextView t5 = (TextView) child.findViewById(R.id.txt2);
                                        ImageView iv = (ImageView) child.findViewById(R.id.pic);
                                        Button b = (Button) child.findViewById(R.id.btnOrder);

                                        RelativeLayout relativeLayout = (RelativeLayout) ((Activity)context).findViewById(R.id.x);
                                        if(! (t.getText().toString()==finalHolder5.pro_name.getText().toString())){
                                            t.setTextColor(Color.parseColor("#303030"));
                                            t1.setTextColor(Color.parseColor("#303030"));
                                            t3.setTextColor(Color.parseColor("#303030"));
                                            t4.setTextColor(Color.parseColor("#303030"));
                                            t5.setTextColor(Color.parseColor("#303030"));
                                            b.getBackground().setColorFilter(b.getContext().getResources().getColor(R.color.colorPrimary), PorterDuff.Mode.MULTIPLY);
                                            iv.setAlpha(255);
                                            b.setEnabled(true);
                                        }
                                    }
                                }


                                finalHolder1.button.setText("Order");
                                finalHolder1.button.getBackground().setColorFilter(finalHolder1.button.getContext().getResources().getColor(R.color.colorPrimary), PorterDuff.Mode.MULTIPLY);
                                Toast.makeText(context,"Order canceled",Toast.LENGTH_SHORT).show();
                            }
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

                if(context.getClass().getSimpleName().toString().trim().equals("Orders")){
                    DecimalFormat df = new DecimalFormat("#.00");
                    String formattedValue = df.format(row_pos.getPrice());
                    holder.price.setText(formattedValue+"X"+ row_pos.getAmount());
                    holder.count.setText( df.format(row_pos.getPrice()*row_pos.getAmount()));
                    holder.num.setText(row_pos.getContactType()+":"+row_pos.getNum());
                }
                else
                {
                    DecimalFormat df = new DecimalFormat("#.00");
                    String formattedValue = df.format(row_pos.getPrice());
                    holder.price.setText(formattedValue);
                }

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

    public void calcPrice(ViewHolder vh, String operation){
        double c = Double.parseDouble(vh.count.getText().toString());
        double p= Double.parseDouble(vh.price.getText().toString());

        tv = (TextView) ((Activity)context).findViewById(R.id.total_amount);

        double currentTotal = Double.parseDouble(tv.getText().toString());
        double total = currentTotal;

        if(operation=="add"){
            total= currentTotal+p;
        }
        if(operation=="deduct"){
            total = currentTotal-p;
        }


        DecimalFormat df = new DecimalFormat("#.00");
        String formattedValue = df.format(total);

        tv.setText(formattedValue);
        //return formattedValue;
    }

}
