package com.example.elbagory.orthodroid.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.elbagory.orthodroid.ImageActivity;
import com.example.elbagory.orthodroid.R;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import static com.example.elbagory.orthodroid.adapters.ImageAdapter.IMAGE_URL;


public class ListImageAdapter extends RecyclerView.Adapter<ListImageAdapter.Holder> {


    List<String> list;
    Context context;

    public ListImageAdapter(List<String> list, Context context) {
        this.list = list;
        this.context = context;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.image, parent, false);

        return new Holder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, final int position) {
        Glide.with(context).load(list.get(position)).into(holder.imageView);

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ImageActivity.class);
                intent.putStringArrayListExtra(IMAGE_URL, (ArrayList<String>) list);
                for (int i = 0; i < list.size(); i++) {
                    System.out.println(list.get(i));
                }
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (list != null)
            return list.size();
        return 0;
    }

    class Holder extends RecyclerView.ViewHolder {
        ImageView imageView;

        public Holder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image);


        }
    }
}
