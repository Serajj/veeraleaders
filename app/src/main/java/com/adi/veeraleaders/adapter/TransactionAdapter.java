package com.adi.veeraleaders.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.adi.veeraleaders.R;
import com.adi.veeraleaders.model.MyWinHistory;

import java.util.List;

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.MyHolder> {

    Context context;
    List<MyWinHistory> transactionList;

    public TransactionAdapter(Context context, List<MyWinHistory> transactionList) {
        this.context = context;
        this.transactionList = transactionList;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.winning_item,parent,false);
        return new TransactionAdapter.MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return transactionList.size();
    }

    class MyHolder extends RecyclerView.ViewHolder {
        TextView upi,tdate,amt;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            upi=itemView.findViewById(R.id.upi_id);
            tdate=itemView.findViewById(R.id.paytime);
            amt=itemView.findViewById(R.id.amt_win);
        }
    }
}
