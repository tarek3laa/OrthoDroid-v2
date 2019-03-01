package com.example.elbagory.orthodroid.adapters;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.elbagory.orthodroid.Models.RecyclerViewRow_Model;
import com.example.elbagory.orthodroid.OnRecyclerViewItemClickListener;
import com.example.elbagory.orthodroid.R;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.PatientHolder> {

    private OnRecyclerViewItemClickListener onRecyclerViewItemClickListener;

    List<RecyclerViewRow_Model> list;
    Context context;



    public RecyclerAdapter(List<RecyclerViewRow_Model> list, Context context) {
        this.list = list;
        this.context = context;
    }


    @NonNull
    @Override
    public PatientHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);
        return new PatientHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull PatientHolder holder, int position) {
        RecyclerViewRow_Model recyclerViewRowModel = list.get(position);

        holder.textViewId.setText(recyclerViewRowModel.getId());
        holder.textViewname.setText(recyclerViewRowModel.getName());
        holder.textViewlasttime.setText(String.valueOf(recyclerViewRowModel.getTime()));
        holder.rowMainParentLinearLayout.setTag(recyclerViewRowModel);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }



    class PatientHolder extends RecyclerView.ViewHolder {
        private TextView textViewId;
        private TextView textViewname;
        private TextView textViewlasttime;
        private LinearLayout rowMainParentLinearLayout;

        public PatientHolder(View itemView) {
            super(itemView);
            textViewId = itemView.findViewById(R.id.textViewID);
            textViewname = itemView.findViewById(R.id.textViewName);
            textViewlasttime = itemView.findViewById(R.id.textViewLastUpdate);
            rowMainParentLinearLayout =  itemView.findViewById(R.id.row_main_adapter_linear_layout);





            rowMainParentLinearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (onRecyclerViewItemClickListener != null) {
                        onRecyclerViewItemClickListener.onItemClick(getAdapterPosition(), view);
                    }
                }
            });
        }
    }

    public void setOnRecyclerViewItemClickListener(OnRecyclerViewItemClickListener onRecyclerViewItemClickListener) {
        this.onRecyclerViewItemClickListener = onRecyclerViewItemClickListener;
    }
}
