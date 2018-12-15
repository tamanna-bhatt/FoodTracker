package com.example.diksha.foodtracker.tackeradapters;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.diksha.foodtracker.AddFoodTypeActivity;
import com.example.diksha.foodtracker.AddRecordActivity;
import com.example.diksha.foodtracker.AllRecordsActivity;
import com.example.diksha.foodtracker.DeleteRecordActivity;
import com.example.diksha.foodtracker.LoginActivity;
import com.example.diksha.foodtracker.R;
import com.example.diksha.foodtracker.ShareHistoryActivity;
import com.example.diksha.foodtracker.pojos.AllMenuList;
import com.example.diksha.foodtracker.pojos.SharedPrefrences;


import java.util.ArrayList;




/**
 * Created by ronakkumar.makwana on 6/28/2017.
 */

public class AllMenuListAdapter extends BaseAdapter {
    private Context mContext;
    private LayoutInflater mInflater;
    private ArrayList<AllMenuList> allMenuListArrayList;


    public AllMenuListAdapter(Context context, ArrayList<AllMenuList> allMenuListArrayList) {
        this.mContext = context;
        mInflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.allMenuListArrayList = allMenuListArrayList;

    }
    @Override
    public int getViewTypeCount() {
        return allMenuListArrayList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getCount() {
        return allMenuListArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return allMenuListArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if (convertView == null) {
            convertView = mInflater.inflate(
                    R.layout.item_menu, parent, false);
            holder = new ViewHolder();

            holder.image = (ImageView) convertView.findViewById(R.id.image);
            holder.tvName = (TextView) convertView.findViewById(R.id.tvName);

            switch (position){
                case 0 :

                    holder.image.setColorFilter(Color.parseColor("#B71C1C"));
                    holder.tvName.setTextColor(Color.parseColor("#B71C1C"));
                    break;

                case 1 :
                    holder.image.setColorFilter(Color.parseColor("#ad9614"));
                    holder.tvName.setTextColor(Color.parseColor("#ad9614"));

                    break;

                case 2 :
                    holder.image.setColorFilter(Color.parseColor("#4c4c4c"));
                    holder.tvName.setTextColor(Color.parseColor("#4c4c4c"));

                    break;

                case 3:
                    holder.image.setColorFilter(Color.parseColor("#ff0099cc"));
                    holder.tvName.setTextColor(Color.parseColor("#ff0099cc"));
                    break;

                case 4:

                    holder.image.setColorFilter(Color.parseColor("#FFC107"));
                    holder.tvName.setTextColor(Color.parseColor("#FFC107"));
                    break;

                case 5 :
                    holder.image.setColorFilter(Color.parseColor("#ffaa66cc"));
                    holder.tvName.setTextColor(Color.parseColor("#ffaa66cc"));

                    break;



            }




            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    switch (position){
                        case 0 :
                            Intent i5 = new Intent(mContext, AddFoodTypeActivity.class);
                            mContext.startActivity(i5);
                            break;

                        case 1 :


                            Intent it = new Intent(mContext, AddRecordActivity.class);
                            mContext.startActivity(it);
                            break;

                        case 2 :
                            Intent it1 = new Intent(mContext, AllRecordsActivity.class);
                            mContext.startActivity(it1);

                            break;

                        case 3:
                            Intent it2 = new Intent(mContext, DeleteRecordActivity.class);
                            mContext.startActivity(it2);
                            break;

                        case 4:
                            Intent it41 = new Intent(mContext, ShareHistoryActivity.class);
                            mContext.startActivity(it41);


                            break;

                        case 5 :

                            SharedPrefrences.setUserId("",mContext);
                            Intent it4 = new Intent(mContext, LoginActivity.class);
                            it4.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            it4.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            it4.putExtra("EXIT", true);
                            mContext.startActivity(it4);
                            break;
                        
                        
                        
                    }


                }
            });



            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        AllMenuList allMenuList = allMenuListArrayList.get(position);

        holder.tvName.setText(allMenuList.getName());
        holder.image.setImageResource(allMenuList.getImage());
        return convertView;
    }


    public interface AdapterClickListener {
        public void onItemClick();
    }

    private static class ViewHolder {

        public TextView tvName;
        public ImageView image;
    }


}
