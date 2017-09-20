package com.tiger.curious.guide.service;

import com.tiger.curious.guide.model.Company;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by lulala on 20/9/2017.
 */

public interface LighterService {

    @GET("/getArrangement")
    List<Company> fetchArrangement();

    @GET("/getArrangement")
    Call<List<Company>> fetchArrangementAsync();
}
