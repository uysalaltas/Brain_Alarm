package com.example.uysal.brain_alarm.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AlertDialog;

import com.example.uysal.brain_alarm.data.AlarmContract;

public class AlarmsDbHelper extends SQLiteOpenHelper {

    public static final String LOG_TAG = AlarmsDbHelper.class.getSimpleName();

    private static final String DATABASE_NAME = "alarms.db";

    private static final int DATABASE_VERSION = 1;


    /**
     * Constructs a new instance of {@link AlarmsDbHelper}.
     *
     * @param context of the app
     */
    public AlarmsDbHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

//        Snipped form;
//        Create a String that contains the SQL statement to create the pets table
//        String SQL_CREATE_PETS_TABLE =  "CREATE TABLE " + PetEntry.TABLE_NAME + " ("
//                + PetEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
//                + PetEntry.COLUMN_PET_NAME + " TEXT NOT NULL, "
//                + PetEntry.COLUMN_PET_BREED + " TEXT, "
//                + PetEntry.COLUMN_PET_GENDER + " INTEGER NOT NULL, "
//                + PetEntry.COLUMN_PET_WEIGHT + " INTEGER NOT NULL DEFAULT 0);";

        String SQL_CREATE_ALARMS_TABLE = "CREATE TABLE " + AlarmContract.AlarmEntry.TABLE_NAME
                + " (" + AlarmContract.AlarmEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + AlarmContract.AlarmEntry.COLUMN_ALARM + " TEXT NOT NULL, "
                + AlarmContract.AlarmEntry.COLUMN_STATUS + " INTEGER NOT NULL DEFAULT 1);";

        db.execSQL(SQL_CREATE_ALARMS_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


}
