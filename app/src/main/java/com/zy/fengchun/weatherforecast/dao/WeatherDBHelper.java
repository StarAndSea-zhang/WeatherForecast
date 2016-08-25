package com.zy.fengchun.weatherforecast.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.zy.fengchun.weatherforecast.ConstantFiled;
import com.zy.fengchun.weatherforecast.activity.MainActivity;
import com.zy.fengchun.weatherforecast.entity.Result;
import com.zy.fengchun.weatherforecast.entity.ResultCity;

import java.util.List;

/**
 * Created by fengchun on 2016/7/29.
 */
public class WeatherDBHelper extends SQLiteOpenHelper {
    private Context mContext;
    private static final String NAME = "city";
    private static final int VERSION = 1;
    public WeatherDBHelper(Context context){
        super(context, NAME, null, VERSION);
        mContext = context;
    }

    public WeatherDBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table city_table(id int,province varchar(10),city varchar(10),district varchar(10))";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insertData(List<ResultCity> citys){
        WeatherDBHelper dbHelper = new WeatherDBHelper(mContext);
        SQLiteDatabase db =dbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        for(ResultCity city:citys){
            cv.put(ConstantFiled.FIELD_ID, city.getId());
//            cv.put(ConstantFiled.FIELD_PROVINCE, city.getProvince());
            cv.put(ConstantFiled.FIELD_CITY, city.getCity());
            cv.put(ConstantFiled.FIELD_DISTRICT, city.getDistrict());
            db.insert("city_table", null, cv);
        }

        db.close();
    }
}
