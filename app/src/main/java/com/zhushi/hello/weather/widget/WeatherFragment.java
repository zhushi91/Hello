package com.zhushi.hello.weather.widget;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.zhushi.hello.R;
import com.zhushi.hello.beans.WeatherBean;
import com.zhushi.hello.weather.presenter.WeatherPresenter;
import com.zhushi.hello.weather.presenter.WeatherPresenterImpl;
import com.zhushi.hello.weather.view.WeatherView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhushi on 2016/3/2.
 */
public class WeatherFragment extends Fragment implements WeatherView {

    private WeatherPresenter mWeatherPresenter;

    private TextView mTodayTV;
    private ImageView mTodayWeatherImage;
    private TextView mTodayTemperatureTV;
    private TextView mTodayWindTV;
    private TextView mTodayWeatherTV;
    private TextView mCityTV;
    private ProgressBar mProgressBar;
    private LinearLayout mWeatherLayout;
    private LinearLayout mWeatherContentLayout;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mWeatherPresenter = new WeatherPresenterImpl(getActivity().getApplication(), this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_weather, container, false);
        mTodayTV = (TextView) view.findViewById(R.id.today);
        mTodayWeatherImage = (ImageView) view.findViewById(R.id.weatherImage);
        mTodayTemperatureTV = (TextView) view.findViewById(R.id.weatherTemp);
        mTodayWindTV = (TextView) view.findViewById(R.id.wind);
        mTodayWeatherTV = (TextView) view.findViewById(R.id.weather);
        mCityTV = (TextView) view.findViewById(R.id.city);
        mProgressBar = (ProgressBar) view.findViewById(R.id.progress);
        mWeatherLayout = (LinearLayout) view.findViewById(R.id.weather_layout);
        mWeatherContentLayout = (LinearLayout) view.findViewById(R.id.weather_content);

        mWeatherPresenter.loadWeatherData();
        return view;
    }

    @Override
    public void showProgress() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void showWeatherLayout() {
        mWeatherLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void setCity(String city) {
        mCityTV.setText(city);
    }

    @Override
    public void setToday(String data) {
        mTodayTV.setText(data);
    }

    @Override
    public void setTemperature(String temperature) {
        mTodayTemperatureTV.setText(temperature);
    }

    @Override
    public void setWind(String wind) {
        mTodayWindTV.setText(wind);
    }

    @Override
    public void setWeather(String weather) {
        mTodayWeatherTV.setText(weather);
    }

    @Override
    public void setWeatherImage(int res) {
        mTodayWeatherImage.setImageResource(res);
    }

    @Override
    public void setWeatherData(List<WeatherBean> lists) {
        List<View> adapterList = new ArrayList<View>();
        for (WeatherBean weatherBean : lists) {
            View view = LayoutInflater.from(getActivity()).inflate(R.layout.item_weather, mWeatherContentLayout, false);
            TextView dateTV = (TextView) view.findViewById(R.id.date);
            ImageView todayWeatherImage = (ImageView) view.findViewById(R.id.weatherImage);
            TextView todayTemperatureTV = (TextView) view.findViewById(R.id.weatherTemp);
            TextView todayWindTV = (TextView) view.findViewById(R.id.wind);
            TextView todayWeatherTV = (TextView) view.findViewById(R.id.weather);

            dateTV.setText(weatherBean.getWeek());
            todayTemperatureTV.setText(weatherBean.getTemperature());
            todayWindTV.setText(weatherBean.getWind());
            todayWeatherTV.setText(weatherBean.getWeather());
            todayWeatherImage.setImageResource(weatherBean.getImageRes());
            mWeatherContentLayout.addView(view);
            adapterList.add(view);
        }
    }

    @Override
    public void showErrorToast(String msg) {
        Snackbar.make(getActivity().findViewById(R.id.drawer_layout), msg, Snackbar.LENGTH_SHORT).show();
    }
}
