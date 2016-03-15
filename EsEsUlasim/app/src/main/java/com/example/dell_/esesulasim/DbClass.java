package com.example.dell_.esesulasim;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dell- on 28.12.2015.
 */
public class DbClass extends SQLiteOpenHelper {

    private static final String Database_Name = "Ulasimlar";
    private static final int Database_Version = 1;
    // otobus
    private static final String TABLE_OTOBUS = "OTOBUSLER";
    // private static final String KEY_ID = "id";
    private static final String KEY_NAME = "otobusAd";
    ///Durak
    private static final String TABLE_DURAK = "DURAKLAR";
    //private static final String KEY_DurakID = "id";
    private static final String KEY_DURAKNAME = "ISIM";
    private static final String KEY_konumx = "konumx";
    private static final String KEY_konumy = "konumy";


    //guzergah
    private static final String TABLE_guzergah = "guzergahlar";
    // private static final String KEY_gid = "gid";
    private static final String KEY_otoad = "otoad";
    private static final String KEY_durakad = "durad";
    private static final String KEY_saat = "saat";

    private static final String TABLE_Favoriler = "favoriler";
      private static final String KEY_CDurak = "cikis";
      private static final String KEY_VDurak = "varis";
      private static final String KEY_otobus = "otobus";
      //private static final String KEY_sure = "sure";

    public DbClass(Context c) {
        super(c, Database_Name, null, Database_Version);
    }

    public void onCreate(SQLiteDatabase db) {
        //otobus
        String CREATE_OTOBUS_TABLE = "CREATE TABLE " + TABLE_OTOBUS + "("
                /*+ KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT ,"*/
                + KEY_NAME + " TEXT PRIMARY KEY )";

        //String CREATE_OTOBUS_TABLE = "CREATE TABLE " + TABLE_OTOBUS + "(" + KEY_NAME + " TEXT PRIMARY KEY)";
        //durak
        //String CREATE_DURAK_TABLE = "CREATE TABLE " + TABLE_DURAK + "(" + KEY_DURAKNAME + "TEXT PRIMARY KEY)" ;
        String CREATE_DURAK_TABLE = "CREATE TABLE " + TABLE_DURAK + "(" /*+ KEY_DurakID
                + "INTEGER PRIMARY KEY AUTOINCREMENT ,"*/
                + KEY_DURAKNAME + " TEXT PRIMARY KEY,"
                + KEY_konumx + " REAL," + KEY_konumy + " REAL )";

        //guzergah
        //String CREATE_guzergah_TABLE = "CREATE TABLE " + TABLE_guzergah + "("/*+KEY_gid+"integer primary key ,"*/
        //       + KEY_durakid + " INTEGER foreign key references TABLE_Durak ("+KEY_DURAKNAME+"),"
        //       + KEY_otoid + " INTEGER foreign key references TABLE_OTOBUS ("+KEY_NAME+"),"
        //       + KEY_saat + " TEXT ,"+"unique ("+KEY_durakid+","+KEY_otoid+"))";
        String CREATE_guzergah_TABLE = "CREATE TABLE " + TABLE_guzergah + "("
                + KEY_durakad + " TEXT ," + KEY_otoad + " TEXT ," + KEY_saat + " TEXT ,"
                + " foreign key(" + KEY_durakad + ") references TABLE_Durak (" + KEY_DURAKNAME + "),"
                + " foreign key(" + KEY_otoad + ") references TABLE_OTOBUS (" + KEY_NAME + "),"
                + " unique (" + KEY_saat + "," + KEY_otoad + "))";






        db.execSQL(CREATE_DURAK_TABLE);
        db.execSQL("INSERT INTO " + TABLE_DURAK + " VALUES('Tip','37','53');");
        db.execSQL("INSERT INTO " + TABLE_DURAK + " VALUES('Vişnelik','37','66');");
        db.execSQL("INSERT INTO " + TABLE_DURAK + " VALUES('Odunpazarı','37','45');");
        db.execSQL("INSERT INTO " + TABLE_DURAK + " VALUES('Sakarya','37','47');");

        //db.execSQL("INSERT INTO " + TABLE_Durak + " VALUES('mimarlik','M','12');");
        // db.execSQL("INSERT INTO " + TABLE_Durak + " VALUES('egitim','14','14');");

        db.execSQL(CREATE_OTOBUS_TABLE);
        db.execSQL("INSERT INTO " + TABLE_OTOBUS + " VALUES('Kirmizi 19');");
        db.execSQL("INSERT INTO " + TABLE_OTOBUS + " VALUES('Mavi 10');");
        db.execSQL("INSERT INTO " + TABLE_OTOBUS + " VALUES('Kirmizi 14');");
        db.execSQL("INSERT INTO " + TABLE_OTOBUS + " VALUES('Siyah 18');");

        db.execSQL(CREATE_guzergah_TABLE);
        db.execSQL("INSERT INTO " + TABLE_guzergah + " VALUES('Tip','Kirmizi 19','09:30');");
        db.execSQL("INSERT INTO " + TABLE_guzergah + " VALUES('Tip','Kirmizi 19','10:30');");
        db.execSQL("INSERT INTO " + TABLE_guzergah + " VALUES('Tip','Kirmizi 19','11:30');");
        db.execSQL("INSERT INTO " + TABLE_guzergah + " VALUES('Tip','Kirmizi 19','12:30');");
        db.execSQL("INSERT INTO " + TABLE_guzergah + " VALUES('Tip','Kirmizi 19','13:30');");
        db.execSQL("INSERT INTO " + TABLE_guzergah + " VALUES('Tip','Mavi 10','10:20');");
        db.execSQL("INSERT INTO " + TABLE_guzergah + " VALUES('Tip','Mavi 10','11:20');");
        db.execSQL("INSERT INTO " + TABLE_guzergah + " VALUES('Tip','Mavi 10','12:20');");
        db.execSQL("INSERT INTO " + TABLE_guzergah + " VALUES('Vişnelik','Kirmizi 19','09:45');");
        db.execSQL("INSERT INTO " + TABLE_guzergah + " VALUES('Vişnelik','Kirmizi 19','10:45');");
        db.execSQL("INSERT INTO " + TABLE_guzergah + " VALUES('Vişnelik','Kirmizi 19','11:45');");
        db.execSQL("INSERT INTO " + TABLE_guzergah + " VALUES('Vişnelik','Kirmizi 19','12:45');");
        db.execSQL("INSERT INTO " + TABLE_guzergah + " VALUES('Vişnelik','Kirmizi 19','13:45');");
        db.execSQL("INSERT INTO " + TABLE_guzergah + " VALUES('Odunpazarı','Mavi 10','10:45');");
        db.execSQL("INSERT INTO " + TABLE_guzergah + " VALUES('Odunpazarı','Mavi 10','11:45');");
        db.execSQL("INSERT INTO " + TABLE_guzergah + " VALUES('Odunpazarı','Mavi 10','12:45');");
        db.execSQL("INSERT INTO " + TABLE_guzergah + " VALUES('Sakarya','Mavi 10','10:55');");
        db.execSQL("INSERT INTO " + TABLE_guzergah + " VALUES('Sakarya','Mavi 10','11:55');");
        db.execSQL("INSERT INTO " + TABLE_guzergah + " VALUES('Sakarya','Mavi 10','12:55');");
        db.execSQL("INSERT INTO " + TABLE_guzergah + " VALUES('Vişnelik','Kirmizi 14','10:30');");
        db.execSQL("INSERT INTO " + TABLE_guzergah + " VALUES('Vişnelik','Kirmizi 14','11:30');");
        db.execSQL("INSERT INTO " + TABLE_guzergah + " VALUES('Vişnelik','Kirmizi 14','12:30');");
        db.execSQL("INSERT INTO " + TABLE_guzergah + " VALUES('Sakarya','Kirmizi 14','10:45');");
        db.execSQL("INSERT INTO " + TABLE_guzergah + " VALUES('Sakarya','Kirmizi 14','11:45');");
        db.execSQL("INSERT INTO " + TABLE_guzergah + " VALUES('Sakarya','Kirmizi 14','12:45');");
        db.execSQL("INSERT INTO " + TABLE_guzergah + " VALUES('Tip','Siyah 18','10:25');");
        db.execSQL("INSERT INTO " + TABLE_guzergah + " VALUES('Tip','Siyah 18','11:25');");
        db.execSQL("INSERT INTO " + TABLE_guzergah + " VALUES('Tip','Siyah 18','12:25');");
        db.execSQL("INSERT INTO " + TABLE_guzergah + " VALUES('Sakarya','Siyah 18','10:50');");
        db.execSQL("INSERT INTO " + TABLE_guzergah + " VALUES('Sakarya','Siyah 18','11:50');");
        db.execSQL("INSERT INTO " + TABLE_guzergah + " VALUES('Sakarya','Siyah 18','12:50');");


        String CREATE_favoriler_TABLE ="CREATE TABLE " + TABLE_Favoriler + "("
                + KEY_CDurak +  " TEXT ," +KEY_VDurak +" TEXT," +KEY_otobus+" TEXT,"
                + " foreign key(" + KEY_CDurak + ") references TABLE_Durak (" + KEY_DURAKNAME + "),"
                + " foreign key(" + KEY_VDurak + ") references TABLE_Durak (" + KEY_DURAKNAME + "),"
                + " foreign key(" + KEY_otobus + ") references TABLE_OTOBUS (" + KEY_NAME + "))";

        db.execSQL(CREATE_favoriler_TABLE);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

  /*  public void insertLabel(String label) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, label);

        // Inserting Row
        db.insert(TABLE_OTOBUS, null, values);
        db.close(); // Closing database connection
    }*/

    public List<String> getAllLabelsDurak() {
        List<String> labels = new ArrayList<String>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_DURAK;


        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                labels.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }

        // closing connection
        cursor.close();
        db.close();

        // returning lables
        return labels;
    }

    public List<String> getAllLabelsOtobus() {
        List<String> labels = new ArrayList<String>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_OTOBUS;


        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                labels.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }

        // closing connection
        cursor.close();
        db.close();

        // returning lables
        return labels;
    }

    /*public List<String> getAllLabelsGuzergah() {
        List<String> labels = new ArrayList<String>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_guzergah;


        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                labels.add(cursor.getString(1));
            } while (cursor.moveToNext());
        }

        // closing connection
        cursor.close();
        db.close();

        // returning lables
        return labels;
    }*/
}