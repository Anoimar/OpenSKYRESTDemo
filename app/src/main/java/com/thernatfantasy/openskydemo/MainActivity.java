package com.thernatfantasy.openskydemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import com.thernatfantasy.openskydemo.json.FlightData;
import com.thernatfantasy.openskydemo.view.FlightStateRecyclerViewAdapter;
import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String LOGGING_TAG = "MAIN_ACTIVITY";
    private static final String LAST_SAVED_FLIGHT_STATES = "saved_flights";
    private RecyclerView flightStateRecyclerView;
    private FlightStateRecyclerViewAdapter flightStatesAdapter;
    private ArrayList<UsefulState> flightStatesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createRecyclerViewWithFlightData(savedInstanceState);

    }

    private void createRecyclerViewWithFlightData(Bundle savedInstanceState) {
        createEmptyFlightStatesAdapter();
        createRecyclerView();
        if(savedInstanceState == null) {
            requestDataFromOpenSkyService();
        }else{
            restoreFlightStateList(savedInstanceState);
        }
    }

    private void createEmptyFlightStatesAdapter() {
        flightStatesList = new ArrayList<>();
        flightStatesAdapter = new FlightStateRecyclerViewAdapter(this,flightStatesList);
    }

    private void createRecyclerView() {
        flightStateRecyclerView = (RecyclerView)findViewById(R.id.flight_data_recycler_view);
        flightStateRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        flightStateRecyclerView.setAdapter(flightStatesAdapter);
    }

    private void requestDataFromOpenSkyService() {
        OpenSkyService openSkyService = OpenSkyService.retrofit.create(OpenSkyService.class);
        Call<FlightData> call = openSkyService.flightData();
        call.enqueue(new Callback<FlightData>() {
            @Override
            public void onResponse(Call<FlightData> call, Response<FlightData> response) {
                flightStatesAdapter.refreshFlightList(response.body().getUsefulStates());

            }

            @Override
            public void onFailure(Call<FlightData> call, Throwable t) {
                Log.e(LOGGING_TAG,"Couldn't get flight data."+t.getMessage());
            }
        });
    }

    private void restoreFlightStateList(Bundle savedInstanceState){
        ArrayList<UsefulState> savedFlightStatesList = savedInstanceState.getParcelableArrayList(LAST_SAVED_FLIGHT_STATES);
        Log.e("SIZE","loaded "+savedFlightStatesList.size());
        flightStatesAdapter.refreshFlightList(savedFlightStatesList);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.e("SIZE","saving "+flightStatesList.size());
        outState.putParcelableArrayList(LAST_SAVED_FLIGHT_STATES,flightStatesList);
    }
}
