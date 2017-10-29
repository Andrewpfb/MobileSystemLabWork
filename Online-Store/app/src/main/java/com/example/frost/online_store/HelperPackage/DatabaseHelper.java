package com.example.frost.online_store.HelperPackage;

/**
 * Created by frost on 28.10.2017.
 */

import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.content.Context;
import android.content.ContentValues;
import android.graphics.Matrix;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "userstore.db"; // название бд
    private static final int SCHEMA = 1; // версия базы данных
    public static final String TABLE = "users"; // название таблицы в бд
    // названия столбцов
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_YEAR = "year";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, SCHEMA);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE users (" + COLUMN_ID
                + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_NAME
                + " TEXT, " + COLUMN_YEAR + " INTEGER);");
        // добавление начальных данных
        db.execSQL("INSERT INTO "+ TABLE +" (" + COLUMN_NAME
                + ", " + COLUMN_YEAR  + ") VALUES ('Том Смит', 1981);");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion,  int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE);
        onCreate(db);
    }
}

    /*private static final String DATABASE_NAME = "onlinestore.db";
    private static final int SCHEMA = 1;
    public static final String TABLE = "products";

    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_CATEGORY = "category";
    public static final String COLUMN_PRICE = "price";
    public static final String COLUMN_COUNT = "count";
    //public static final String COLUMN_IMAGEPATH = "imagePath";

    public DatabaseHelper (Context context){
        super(context,DATABASE_NAME,null,SCHEMA);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE products (" + COLUMN_ID
        + "INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_NAME
        + " TEXT, " + COLUMN_CATEGORY + " TEXT, " + COLUMN_PRICE + "DOUBLE, "
        + COLUMN_COUNT + " INTEGER);");//, " + COLUMN_IMAGEPATH + " TEXT);");

        db.execSQL("INSERT INTO "+ TABLE +" (" + COLUMN_NAME
                + ", " + COLUMN_CATEGORY
                + ", " + COLUMN_PRICE
                + ", " + COLUMN_COUNT + ") VALUES ('Acer', 'Notebook', 1100, 5);");
        db.execSQL("INSERT INTO "+ TABLE +" (" + COLUMN_NAME
                + ", " + COLUMN_CATEGORY
                + ", " + COLUMN_PRICE
                + ", " + COLUMN_COUNT + ") VALUES ('Lenovo', 'Notebook', 1200, 15);");
        db.execSQL("INSERT INTO "+ TABLE +" (" + COLUMN_NAME
                + ", " + COLUMN_CATEGORY
                + ", " + COLUMN_PRICE
                + ", " + COLUMN_COUNT + ") VALUES ('Asus', 'Notebook', 1300, 25);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE);
        onCreate(db);
    }
}*/
