package com.example.demo_tebbed;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {
    ArrayList<Iteam> mExampleList;
    Context context;


    public CustomAdapter(ArrayList<Iteam> exampleList,Context context) {
        mExampleList = exampleList;
        this.context = context;
    }

    @NonNull
    @Override
    public CustomAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapter.ViewHolder holder, int position) {
        final Iteam currentItem = mExampleList.get(position);
        final int id = currentItem.getId();
        holder.hid.setText(String.valueOf(id));
        holder.name.setText(currentItem.getName());
        if (currentItem.getPgflag().equals("p"))
            holder.imageView.setImageResource(R.drawable.red);
        else
            holder.imageView.setImageResource(R.drawable.green);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,Next_scree_Update.class);
                intent.putExtra("id",id);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mExampleList.size();
    }

    public void filterList(ArrayList<Iteam> filteredList) {
        mExampleList = filteredList;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name,hid;
        ImageView imageView;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            hid = itemView.findViewById(R.id.hid);
            imageView = itemView.findViewById(R.id.himg);
        }

        }
    }



