package com.example.dell_.esesulasim;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dell- on 28.12.2015.
 */
public class SekmeIki extends AppCompatActivity {
    ArrayList<String> Sonuclar = new ArrayList<String>();
    ListView myListKisiler;
    // String Sonuclar="";
    Button btnGuzergah;
    Spinner spnOtbs;
    // TextView tv;
    DbClass myDb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sekme_iki);
        spnOtbs=(Spinner)findViewById(R.id.spinner3);
        //tv=(TextView)findViewById(R.id.textView);
        btnGuzergah=(Button)findViewById(R.id.button4);
        myListKisiler=(ListView)findViewById(R.id.listView4);
        loadSpinnerDataOtobus();
        myDb = new DbClass(this);
        btnGuzergah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DbAraGoster(spnOtbs.getSelectedItem().toString());
                // DbAraGoster(spnOtbs.getItemAtPosition(spnOtbs.getSelectedItemPosition()).toString());
                //  DbAraGoster("Kirmizi 19");
            }
        });
    }

    private void loadSpinnerDataOtobus() {
        // database handler
        DbClass db = new DbClass(getApplicationContext());

        // Spinner Drop down elements
        List<String> lables = db.getAllLabelsOtobus();

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, lables);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spnOtbs.setAdapter(dataAdapter);

    }

    private void DbAraGoster(String Ad)
    {
        Sonuclar.clear();
        SQLiteDatabase db1=myDb.getReadableDatabase();
        // String myQuery="Select * From OTOBUSLER where OtobusAd like '%" + Ad + "%'";
        //String myQuery="Select Duraklar.ISIM From DURAKLAR INNER JOIN guzergahlar INNER JOIN OTOBUSLER ON DURAKLAR.id=guzergahlar.durid and OTOBUSLER.id=guzergahlar.otoid where OTOBUSLER.otobusAd like "+Ad+"";
        String myQuery="Select DISTINCT guzergahlar.durad From guzergahlar where guzergahlar.otoad like '%" + Ad + "%' order by guzergahlar.saat";
        Cursor myC= db1.rawQuery(myQuery,null);

        if(myC!=null){
            if(myC.moveToFirst()){
                do{
                    // String kId=Integer.toString(myC.getInt(0));
                    String kAd=myC.getString(myC.getInt(0));
                    // String kSoyAd=myC.getString(myC.getInt(myC.getColumnIndex("Kisi_Soyadi")));
                    // String kTel=myC.getString(myC.getInt(myC.getColumnIndex("Kisi_Tel")));
                    // String kYas=myC.getString(myC.getInt(myC.getColumnIndex("Kisi_Yasi")));
                    // Sonuclar.add("Id:"+kId+",Ad:"+kAd+",Soyad:"+kSoyAd+",Telefon:"+kTel+",Yası:"+kYas);
                    // Sonuclar+=kAd+" ";
                    Sonuclar.add( kAd );

                }while(myC.moveToNext());
            }
        }
        db1.close();
        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line,Sonuclar);
        myListKisiler.setAdapter(myAdapter);


        //tv.setText(Sonuclar);

    }
}
