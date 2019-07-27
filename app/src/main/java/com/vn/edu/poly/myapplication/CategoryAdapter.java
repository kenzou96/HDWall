package com.vn.edu.poly.myapplication;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.vn.edu.poly.myapplication.model.category;

import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ExampleViewHolder> {
    private ArrayList<category> categoryArrayList;
    private Context context;


    public static class ExampleViewHolder extends RecyclerView.ViewHolder {
        public ImageView mImageView;
        public TextView mTextView1;



        public ExampleViewHolder(View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.imgBg);
            mTextView1 = itemView.findViewById(R.id.txtvTitle);



        }
    }

    public CategoryAdapter(Context context,ArrayList<category> weatherList) {
        this.context = context;
        this.categoryArrayList = weatherList;
    }

    @Override
    public ExampleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_category, parent, false);
        ExampleViewHolder evh = new ExampleViewHolder(v);
        return evh;
    }

    @Override
    public void onBindViewHolder(ExampleViewHolder holder, int position) {
        final category category = categoryArrayList.get(position);

        //holder.mImageView.setImageResource(R.drawable.sutu);
        holder.mTextView1.setText(category.getmText());
        Picasso.with(context).load(category.getmImageResource()).into(holder.mImageView);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, category.getmText(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context,ImageActivity.class);
                intent.putExtra("Anh",category.getmImageResource());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return categoryArrayList.size();
    }
}