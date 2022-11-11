package com.example.ruangan;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {

    private Context context;
    private Activity activity;
    private ArrayList ruang_id, kode_ruang, kapasitas_ruang;

    Adapter(Activity activity, Context context, ArrayList ruang_id, ArrayList kode_ruang, ArrayList kapasitas_ruang){
        this.activity = activity;
        this.context = context;
        this.ruang_id = ruang_id;
        this.kode_ruang = kode_ruang;
        this.kapasitas_ruang = kapasitas_ruang;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row ,parent, false);
        return new MyViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        holder.ruang_id_txt.setText(String.valueOf(ruang_id.get(position)));
        holder.kode_ruang_txt.setText(String.valueOf(kode_ruang.get(position)));
        holder.kapasitas_ruang_txt.setText(String.valueOf(kapasitas_ruang.get(position)));

        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =  new Intent(context, UpdateActivity.class);
                intent.putExtra("id", String.valueOf(ruang_id.get(position)));
                intent.putExtra("kodeRuang", String.valueOf(kode_ruang.get(position)));
                intent.putExtra("kapasitas", String.valueOf(kapasitas_ruang.get(position)));
                activity.startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return ruang_id.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        TextView ruang_id_txt, kode_ruang_txt, kapasitas_ruang_txt;
        LinearLayout mainLayout;

        MyViewHolder(@NonNull View itemView){
            super(itemView);
            ruang_id_txt = itemView.findViewById(R.id.ruang_id_txt);
            kode_ruang_txt = itemView.findViewById(R.id.kode_ruang_txt);
            kapasitas_ruang_txt = itemView.findViewById(R.id.kapasitas_ruang_txt);
            mainLayout = itemView.findViewById(R.id.mainLayout);

            Animation translate_anim = AnimationUtils.loadAnimation(context, R.anim.translate_anim);
            mainLayout.setAnimation(translate_anim);
        }
    }
}
