package com.thernatfantasy.openskydemo.main;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.thernatfantasy.openskydemo.R;
import com.thernatfantasy.openskydemo.database.FlightDataListModel;
import com.thernatfantasy.openskydemo.database.FlightInfo;
import com.thernatfantasy.openskydemo.repository.FlightInfoRepository;
import com.thernatfantasy.openskydemo.view.FlightInfoRecyclerViewAdapter;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

public class MainActivity extends AppCompatActivity {

    private static final String LOGGING_TAG = "MAIN_ACTIVITY";
    private RecyclerView flightInfoRecyclerView;
    private FlightInfoRecyclerViewAdapter flightInfoRecyclerViewAdapter;
    private List<FlightInfo> flightInfoList;
    @Inject
    ViewModelProvider.Factory viewModelFactory;
    private FlightDataListModel viewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createRecyclerViewWithFlightData();

    }

    private void createRecyclerViewWithFlightData() {
        createEmptyFlightStatesAdapter();
        createRecyclerView();
    }

    private void createEmptyFlightStatesAdapter() {
        flightInfoList = new ArrayList<>();
        flightInfoRecyclerViewAdapter = new FlightInfoRecyclerViewAdapter(this, flightInfoList);
    }

    private void createRecyclerView() {
        flightInfoRecyclerView = findViewById(R.id.flight_data_recycler_view);
        flightInfoRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        flightInfoRecyclerView.setAdapter(flightInfoRecyclerViewAdapter);
        viewModel = ViewModelProviders.of(this,viewModelFactory).get(FlightDataListModel.class);
        viewModel.getFlightInfoList().observe(MainActivity.this, new Observer<List<FlightInfo>>() {
            @Override
            public void onChanged(@Nullable List<FlightInfo> flightInfos) {
                flightInfoRecyclerViewAdapter.refreshFlightList(flightInfos);
            }
        });
    }

}
