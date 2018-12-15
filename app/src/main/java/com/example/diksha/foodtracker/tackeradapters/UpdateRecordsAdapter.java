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
import com.example.diksha.foodtracker.UpdateFodTypeActivity;
import com.example.diksha.foodtracker.trackerdatabase.FoodTrackerDatabase;
import com.example.diksha.foodtracker.pojos.FoodType;

import java.util.ArrayList;

public class UpdateRecordsAdapter extends RecyclerView
        .Adapter<UpdateRecordsAdapter
        .DataObjectHolder> {
    private static String LOG_TAG = "MyRecyclerViewAdapter";
    private ArrayList<FoodType> foodTypeArrayList;


    private Context context;
    private static MyClickListener myClickListener;

    public static class DataObjectHolder extends RecyclerView.ViewHolder
            implements View
            .OnClickListener {
        TextView tvName;
        private LinearLayout llDelete;



        public DataObjectHolder(View itemView) {
            super(itemView);
            tvName = (TextView) itemView.findViewById(R.id.tvName);

            llDelete= (LinearLayout) itemView.findViewById(R.id.llDelete);
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

    public UpdateRecordsAdapter(Context context, ArrayList<FoodType> foodTypeArrayList) {
        this.context = context;
        this.foodTypeArrayList = foodTypeArrayList;
    }

    @Override
    public DataObjectHolder onCreateViewHolder(ViewGroup parent,
                                               int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_search_product, parent, false);


        DataObjectHolder dataObjectHolder = new DataObjectHolder(view);
        return dataObjectHolder;
    }

    @Override
    public void onBindViewHolder(DataObjectHolder holder, final int position) {
//        holder.quote.setText(recommendedDishesArrayList.get(position).getQuote());
//
//
//
        holder.tvName.setText(foodTypeArrayList.get(position).getCatName());


//        holder.totalWeight.setText(airGroundServiceArrayList.get(position).get());

        holder.llDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                alertDialogBuilder.setMessage("Are you sure want to delete " + foodTypeArrayList.get(position).getCatName() + " ?");
                        alertDialogBuilder.setPositiveButton("yes",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface arg0, int arg1) {
                                        FoodTrackerDatabase foodTrackerDatabase = new FoodTrackerDatabase(context);
                                        SQLiteDatabase sqLiteDatabase = foodTrackerDatabase.getWritableDatabase();

                                        foodTrackerDatabase.deletecategory(foodTypeArrayList.get(position));
                                        foodTypeArrayList.remove(position);

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

                Intent it = new Intent(context, UpdateFodTypeActivity.class);
                it.putExtra("id", foodTypeArrayList.get(position).getId());
                context.startActivity(it);
            }
        });

    }



    @Override
    public int getItemCount() {
        return foodTypeArrayList.size();
    }

    public interface MyClickListener {
        public void onItemClick(int position, View v);

    }
}