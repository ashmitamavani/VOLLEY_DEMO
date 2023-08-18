package com.example.volley_demo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;

import java.util.ArrayList;

public class Recyclerview_Adapter extends RecyclerView.Adapter<Recyclerview_Adapter.viewHolder> {
    MainActivity mainActivity;
    ArrayList<Model> list;
    RequestQueue queue;
    public Recyclerview_Adapter(MainActivity mainActivity, ArrayList<Model> list) {
        this.mainActivity=mainActivity;
        this.list=list;

    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(mainActivity).inflate(R.layout.recyclerview_item_layout,parent,false);
        viewHolder holder=new viewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull Recyclerview_Adapter.viewHolder holder, int position) {

        holder.t1.setText(""+list.get(position).getName());
        holder.t2.setText(""+list.get(position).getUsername());
        holder.t3.setText(""+list.get(position).getEmail());
        holder.t4.setText(""+list.get(position).getCity());
       holder.t5.setText(""+list.get(position).getLat());


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        TextView t1,t2,t3,t4,t5;

        public viewHolder(@NonNull View itemView) {
            super(itemView);


            t1=itemView.findViewById(R.id.n1);
            t2=itemView.findViewById(R.id.n2);
            t3=itemView.findViewById(R.id.n3);
            t4=itemView.findViewById(R.id.n4);
            t5=itemView.findViewById(R.id.n5);
        }
    }
}
