package com.example.denememesaj;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class grupslideadaptor extends RecyclerView.Adapter<grupslideadaptor.ViewHolder> {

    private ArrayList<grupslidemodel>grupslider;
    private Context context;

    public grupslideadaptor(ArrayList<grupslidemodel> grupslider, Context context) {
        this.grupslider = grupslider;
        this.context = context;
    }

    @NonNull
    @Override
    public grupslideadaptor.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate((R.layout.grupslide),parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull grupslideadaptor.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return grupslider.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView img;
        TextView name,desc,sira;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            img=itemView.findViewById(R.id.icon_img);
            name=itemView.findViewById(R.id.grupname);
            desc=itemView.findViewById(R.id.grupdesc);
            sira=itemView.findViewById(R.id.sirano);


        }
    }
}
