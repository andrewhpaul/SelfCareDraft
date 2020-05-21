package com.a_adevelopers.selfcare.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.a_adevelopers.selfcare.Model.GroupModel;
import com.a_adevelopers.selfcare.Model.Model;
import com.a_adevelopers.selfcare.Model.NotiModel;

import java.util.ArrayList;

public class SqlLite extends SQLiteOpenHelper {
    public  static  final String DATABASE_NAME="sc.dp";


    public SqlLite(@Nullable Context context) {
        super( context,DATABASE_NAME , null, 1 );
    }

    @Override
    public void onCreate(SQLiteDatabase db) {


        final String noti="Create table noti ( id integer Primary key autoincrement,image text, name text , expDate text,selectedDate text   );";
        final String groups="Create table groups ( id integer Primary key autoincrement, name text , day text  );";
        final String p_groups="Create table p_groups ( id integer Primary key autoincrement, name text , g_id text  );";

        db.execSQL( noti );
        db.execSQL( groups );
        db.execSQL( p_groups );
        db.execSQL( Constants.CREATE_TABLE );




    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
      //  db.execSQL( "Drop table if exists noti" );

    }
    public void deleteInfo(String id) {

        SQLiteDatabase db = getWritableDatabase();
        db.delete(Constants.TABLE_NAME, Constants.C_ID + " = ?", new String[]{id});
        db.close();
    }
    public void deleteGroup(String id) {

        SQLiteDatabase db = getWritableDatabase();
        db.delete("groups", Constants.C_ID + " = ?", new String[]{id});
        db.close();
    }
    public void deleteNoti(String id) {

        SQLiteDatabase db = getWritableDatabase();
        db.delete("noti", Constants.C_ID + " = ?", new String[]{id});
        db.close();
    }
    public long insertInfo(String name, String day ) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("day", day);

        long id = db.insert("groups", null, values);
        db.close();
        return id;
    }

    public void updateInfo(String id, String image, String name, String purchaseDate, String expiryDate, String url, String price, String location, String productGroup, String tag, String notes, String rating) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Constants.C_NAME, name);
        values.put(Constants.C_PURCHASE_DATE, purchaseDate);
        values.put(Constants.C_EXPIRY_DATE, expiryDate);
        values.put(Constants.C_IMAGE, image);
        values.put(Constants.C_URL, url);
        values.put(Constants.C_PRICE, price);
        values.put(Constants.C_LOCATION, location);
        values.put(Constants.C_PRODUCT_GROUP, productGroup);
        values.put(Constants.C_TAG, tag);
        values.put(Constants.C_NOTES, notes);
        values.put(Constants.C_RATING, rating);

        db.update(Constants.TABLE_NAME, values, Constants.C_ID + " = ?", new String[]{id});
        db.close();
    }


    public long insertInfo(String image, String name, String purchaseDate, String expiryDate, String url, String price, String location, String productGroup, String tag, String notes, String rating) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Constants.C_NAME, name);
        values.put(Constants.C_PURCHASE_DATE, purchaseDate);
        values.put(Constants.C_EXPIRY_DATE, expiryDate);
        values.put(Constants.C_IMAGE, image);
        values.put(Constants.C_URL, url);
        values.put(Constants.C_PRICE, price);
        values.put(Constants.C_LOCATION, location);
        values.put(Constants.C_PRODUCT_GROUP, productGroup);
        values.put(Constants.C_TAG, tag);
        values.put(Constants.C_NOTES, notes);
        values.put(Constants.C_RATING, rating);

        long id = db.insert(Constants.TABLE_NAME, null, values);
        db.close();
        return id;
    }
    public ArrayList<Model> getAllData() {

        ArrayList<Model> arrayList = new ArrayList<>();
        // query for select info
        String selectQuery = "SELECT * FROM " + Constants.TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToNext()) {
            do {
                Model model = new Model(
                        ""+cursor.getInt(cursor.getColumnIndex(Constants.C_ID)),
                        ""+cursor.getString(cursor.getColumnIndex(Constants.C_IMAGE)),
                        ""+cursor.getString(cursor.getColumnIndex(Constants.C_NAME)),
                        ""+cursor.getString(cursor.getColumnIndex(Constants.C_PURCHASE_DATE)),
                        ""+cursor.getString(cursor.getColumnIndex(Constants.C_EXPIRY_DATE)),
                        ""+cursor.getString(cursor.getColumnIndex(Constants.C_URL)),
                        ""+cursor.getString(cursor.getColumnIndex(Constants.C_PRICE)),
                        ""+cursor.getString(cursor.getColumnIndex(Constants.C_LOCATION)),
                        ""+cursor.getString(cursor.getColumnIndex(Constants.C_PRODUCT_GROUP)),
                        ""+cursor.getString(cursor.getColumnIndex(Constants.C_TAG)),
                        ""+cursor.getString(cursor.getColumnIndex(Constants.C_NOTES)),
                        ""+cursor.getString(cursor.getColumnIndex(Constants.C_RATING))
                );
                arrayList.add(model);
            }while (cursor.moveToNext());
        }

        db.close();
        return arrayList;
    }

    public long insertInfo(String image, String name, String expiryDate, String purchaseDate) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("image", image);
        values.put("expDate", expiryDate);
        values.put("selectedDate", purchaseDate);
        long id = db.insert("noti", null, values);
        db.close();
        return id;
    }

    public ArrayList<NotiModel> getAllNotification() {

        ArrayList<NotiModel> arrayList = new ArrayList<>();
        // query for select info
        String selectQuery = "SELECT * FROM noti";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToNext()) {
            do {
                NotiModel model = new NotiModel(
                        ""+cursor.getInt(cursor.getColumnIndex("id")),
                        ""+cursor.getString(cursor.getColumnIndex("image")),
                        ""+cursor.getString(cursor.getColumnIndex("name")),
                        ""+cursor.getString(cursor.getColumnIndex("expDate")),
                        ""+cursor.getString(cursor.getColumnIndex("selectedDate"))

                        );
                arrayList.add(model);
            }while (cursor.moveToNext());
        }

        db.close();
        return arrayList;
    }

    public ArrayList<GroupModel> getAllGroups() {

        ArrayList<GroupModel> arrayList = new ArrayList<>();
        // query for select info
        String selectQuery = "SELECT * FROM groups";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToNext()) {
            do {
                GroupModel model = new GroupModel(
                        ""+cursor.getInt(cursor.getColumnIndex("id")),
                        ""+cursor.getString(cursor.getColumnIndex("name")),
                        ""+cursor.getString(cursor.getColumnIndex("day"))


                );
                arrayList.add(model);
            }while (cursor.moveToNext());
        }

        db.close();
        return arrayList;
    }

    public void updateGroup(String id, String name, String day) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("day", day);
        db.update(Constants.TABLE_NAME, values, Constants.C_ID + " = ?", new String[]{id});
        db.close();
    }


   /* public long insertInfo(String image, String name, String purchaseDate, String expiryDate) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Constants.C_NAME, name);
        values.put(Constants.C_PURCHASE_DATE, purchaseDate);
        values.put(Constants.C_EXPIRY_DATE, expiryDate);
        values.put(Constants.C_IMAGE, image);
        values.put(Constants.C_URL, url);
        values.put(Constants.C_PRICE, price);
        values.put(Constants.C_LOCATION, location);
        values.put(Constants.C_PRODUCT_GROUP, productGroup);
        values.put(Constants.C_TAG, tag);
        values.put(Constants.C_NOTES, notes);
        values.put(Constants.C_RATING, rating);

        long id = db.insert(Constants.TABLE_NAME, null, values);
        db.close();
        return id;
    }

   */
}
