package com.zy.fengchun.weatherforecast.adater;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zy.fengchun.weatherforecast.ConstantFiled;
import com.zy.fengchun.weatherforecast.R;
import com.zy.fengchun.weatherforecast.entity.FutureWeather;

import java.util.ArrayList;
import java.util.List;

public class WeatherAdapter extends RecyclerView.Adapter<FutherWeatherViewHolder> {
    private List<FutureWeather> mData = new ArrayList<>();
    private Context mContext;

    public WeatherAdapter(Context context, List<FutureWeather> datas) {
        mData = datas;
        mContext = context;
    }

    @Override
    public FutherWeatherViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_item, parent, false);
        FutherWeatherViewHolder viewHolder = new FutherWeatherViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(FutherWeatherViewHolder holder, int position) {
        holder.mTemperture.setText(mData.get(position).getTemperature());
        holder.mWeatehr.setText(mData.get(position).getWeather());
        holder.mWeek.setText(mData.get(position).getWeek());
        holder.mWind.setText(mData.get(position).getWind());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }
}

class FutherWeatherViewHolder extends RecyclerView.ViewHolder {
    TextView mTemperture;
    TextView mWeatehr;
    TextView mWind;
    TextView mWeek;

    public FutherWeatherViewHolder(View itemView) {
        super(itemView);
        mWeek = (TextView) itemView.findViewById(R.id.item_week);
        mTemperture = (TextView) itemView.findViewById(R.id.item_temperture);
        mWeatehr = (TextView) itemView.findViewById(R.id.item_weather);
        mWind = (TextView) itemView.findViewById(R.id.item_wind);
    }
}
