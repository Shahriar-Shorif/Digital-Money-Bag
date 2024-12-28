package com.shorifulislam.digitalmoneybag;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DataBaseHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "database";
    public static final int DB_VERSION = 1;

    public DataBaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table expense (id INTEGER primary key autoincrement, amount DOUBLE,reason TEXT, time TEXT) ");
        db.execSQL("create table income (id INTEGER primary key autoincrement, amount DOUBLE,reason TEXT, time TEXT) ");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists income");

    }

    //============================================================//
    public void addExpense(double amount, String reason){
        SQLiteDatabase database = this.getWritableDatabase();

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        String currentDateAndTime = sdf.format(new Date());

        ContentValues conval = new ContentValues();
        conval.put("amount",amount);
        conval.put("reason",reason);
        conval.put("time", currentDateAndTime);
        database.insert("expense",null,conval);
    }
    //============================================================//

    //============================================================//
    public void addIncome(double amount, String reason){
        SQLiteDatabase database = this.getWritableDatabase();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss a");
        String currentDateAndTime = sdf.format(new Date());

        ContentValues conval = new ContentValues();
        conval.put("amount",amount);
        conval.put("reason",reason);
        conval.put("time", currentDateAndTime);
        database.insert("income",null,conval);
    }
    //============================================================//

    public Double CalculateTotalExpense(){
        double TotalExpense = 0;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from expense",null);
        if(cursor!=null && cursor.getCount()>=0){
            for(;cursor.moveToNext();){
                double expense = cursor.getDouble(1);
                TotalExpense = TotalExpense+expense;
            }

        }

        return TotalExpense;
    }

    //============================================================//

    public Double CalculateTotalIncome(){
        double TotalIncome = 0;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from income",null);
        if(cursor!=null && cursor.getCount()>=0){
            for(;cursor.moveToNext();){
                double expense = cursor.getDouble(1);
                TotalIncome = TotalIncome+expense;
            }

        }

        return TotalIncome;
    }
    //============================================================//
    public double CalculateWholeIncome(){
        double To = CalculateTotalIncome()-CalculateTotalExpense();
        return To;
    }

    //============================================================//
    public Cursor getDataExpense(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from expense",null);
        return cursor;
    }
    //=================================================================//
    public Cursor getDataIncome(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from income",null);
        return cursor;
    }

//=====================================================================//
    public void DeleteDataByIdFromExpense(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from expense where id like "+id);
    }

    //=====================================================================//
    public void DeleteDataByIdFromIncome(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from income where id like "+id);
    }


}
