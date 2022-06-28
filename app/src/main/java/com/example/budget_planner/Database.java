package com.example.budget_planner;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Database extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_FILE_NAME = "budget";
    private static final String DATABASE_TABLE_NAME = "budget";
    private static final String PKEY = "id";
    private static final String COL1 = "date";
    private static final String COL2 = "amount";

    Database(Context context) {
        super(context, DATABASE_FILE_NAME, null, DATABASE_VERSION);
    }

    /**
     * SQL Creation of Database
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        String DATABASE_TABLE_CREATE = "CREATE TABLE " + DATABASE_TABLE_NAME + " (" +
                PKEY + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COL1 + " TEXT," +
                COL2 + " TEXT);";
        db.execSQL(DATABASE_TABLE_CREATE);
    }

    /**
     * SQL Update of Database
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion != newVersion) { // Upgrade pas très fin
            db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE_NAME);
            onCreate(db);
        }
    }

    /**
     * SQL Insert info in Database
     */
    public void insertData(double wallet)
    {
        Log.i("Update wallet"," Insert in database");
        SQLiteDatabase db = getWritableDatabase();
        db.beginTransaction();
        ContentValues values = new ContentValues();
        values.put(COL1, new SimpleDateFormat("yyyy/dd/MM").format(Calendar.getInstance().getTime()));
        values.put(COL2, wallet);
        db.insertOrThrow(DATABASE_TABLE_NAME,null, values);
        db.setTransactionSuccessful();
        db.endTransaction();
    }

    /**
     * SQL get latest info from Database
     */
    public double latest() {
        double late = 0;
        String select = new String("SELECT * from " + DATABASE_TABLE_NAME + " ORDER BY " + PKEY + " DESC LIMIT 1");
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(select, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                return late = cursor.getDouble((cursor.getColumnIndex(COL2)));
            } while (cursor.moveToNext());
        }
        return late;
    }
}
