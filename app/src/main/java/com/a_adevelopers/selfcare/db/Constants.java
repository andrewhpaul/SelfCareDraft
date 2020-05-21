package com.a_adevelopers.selfcare.db;

public class Constants {

    // db name
    public static final String DB_NAME = "sc";
    // db version
    public static final int DB_VERSION = 1;
    // db table name
    public static final String TABLE_NAME = "sc";
    // columns of table
    public static final String C_ID = "ID";
    public static final String C_NAME = "NAME";
    public static final String C_PURCHASE_DATE = "PURCHASE_DATE";
    public static final String C_EXPIRY_DATE = "EXPIRY_DATE";
    public static final String C_IMAGE = "IMAGE";
    public static final String C_URL = "URL";
    public static final String C_PRICE = "PRICE";
    public static final String C_LOCATION = "LOCATION";
    public static final String C_PRODUCT_GROUP = "PRODUCT_GROUP";
    public static final String C_TAG = "TAG";
    public static final String C_NOTES = "NOTES";
    public static final String C_RATING = "RATING";
    // create table query
    public static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " ("
            + C_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + C_NAME + " TEXT,"
            + C_PURCHASE_DATE + " TEXT,"
            + C_EXPIRY_DATE + " TEXT,"
            + C_IMAGE + " TEXT,"
            + C_URL + " TEXT,"
            + C_PRICE + " TEXT,"
            + C_LOCATION + " TEXT,"
            + C_PRODUCT_GROUP+ " TEXT,"
            + C_TAG + " TEXT,"
            + C_NOTES + " TEXT,"
            + C_RATING + " TEXT"
            + ");";

}
