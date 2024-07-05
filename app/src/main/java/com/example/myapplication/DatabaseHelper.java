package com.example.myapplication;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "results.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "students_final";
    private static final String COLUMN_ROLL_NUMBER = "roll_number";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_SEMESTER_1 = "semester_1";
    private static final String COLUMN_SEMESTER_2 = "semester_2";
    private static final String COLUMN_SEMESTER_3 = "semester_3";
    private static final String i = "semester";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableQuery = "CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_ROLL_NUMBER + " INTEGER PRIMARY KEY, " +
                COLUMN_NAME + " TEXT, " +
                COLUMN_SEMESTER_1 + " INTEGER, " +
                COLUMN_SEMESTER_2 + " INTEGER, " +
                COLUMN_SEMESTER_3 + " INTEGER )";
        db.execSQL(createTableQuery);

        // Insert initial data
        insertInitialData(db);
    }

    private void insertInitialData(SQLiteDatabase db) {

        db.execSQL("INSERT INTO " + TABLE_NAME + " VALUES (101, 'John Doe', 'CGPA: 7.9   Grade: B    [PASS]','CGPA: 10   Grade: O    [PASS]','CGPA: 9.9   Grade: A+    [PASS]')");
        db.execSQL("INSERT INTO " + TABLE_NAME + " VALUES (102, 'Smith Shah', 'Grade: D   [FAIL]','CGPA: 7.2   Grade: B   [PASS]','CGPA: 8.2   Grade: B+   [PASS]')");
        db.execSQL("INSERT INTO " + TABLE_NAME + " VALUES (103, 'Bob Adam','CGPA: 9.7   Grade: A+   [PASS]', 'Grade: D   [FAIL]', 'CGPA: 7.3   Grade: C   [PASS]' )");
        db.execSQL("INSERT INTO " + TABLE_NAME + " VALUES (104, 'Hayes Jordan','CGPA: 9.9   Grade: A+   [PASS]', 'CGPA: 8.1   Grade: A   [PASS]', 'CGPA: 8.7   Grade: A   [PASS]')");
        db.execSQL("INSERT INTO " + TABLE_NAME + " VALUES (105, 'Allen Sick', 'CGPA: 8.4   Grade: A   [PASS]', 'CGPA: 7.6   Grade: B    [PASS]','CGPA: 9.9   Grade: A+   [PASS]' )");
        db.execSQL("INSERT INTO " + TABLE_NAME + " VALUES (106, 'Nick Tenz','Grade: E   [FAIL]', 'CGPA: 8.8   Grade: A+   [PASS]', null)");

        // Add more initial data as needed
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Handle database upgrades if needed
    }

    public String fetchResult(String name, int rollNumber, int semester) {
        SQLiteDatabase db = this.getReadableDatabase();
        String result = null;

        String columnName = i + "_" + semester; // Construct the correct column name
        String[] columns = {columnName};
        String selection = COLUMN_NAME + " = ? AND " + COLUMN_ROLL_NUMBER + " = ?";
        String[] selectionArgs = {name, String.valueOf(rollNumber)};

        Cursor cursor = db.query(TABLE_NAME, columns, selection, selectionArgs, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            result = cursor.getString(cursor.getColumnIndex(columnName));
        }

        if (cursor != null) {
            cursor.close();
        }
        db.close();

        return result;
    }
}