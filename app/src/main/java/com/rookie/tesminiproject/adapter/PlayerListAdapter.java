package com.rookie.tesminiproject.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.rookie.tesminiproject.R;
import com.rookie.tesminiproject.model.Player;

import java.util.List;

public class PlayerListAdapter extends RecyclerView.Adapter<PlayerListAdapter.ViewHolder>{
    private Context context;
    private List<Player> playerList;

    public PlayerListAdapter(Context context, List<Player> playerList) {
        this.context = context;
        this.playerList = playerList;
    }

    @NonNull
    @Override
    public PlayerListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_view,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PlayerListAdapter.ViewHolder holder, int position) {
        Player player = playerList.get(position);

        holder.fullnameTv.setText(player.getName());
        holder.salaryTv.setText(player.getSalary());
        Glide.with(context).load(player.getImage()).into(holder.imagePeople);
    }

    @Override
    public int getItemCount() {
        return playerList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView fullnameTv,salaryTv;
        ImageView imagePeople;

        public ViewHolder(View itemView) {
            super(itemView);

            fullnameTv = itemView.findViewById(R.id.fullnameTv);
            salaryTv = itemView.findViewById(R.id.salaryTv);
            imagePeople = itemView.findViewById(R.id.imgPeople);
        }
    }
}
