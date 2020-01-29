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

public class DoctorAdapter extends RecyclerView.Adapter<DoctorAdapter.ViewHolder> {
    ArrayList<Doctor> mExampleList;
    Context context;

    public DoctorAdapter(ArrayList<Doctor> exampleList,Context context) {
        mExampleList = exampleList;
        this.context = context;
    }

    @NonNull
    @Override
    public DoctorAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.doctor_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DoctorAdapter.ViewHolder holder, int position) {
        final Doctor currentItem = mExampleList.get(position);
        final int id = currentItem.getId();
        holder.hid.setText(String.valueOf(id));
        holder.name.setText(currentItem.getName());
        holder.imageView.setImageResource(R.drawable.ic_person_black_24dp);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(context, String.valueOf(id), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context,DoctorList.class);
                intent.putExtra("d_id",String.valueOf(id));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mExampleList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name,hid;
        ImageView imageView;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.dname);
            hid = itemView.findViewById(R.id.did);
            imageView = itemView.findViewById(R.id.dimg);
        }

    }
}