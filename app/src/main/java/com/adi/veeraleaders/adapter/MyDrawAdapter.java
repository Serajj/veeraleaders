package com.adi.veeraleaders.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.adi.veeraleaders.R;
import com.adi.veeraleaders.model.LuckyDraw;
import com.adi.veeraleaders.model.MyDraw;
import com.adi.veeraleaders.ui.BetActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MyDrawAdapter extends RecyclerView.Adapter<MyDrawAdapter.MyHolder>{

    Context context;
    List<MyDraw> luckyDrawList;

    public MyDrawAdapter(Context context, List<MyDraw> luckyDrawList) {
        this.context = context;
        this.luckyDrawList = luckyDrawList;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.mydraw_item,parent,false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {

        holder.title.setText(luckyDrawList.get(position).getTitle());
        holder.first_winner.setText("First winning amount "+luckyDrawList.get(position).getAmt());

        if (luckyDrawList.get(position).getStatus().equalsIgnoreCase("completed")){

            Log.d("serajmd",luckyDrawList.get(position).getWinAmt());
            Log.d("serajmd",luckyDrawList.get(position).getAmt());
            if (Integer.parseInt(luckyDrawList.get(position).getAmt()) < Integer.parseInt(luckyDrawList.get(position).getWinAmt())){
                holder.timersd.setText("Congratulation you are winner !");
                holder.timersd.setTextColor(Color.GREEN);
                holder.joinBtn.setText("₹ "+luckyDrawList.get(position).getWinAmt());
                holder.joinBtn.setVisibility(View.VISIBLE);
            }else{
                holder.timersd.setText("Better luck next time !");
                holder.joinBtn.setText("₹ "+luckyDrawList.get(position).getWinAmt()+" Refunded");
                holder.joinBtn.setVisibility(View.VISIBLE);
            }
        }








        holder.joinBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });
    }

    @Override
    public int getItemCount() {
        return luckyDrawList.size();
    }



    class MyHolder extends RecyclerView.ViewHolder {
        TextView joinBtn,title,timersd,first_winner;
        boolean c=false;
        Date startDate;
        public MyHolder(@NonNull View itemView) {

            super(itemView);

            joinBtn=itemView.findViewById(R.id.join_btn);
            title=itemView.findViewById(R.id.titles);
            timersd=itemView.findViewById(R.id.timer);
            first_winner=itemView.findViewById(R.id.first_prize);

        }



    }
}
