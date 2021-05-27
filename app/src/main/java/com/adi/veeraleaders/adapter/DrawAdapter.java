package com.adi.veeraleaders.adapter;

import android.content.Context;
import android.content.Intent;
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
import com.adi.veeraleaders.ui.BetActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DrawAdapter extends RecyclerView.Adapter<DrawAdapter.MyHolder>{

    Context context;
    List<LuckyDraw> luckyDrawList;

    public DrawAdapter(Context context, List<LuckyDraw> luckyDrawList) {
        this.context = context;
        this.luckyDrawList = luckyDrawList;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.lucky_draw_item,parent,false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {

        holder.title.setText(luckyDrawList.get(position).getTitle());
        holder.first_winner.setText("First winning amount "+luckyDrawList.get(position).getWinAmt());

        holder.joinBtn.setText("₹ "+luckyDrawList.get(position).getAmt());

        Log.d("serajc",""+luckyDrawList.get(position).getEndTime());

        Log.d("seraj","calling");





        final Handler handler = new Handler();
        Runnable runnable = new Runnable() {


            @Override
            public void run() {
                try{


                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-M-dd hh:mm:ss");
                    Log.d("serajd","calling");

                    String date= luckyDrawList.get(position).getEndTime();




                    try {
                        Date currentTime = Calendar.getInstance().getTime();
                        Log.d("serajct",currentTime+"");

                        Date date1 = currentTime;
                        Date date2 = simpleDateFormat.parse(date);
                        Log.d("serajd","called");
                        String s =  holder.dateDifference(date1, date2);
                        if (s.equals("Dead Line Passed")){
                            holder.joinBtn.setEnabled(false);
                            holder.c=true;
//                            luckyDrawList.remove(position);
//                            notifyDataSetChanged();



                        }
                        holder.timersd.setText(s);

                    } catch (ParseException e) {
                        e.printStackTrace();
                        Toast.makeText(context, ""+e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    }




                }
                catch (Exception e) {
                    // TODO: handle exception
                }
                finally{
                    //also call the same runnable to call it at regular interval
                    handler.postDelayed(this, 1000);
                }
            }
        };

//runnable must be execute once
        handler.post(runnable);









         if (holder.c){
             handler.removeCallbacks(runnable);
             Log.d("serajr","stopped");
         }


        holder.joinBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(context,BetActivity.class);
                intent.putExtra("optn1",luckyDrawList.get(position).getOpI());
                intent.putExtra("optn2",luckyDrawList.get(position).getOpIi());
                intent.putExtra("optn3",luckyDrawList.get(position).getOpIii());
                intent.putExtra("optn4",luckyDrawList.get(position).getOpIv());
                intent.putExtra("optn5",luckyDrawList.get(position).getOpV());
                intent.putExtra("optn6",luckyDrawList.get(position).getOpVi());
                intent.putExtra("did",luckyDrawList.get(position).getId());
                intent.putExtra("title",luckyDrawList.get(position).getTitle());
                intent.putExtra("amt",luckyDrawList.get(position).getAmt());
                context.startActivity(intent);
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

        private String dateDifference(Date startDate,Date endDate) {

            //milliseconds
            long different = endDate.getTime() - startDate.getTime();

            System.out.println("startDate : " + startDate);
            System.out.println("endDate : "+ endDate);
            System.out.println("different : " + different);

            long secondsInMilli = 1000;
            long minutesInMilli = secondsInMilli * 60;
            long hoursInMilli = minutesInMilli * 60;
            long daysInMilli = hoursInMilli * 24;

            long elapsedDays = different / daysInMilli;
            different = different % daysInMilli;

            long elapsedHours = different / hoursInMilli;
            different = different % hoursInMilli;

            long elapsedMinutes = different / minutesInMilli;
            different = different % minutesInMilli;

            long elapsedSeconds = different / secondsInMilli;

            String s=  elapsedDays +" days "+ elapsedHours+" h "+ elapsedMinutes+" m "+ elapsedSeconds+" s";

            if (elapsedSeconds < -1){
                return "Dead Line Passed";
            }else{
                return s;
            }




        }

    }
}
