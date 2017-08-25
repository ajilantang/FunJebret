package com.task.basilischi.funjebret;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.task.basilischi.funjebret.models.FootballData;
import com.task.basilischi.funjebret.models.Matches;

import java.util.List;

/**
 * Created by ajilantang on 24/08/17.
 */

public class MatchesAdapter extends RecyclerView.Adapter<MatchesAdapter.MyViewHolder> {
    List<Matches> matchList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView home, away, date, leagueName;

        public MyViewHolder(View view) {
            super(view);
            home = (TextView) view.findViewById(R.id.home);
            away = (TextView) view.findViewById(R.id.away);
            date = (TextView) view.findViewById(R.id.date);
            leagueName = (TextView) view.findViewById(R.id.league);
        }
    }

    public MatchesAdapter(List<Matches> matchList) {
        this.matchList = matchList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_view, parent, false);

        return new MyViewHolder(itemView);
    }
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Matches match = matchList.get(position);
        holder.home.setText(match.getHome());
        holder.away.setText(match.getAway());
        holder.date.setText(match.getDate());
        holder.leagueName.setText(match.getLeagueName()+" | ");
    }

    @Override
    public int getItemCount() {
        return matchList.size();
    }
}
