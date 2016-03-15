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
public class SekmeUc extends AppCompatActivity {

    Spinner spinDurak, spinOtobus;
    ListView lvsure;
    ArrayList<String> Sonuc3 = new ArrayList<String>();
    DbClass myDb3;
    Button btnSaat,btndurak;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sekme_uc);
        myDb3 = new DbClass(this);


        spinDurak = (Spinner) findViewById(R.id.spinner4);
        spinOtobus = (Spinner) findViewById(R.id.spinner5);
        lvsure=(ListView)findViewById(R.id.listView2);
        btnSaat=(Button)findViewById(R.id.button5);
        btndurak=(Button)findViewById(R.id.button6);
        loadSpinnerDataDurak();
        loadSpinnerDataOtobus();

        btndurak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spinOtobus.setPrompt("Durak Şeç");
                DbDurakGoster(spinDurak.getSelectedItem().toString());
            }
        });
        btnSaat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DbAraGosterrr(spinDurak.getSelectedItem().toString(), spinOtobus.getSelectedItem().toString());

            }
        });
    }

    private void loadSpinnerDataDurak() {
        // database handler

        DbClass db = new DbClass(getApplicationContext());

        // Spinner Drop down elements
        List<String> lables = db.getAllLabelsDurak();

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdap = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, lables);

        // Drop down layout style - list view with radio button
        dataAdap.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinDurak.setAdapter(dataAdap);
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
        spinOtobus.setAdapter(dataAdapter);

    }
    private void DbAraGosterrr(String dur,String oto )
    {
        Sonuc3.clear();
        SQLiteDatabase db1=myDb3.getReadableDatabase();
        // String myQuery="Select * From OTOBUSLER where OtobusAd like '%" + Ad + "%'";
        //String myQuery="Select Duraklar.ISIM From DURAKLAR INNER JOIN guzergahlar INNER JOIN OTOBUSLER ON DURAKLAR.id=guzergahlar.durid and OTOBUSLER.id=guzergahlar.otoid where OTOBUSLER.otobusAd like "+Ad+"";
        String myQuery="Select guzergahlar.saat ,guzergahlar.otoad From guzergahlar where guzergahlar.durad like '%" +dur+ "%' and guzergahlar.otoad like '%" +oto+ "%' order by guzergahlar.saat ";
        // +"INTERSECT Select guzergahlar.saat From guzergahlar where guzergahlar.otoid like '%" + oto + "%'";
        Cursor myC= db1.rawQuery(myQuery,null);

        if(myC!=null){
            if(myC.moveToFirst()){
                do{
                    // String kId=Integer.toString(myC.getInt(0));
                    // String kAd=myC.getString(myC.getInt(myC.getColumnIndex("saat")));
                    String kAd = myC.getString((myC.getColumnIndex("saat")));
                    String kAdi = myC.getString((myC.getColumnIndex("otoad")));

                    // String kAd=myC.getString(myC.getInt(0));
                    // String kSoyAd=myC.getString(myC.getInt(myC.getColumnIndex("Kisi_Soyadi")));
                    // String kTel=myC.getString(myC.getInt(myC.getColumnIndex("Kisi_Tel")));
                    // String kYas=myC.getString(myC.getInt(myC.getColumnIndex("Kisi_Yasi")));
                    // Sonuclar.add("Id:"+kId+",Ad:"+kAd+",Soyad:"+kSoyAd+",Telefon:"+kTel+",Yası:"+kYas);
                    // Sonuclar+=kAd+" ";
                    Sonuc3.add( kAd +"  "+kAdi);

                }while(myC.moveToNext());
            }
        }
        db1.close();
        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line,Sonuc3);
        lvsure.setAdapter(myAdapter);


        //tv.setText(Sonuclar);

    }
    private void DbDurakGoster(String dur )
    {
        Sonuc3.clear();
        SQLiteDatabase db1=myDb3.getReadableDatabase();
        // String myQuery="Select * From OTOBUSLER where OtobusAd like '%" + Ad + "%'";
        //String myQuery="Select Duraklar.ISIM From DURAKLAR INNER JOIN guzergahlar INNER JOIN OTOBUSLER ON DURAKLAR.id=guzergahlar.durid and OTOBUSLER.id=guzergahlar.otoid where OTOBUSLER.otobusAd like "+Ad+"";
        String myQuery="Select guzergahlar.saat,guzergahlar.otoad  From guzergahlar where guzergahlar.durad like '%" +dur+ "%' order by guzergahlar.saat ";
        // +"INTERSECT Select guzergahlar.saat From guzergahlar where guzergahlar.otoid like '%" + oto + "%'";
        Cursor myC= db1.rawQuery(myQuery,null);

        if(myC!=null){
            if(myC.moveToFirst()){
                do{
                    // String kId=Integer.toString(myC.getInt(0));


                    String kAd = myC.getString((myC.getColumnIndex("saat")));
                    String kAdi = myC.getString((myC.getColumnIndex("otoad")));




                    Sonuc3.add( kAd+"  "+kAdi );

                }while(myC.moveToNext());
            }
        }
        db1.close();
        ArrayAdapter<String> myAdapt = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line,Sonuc3);
        lvsure.setAdapter(myAdapt);




    }
}
