package com.task.basilischi.funjebret.interfaces;

import com.task.basilischi.funjebret.models.FootballData;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by syauqi on 24/08/17.
 */

public interface FootballAPI {

    @GET("api/?action=get_events")
    Call<List<FootballData>> getFootballList(@Query("APIkey") String apiKey,
                                             @Query("from") String from,
                                             @Query("to") String to,
                                             @Query("league_id") String leagueId);


}
