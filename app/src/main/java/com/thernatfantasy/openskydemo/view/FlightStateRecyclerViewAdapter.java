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
        holder.tvIcao24.setText(flightState.getIcao24());
        holder.tvCallsign.setText(flightState.getCallSign());
        holder.tvCountryOfOrigin.setText(flightState.getOriginCountry());
    }

    @Override
    public int getItemCount() {
        return flightsStates.size();
    }

    public class FlightStatesViewHolder extends RecyclerView.ViewHolder{
        TextView tvIcao24;
        TextView tvCallsign;
        TextView tvCountryOfOrigin;

        public FlightStatesViewHolder(View view) {
            super(view);
            tvIcao24 = (TextView) view.findViewById(R.id.tv_icao24);
            tvCallsign = (TextView) view.findViewById(R.id.tv_callsign);
            tvCountryOfOrigin = (TextView) view.findViewById(R.id.tv_country_of_origin);
        }
    }
}
