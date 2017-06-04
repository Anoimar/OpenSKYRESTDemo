package com.thernatfantasy.openskydemo.view;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.thernatfantasy.openskydemo.R;
import com.thernatfantasy.openskydemo.UsefulState;

import java.util.List;

/**
 * Created by mariuszrafalski on 04.06.2017.
 */

public class FlightStateRecyclerViewAdapter extends RecyclerView.Adapter<FlightStateRecyclerViewAdapter.FlightStatesViewHolder>{

    private List<UsefulState> flightsStates;

    public FlightStateRecyclerViewAdapter(List<UsefulState> flightsStates) {
        this.flightsStates = flightsStates;
    }

    @Override
    public FlightStatesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View flightItemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.flight_data_row,parent,false);
        return new FlightStatesViewHolder(flightItemView);
    }

    @Override
    public void onBindViewHolder(FlightStatesViewHolder holder, int position) {
        UsefulState flightState = flightsStates.get(position);
        holder.icao24TextView.setText(flightState.getIcao24());
    }

    @Override
    public int getItemCount() {
        return flightsStates.size();
    }

    public class FlightStatesViewHolder extends RecyclerView.ViewHolder{
        TextView icao24TextView;

        public FlightStatesViewHolder(View view) {
            super(view);
            icao24TextView = (TextView) view.findViewById(R.id.icao24);

        }
    }
}
