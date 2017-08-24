package com.task.basilischi.funjebret;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.task.basilischi.funjebret.interfaces.FootballAPI;
import com.task.basilischi.funjebret.models.FootballData;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {


    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Gson gson = new GsonBuilder().create();
        String apiKey,from,to,leagueId;
        Date currentTime = Calendar.getInstance().getTime();
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
        from = dateFormatter.format(currentTime);
        Calendar currentCal = Calendar.getInstance();
        currentCal.add(Calendar.DATE, 7);
        to =  dateFormatter.format(currentCal.getTime());
        apiKey = getResources().getString(R.string.api_key);
        //from = getResources().getString(R.string.from);
        //to = getResources().getString(R.string.to);
        leagueId = getResources().getString(R.string.league_id);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getResources().getString(R.string.base_url))
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        FootballAPI footballAPI = retrofit.create(FootballAPI.class);
        Call<List<FootballData>> result = footballAPI.getFootballList(apiKey,from,to,leagueId);
        result.enqueue(new Callback<List<FootballData>>() {
            @Override
            public void onResponse(Call<List<FootballData>> call, Response<List<FootballData>> response) {
                List<FootballData> datas = response.body();
                for(FootballData m: datas){
                    Log.d("Negara : ",m.getCountryName());
                    Log.d("Liga : ",m.getLeagueName());
                    Log.d("Home : ",m.getMatchHometeamName());
                    Log.d("Away : ",m.getMatchAwayteamName());
                    Log.d("Date : ",m.getMatchDate());
                    Log.d("Time : ",m.getMatchTime());
                }
            }

            @Override
            public void onFailure(Call<List<FootballData>> call, Throwable t) {
                Toast.makeText(getContext(), " response bad " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

}
