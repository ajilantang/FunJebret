package com.task.basilischi.funjebret;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.facebook.login.LoginManager;
import com.google.firebase.auth.FirebaseAuth;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.task.basilischi.funjebret.interfaces.FootballAPI;
import com.task.basilischi.funjebret.models.FootballData;
import com.task.basilischi.funjebret.models.Matches;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.facebook.FacebookSdk.getApplicationContext;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {
    private View view;
    private List<FootballData> dataList = new ArrayList<>();
    private List<Matches> matchList = new ArrayList<>();
    private RecyclerView recyclerView;
    private MatchesAdapter mAdapter;



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
        view = inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        mAdapter = new MatchesAdapter(matchList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
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
                    Matches matches = new Matches(m.getMatchHometeamName(), m.getMatchAwayteamName(),m.getMatchDate(),m.getLeagueName());
                    matchList.add(matches);
                    Log.d("Negara : ",m.getCountryName());
                    Log.d("Liga : ",m.getLeagueName());
                    Log.d("Home : ",m.getMatchHometeamName());
                    Log.d("Away : ",m.getMatchAwayteamName());
                    Log.d("Date : ",m.getMatchDate());
                    Log.d("Time : ",m.getMatchTime());
                }
                mAdapter.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<List<FootballData>> call, Throwable t) {
                Toast.makeText(getContext(), " response bad " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        View view = inflater.inflate(R.layout.fragment_home, container, false);
        Button signOut = (Button)view.findViewById(R.id.signOut);

        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                LoginManager.getInstance().logOut();
                ActivityCompat.finishAffinity(getActivity());
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
                Toast.makeText(getActivity(), "Sign Out Success!", Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }

}
