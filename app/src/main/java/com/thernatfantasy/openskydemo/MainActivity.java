package com.thernatfantasy.openskydemo;

import android.arch.lifecycle.LifecycleActivity;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import com.thernatfantasy.openskydemo.database.FlightDataListModel;
import com.thernatfantasy.openskydemo.json.FlightData;
import com.thernatfantasy.openskydemo.view.FlightInfoRecyclerViewAdapter;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends LifecycleActivity {

    private static final String LOGGING_TAG = "MAIN_ACTIVITY";
    private RecyclerView flightInfoRecyclerView;
    private FlightInfoRecyclerViewAdapter flightInfoRecyclerViewAdapter;
    private List<FlightInfo> flightInfoList;
    private FlightDataListModel viewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createRecyclerViewWithFlightData();

    }

    private void createRecyclerViewWithFlightData() {
        createEmptyFlightStatesAdapter();
        createRecyclerView();
        requestDataFromOpenSkyService();
    }

    private void createEmptyFlightStatesAdapter() {
        flightInfoList = new ArrayList<>();
        flightInfoRecyclerViewAdapter = new FlightInfoRecyclerViewAdapter(this, flightInfoList);
    }

    private void createRecyclerView() {
        flightInfoRecyclerView = (RecyclerView)findViewById(R.id.flight_data_recycler_view);
        flightInfoRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        flightInfoRecyclerView.setAdapter(flightInfoRecyclerViewAdapter);
        viewModel = ViewModelProviders.of(this).get(FlightDataListModel.class);
        viewModel.getFlightInfoList().observe(MainActivity.this, new Observer<List<FlightInfo>>() {
            @Override
            public void onChanged(@Nullable List<FlightInfo> flightInfos) {
                flightInfoRecyclerViewAdapter.refreshFlightList(flightInfos);
            }
        });
    }

    private void requestDataFromOpenSkyService() {
        OpenSkyService openSkyService = OpenSkyService.retrofit.create(OpenSkyService.class);
        Call<FlightData> call = openSkyService.flightData();
        call.enqueue(new Callback<FlightData>() {
            @Override
            public void onResponse(Call<FlightData> call, Response<FlightData> response) {
             List<FlightInfo> flightInfos = response.body().getFlightInfoList();
                for(FlightInfo flightInfo : flightInfos){
                    viewModel.addFlightInfo(flightInfo);
                }
            }

            @Override
            public void onFailure(Call<FlightData> call, Throwable t) {
                Log.e(LOGGING_TAG,"Couldn't get flight data."+t.getMessage());
            }
        });
    }
}
