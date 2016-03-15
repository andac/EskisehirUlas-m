package com.example.dell_.esesulasim;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by dell- on 28.12.2015.
 */
public class SekmeDort extends AppCompatActivity {
    ArrayList<String> Sonuc = new ArrayList<String>();
    ListView lv;
    DbClass myDb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.sekme_dort);
        myDb=new DbClass(this);
        lv=(ListView)findViewById(R.id.listView2);


        DbAraGosterr();


    }
    private void DbAraGosterr()
    {
        Sonuc.clear();
        SQLiteDatabase db1=myDb.getReadableDatabase();

        String myQuery="Select *  From favoriler ";




        Cursor myC= db1.rawQuery(myQuery, null);

        if(myC!=null){
            if(myC.moveToFirst()){
                do{
                   // String kAd = myC.getString((myC.getColumnIndex("saat")));
                    String kcikis=myC.getString(myC.getColumnIndex("cikis"));
                    String kvaris=myC.getString(myC.getColumnIndex("varis"));
                    String koto=myC.getString(myC.getColumnIndex("otobus"));
                  //  String kvaris=myC.getString(myC.getInt(1));
                  //  String koto=myC.getString(myC.getInt(2));

                    // Sonuclar.add("Id:"+kId+",Ad:"+kAd+",Soyad:"+kSoyAd+",Telefon:"+kTel+",Yası:"+kYas);
                    // Sonuclar+=kAd+" ";
                    Sonuc.add( kcikis + "  " +kvaris+ "  " +koto );

                }while(myC.moveToNext());
            }
        }


        db1.close();
        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line,Sonuc);
        lv.setAdapter(myAdapter);


        //tv.setText(Sonuclar);

    }


}
