package com.example.nospetitessortieshelloworld.ui.login;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.Array;
import java.util.Arrays;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final int version = 1;
    public static String dbName="Company.db";
    public static final String TABLE_NAME ="Users";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_DEPARTEMENT = "department";
    public static final String COLUMN_PASSWORD = "password";
    private static final String CREATE_TABLE="create table if not exists "+ TABLE_NAME + "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"+COLUMN_NAME+" TEXT NOT NULL,"
            + COLUMN_DEPARTEMENT + " TEXT, " +COLUMN_PASSWORD + " INTEGER);";
    private static final String DROP_TABLE = "DROP TABLE IF EXISTS "+ TABLE_NAME;

    private Context context;

    public DatabaseHelper(Context context) {
        super(context,dbName,null,version);
        context=this.context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL(CREATE_TABLE);
        } catch (Exception e) {
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_TABLE);
        onCreate(db);
    }

    public boolean InsertUser(AppUser objUser)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_NAME,objUser.getName());
        cv.put(COLUMN_DEPARTEMENT,objUser.getDepartement());
        cv.put(COLUMN_PASSWORD,objUser.getPassword());
        long result = db.insert(TABLE_NAME,null,cv);
        if (result == -1)
            return false;
        else
            return true;
    }

    // Credits : https://stackoverflow.com/questions/10600670/sqlitedatabase-query-method
    public boolean existsPseudo(String pseudo) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.query(TABLE_NAME, new String[] {COLUMN_NAME, COLUMN_PASSWORD},  COLUMN_NAME+" = ?", new String[]{pseudo}, null, null, null);
        return c.moveToLast(); // https://developer.android.com/reference/android/database/Cursor#isLast()
    }
}

// Credits : https://dzone.com/articles/sqlite-openhelper-and-database-inspector-in-android#:~:text=SQLiteOpenHelper%20is%20an%20in-built%20class%20of%20android.database.sqlite.SQLiteDatabase%20package.,handling%20database%20manipulations%2C%20and%20also%20the%20version%20management.