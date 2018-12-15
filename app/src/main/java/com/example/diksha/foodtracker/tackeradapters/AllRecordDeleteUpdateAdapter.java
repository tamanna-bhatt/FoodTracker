package com.example.diksha.foodtracker.tackeradapters;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.diksha.foodtracker.R;
import com.example.diksha.foodtracker.UpdateRecordActivity;
import com.example.diksha.foodtracker.trackerdatabase.FoodTrackerDatabase;
import com.example.diksha.foodtracker.pojos.Records;

import java.util.ArrayList;

public class AllRecordDeleteUpdateAdapter extends RecyclerView
        .Adapter<AllRecordDeleteUpdateAdapter
        .DataObjectHolder> {
    private static String LOG_TAG = "MyRecyclerViewAdapter";
    private ArrayList<Records> mealsArrayList;


    private Context context;
    private static MyClickListener myClickListener;

    public static class DataObjectHolder extends RecyclerView.ViewHolder
            implements View
            .OnClickListener {
        TextView tvMenuName,tvProtien,tvCarbohydrate,tvFat,tvCalories,tvDateTime,type;
        private LinearLayout llDelete;
        private LinearLayout llDateAndtype,llDivider;



        public DataObjectHolder(View itemView) {
            super(itemView);
            tvMenuName = (TextView) itemView.findViewById(R.id.tvMenuName);
            tvProtien = (TextView) itemView.findViewById(R.id.tvProtien);
            tvCarbohydrate = (TextView) itemView.findViewById(R.id.tvCarbohydrate);
            tvFat = (TextView) itemView.findViewById(R.id.tvFat);
            tvCalories = (TextView) itemView.findViewById(R.id.tvCalories);
            llDelete= (LinearLayout) itemView.findViewById(R.id.llDelete);
            tvDateTime = (TextView) itemView.findViewById(R.id.tvDateTime);
            llDateAndtype= (LinearLayout) itemView.findViewById(R.id.llDateAndtype);
            type = (TextView) itemView.findViewById(R.id.type);
            llDivider= (LinearLayout) itemView.findViewById(R.id.llDivider);
            llDelete.setVisibility(View.VISIBLE);

//  *****for line on text********
//            cut_price.setPaintFlags(cut_price.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            Log.i(LOG_TAG, "Adding Listener");
           // itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            //       myClickListener.onItemClick(getAdapterPosition(), v);
        }

    }

    public void setOnItemClickListener(MyClickListener myClickListener) {
        this.myClickListener = myClickListener;
    }

    public AllRecordDeleteUpdateAdapter(Context context, ArrayList<Records> mealsArrayList) {
        this.context = context;
        this.mealsArrayList = mealsArrayList;
    }

    @Override
    public DataObjectHolder onCreateViewHolder(ViewGroup parent,
                                               int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_record, parent, false);


        DataObjectHolder dataObjectHolder = new DataObjectHolder(view);
        return dataObjectHolder;
    }

    @Override
    public void onBindViewHolder(DataObjectHolder holder, final int position) {
//        holder.quote.setText(recommendedDishesArrayList.get(position).getQuote());
//
//
//
       holder.tvCarbohydrate.setText("Carbohydrate : "+mealsArrayList.get(position).getCarbohydrate());
        holder.tvProtien.setText("Protien : "+mealsArrayList.get(position).getCarbohydrate());
        holder.tvFat.setText("Fat : "+mealsArrayList.get(position).getCarbohydrate());
        holder.tvCalories.setText("Energy : "+mealsArrayList.get(position).getCalories());
        holder.tvMenuName.setText(mealsArrayList.get(position).getMealName());
        holder.tvDateTime.setText(mealsArrayList.get(position).getDate1() + " " + mealsArrayList.get(position).getCurrent_time());


        if (mealsArrayList.get(position).isShowDate() == true){
            holder.llDateAndtype.setVisibility(View.VISIBLE);
            holder.llDivider.setVisibility(View.GONE);

        }else {
            holder.llDateAndtype.setVisibility(View.GONE);
            holder.llDivider.setVisibility(View.GONE);
        }
        holder.tvDateTime.setText(mealsArrayList.get(position).getDate1());

        holder.type.setText(mealsArrayList.get(position).getMealCategory());


//        holder.totalWeight.setText(airGroundServiceArrayList.get(position).get());

        holder.llDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                alertDialogBuilder.setMessage("Are you sure want to delete " + mealsArrayList.get(position).getMealName() + " ?");
                        alertDialogBuilder.setPositiveButton("yes",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface arg0, int arg1) {
                                        FoodTrackerDatabase foodTrackerDatabase = new FoodTrackerDatabase(context);
                                        SQLiteDatabase sqLiteDatabase = foodTrackerDatabase.getWritableDatabase();

                                        foodTrackerDatabase.deleteMeals(mealsArrayList.get(position));
                                        mealsArrayList.remove(position);

                                        notifyDataSetChanged();
                                    }
                                });

                alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                dialogInterface.dismiss();
                            }
                        });

                        AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();

                            // Create the AlertDialog object and return it



            }
        });


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent it = new Intent(context, UpdateRecordActivity.class);
                it.putExtra("id", mealsArrayList.get(position).getId());
                context.startActivity(it);
            }
        });

    }



    @Override
    public int getItemCount() {
        return mealsArrayList.size();
    }

    public interface MyClickListener {
        public void onItemClick(int position, View v);

    }
}