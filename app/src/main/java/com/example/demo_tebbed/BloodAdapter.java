package com.example.demo_tebbed;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class BloodAdapter extends RecyclerView.Adapter<BloodAdapter.ViewHolder> {
    ArrayList<Doctor> mExampleList;
    Context context;

    public BloodAdapter(ArrayList<Doctor> exampleList,Context context) {
        mExampleList = exampleList;
        this.context = context;
    }

    @NonNull
    @Override
    public BloodAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.blood_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BloodAdapter.ViewHolder holder, int position) {
        final Doctor currentItem = mExampleList.get(position);
        final int id = currentItem.getId();
        holder.hid.setText(String.valueOf(id));
        holder.name.setText(currentItem.getName());
        holder.imageView.setImageResource(R.drawable.ic_local_hospital_black_24dp);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,Bloodbank_show.class);
                intent.putExtra("b_id",String.valueOf(id));
                System.out.println("iiiiiiiiiiiiiiii :"+id);
                context.startActivity(intent);
                Toast.makeText(context, String.valueOf(id), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mExampleList.size();
    }

    public void filterList(ArrayList<Doctor> filteredList) {
        mExampleList = filteredList;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name,hid;
        ImageView imageView;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.bname);
            hid = itemView.findViewById(R.id.bid);
            imageView = itemView.findViewById(R.id.bimg);
        }

    }
}



