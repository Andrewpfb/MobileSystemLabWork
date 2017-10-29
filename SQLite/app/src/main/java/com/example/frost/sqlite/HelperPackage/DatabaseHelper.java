package com.example.frost.sqlite.HelperPackage;

/**
 * Created by frost on 28.10.2017.
 */

import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.content.Context;
import android.content.ContentValues;

import com.example.frost.sqlite.Interface.IDatabaseHelper;
import com.example.frost.sqlite.ProductPackage.Product;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper implements IDatabaseHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "productsManager";
    private static final String TABLE_PRODUCTS = "products";
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_CATEGORY = "category";
    private static final String KEY_PRICE = "price";
    private static final String KEY_COUNT = "count";
    private static final String KEY_IMAGE_PATH = "image_path";
    private static final  String KEY_FAVORITE = "favorite";

    public DatabaseHelper (Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate (SQLiteDatabase db){
        String CREATE_PRODUCTS_TABLE = "CREATE TABLE " + TABLE_PRODUCTS + "("
                + KEY_ID + " INTEGER PRIMARY KEY, " + KEY_NAME + " TEXT, "
                + KEY_CATEGORY + " TEXT," + KEY_PRICE + " REAL,"
                + KEY_COUNT + " INTEGER, " + KEY_IMAGE_PATH + " TEXT"
                + KEY_FAVORITE + " INTEGER " + ")";
        db.execSQL(CREATE_PRODUCTS_TABLE);
    }
    @Override
    public void onUpgrade (SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCTS);
        onCreate(db);
    }

    @Override
    public void addProduct(Product product) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, product.getName());
        values.put(KEY_CATEGORY, product.getCategory());
        values.put(KEY_PRICE, product.getPrice());
        values.put(KEY_COUNT, product.getCount());
        values.put(KEY_IMAGE_PATH, product.getImagePath());

        db.insert(TABLE_PRODUCTS, null, values);
        db.close();
    }

    @Override
    public Product getProduct(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(
                TABLE_PRODUCTS,
                new String[]{KEY_ID, KEY_NAME,KEY_CATEGORY,KEY_PRICE,KEY_COUNT,KEY_IMAGE_PATH},
                 KEY_ID + "=?",
                new String[]{String.valueOf(id)},
                null,
                null,
                null,
                null
        );

        if(cursor!=null){
            cursor.moveToFirst();
        }

        Product product = new Product(cursor.getInt(0),cursor.getString(1),cursor.getString(2),
                cursor.getDouble(3),cursor.getInt(4), cursor.getString(5));

        cursor.close();
        db.close();
        return product;
    }

    @Override
    public Product getProductByName(String name){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(
                TABLE_PRODUCTS,
                new String[]{KEY_ID, KEY_NAME,KEY_CATEGORY,KEY_PRICE,KEY_COUNT,KEY_IMAGE_PATH},
                KEY_NAME + "=?",
                new String[]{String.valueOf(name)},
                null,
                null,
                null,
                null
        );

        if(cursor!=null){
            cursor.moveToFirst();
        }

        Product product = new Product(cursor.getInt(0),cursor.getString(1),cursor.getString(2),
                cursor.getDouble(3),cursor.getInt(4), cursor.getString(5));

        cursor.close();
        db.close();
        return product;
    }

    @Override
    public List<Product> getAllProducts() {
        List<Product> productList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_PRODUCTS + " ORDER BY " + KEY_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null);

        if(cursor.moveToFirst()){
            do{
                Product product = new Product();
                product.setId(cursor.getInt(0));
                product.setName(cursor.getString(1));
                product.setCategory(cursor.getString(2));
                product.setPrice(cursor.getDouble(3));
                product.setCount(cursor.getInt(4));
                product.setImagePath(cursor.getString(5));
                productList.add(product);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return productList;
    }

    @Override
    public int getProductsCount() {
        String countQuery = "SELECT * FROM " + TABLE_PRODUCTS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery,null);

        cursor.close();
        db.close();

        return cursor.getCount();
    }

    @Override
    public int updateProduct(Product product) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, product.getName());
        values.put(KEY_CATEGORY, product.getCategory());
        values.put(KEY_PRICE, product.getPrice());
        values.put(KEY_COUNT, product.getCount());
        values.put(KEY_IMAGE_PATH, product.getImagePath());

        return db.update(TABLE_PRODUCTS, values, KEY_ID + " =?", new String[]{String.valueOf(product.getId())});
    }

    @Override
    public void deleteProduct(Product product) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_PRODUCTS, KEY_ID + " =?", new String[]{String.valueOf(product.getId())});
        db.close();;
    }

    @Override
    public void deleteAllProducts() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_PRODUCTS, null, null);
        db.close();
    }
}
