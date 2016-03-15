package com.example.dell_.esesulasim;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dell- on 28.12.2015.
 */
public class SekmeBir extends AppCompatActivity {

    Button sonucbtn,varis,harita;
    Spinner spnCikis,spnVaris;
    ListView lv;
    DbClass myDb;


    ArrayList<String> Sonuc = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sekme_bir);

        myDb=new DbClass(this);

        sonucbtn=(Button)findViewById(R.id.button);
        harita=(Button)findViewById(R.id.button3);

        spnCikis=(Spinner)findViewById(R.id.spinner);
        spnVaris=(Spinner)findViewById(R.id.spinner2);
        lv=(ListView)findViewById(R.id.listView3);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
               SQLiteDatabase db1=myDb.getWritableDatabase();

                String myQuery="INSERT INTO  favoriler  VALUES ('"+spnCikis.getSelectedItem().toString()+"','"+spnVaris.getSelectedItem().toString()+"','"+Sonuc.get(position).toString()+"')";

                     db1.execSQL(myQuery);
                Toast.makeText(getApplicationContext(),"FAVORİLERE EKLENDİ " +
                        "!" , Toast.LENGTH_LONG).show();


            }
        });
      sonucbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i;
                i=new Intent(SekmeBir.this,MapsActivity.class);
                startActivity(i);
            }
        });






        //  loadSpinnerDataOtobus();
        loadSpinnerDataDurak();
        /*loadListViewData();*/
        harita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DbAraGosterr(spnCikis.getSelectedItem().toString(), spnVaris.getSelectedItem().toString());

            }
        });

    }

    /* private void loadSpinnerDataOtobus() {
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
          spnVaris.setAdapter(dataAdapter);
       }*/
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
        spnCikis.setAdapter(dataAdap);
        spnVaris.setAdapter(dataAdap);
    }
   /* private void loadListViewData() {
        // database handler
        DbClass db = new DbClass(getApplicationContext());

        // Spinner Drop down elements
        List<String> lables = db.getAllLabelsGuzergah();

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, lables);

        // attaching data adapter to spinner
        lv.setAdapter(dataAdapter);
    }*/

    private void DbAraGosterr(String cik,String var)
    {
        Sonuc.clear();
        SQLiteDatabase db1=myDb.getReadableDatabase();
        // String myQuery="Select * From OTOBUSLER where OtobusAd like '%" + Ad + "%'";
        //String myQuery="Select Duraklar.ISIM From DURAKLAR INNER JOIN guzergahlar INNER JOIN OTOBUSLER ON DURAKLAR.id=guzergahlar.durid and OTOBUSLER.id=guzergahlar.otoid where OTOBUSLER.otobusAd like "+Ad+"";
        // String myQuery="Select guzergahlar.durid From guzergahlar where guzergahlar.otoid like '%" + Ad + "%'";
        String myQuery="Select guzergahlar.otoad From guzergahlar where guzergahlar.durad like '%" + cik + "%'"
                +"INTERSECT Select guzergahlar.otoad From guzergahlar where guzergahlar.durad like '%" + var + "%'";




        Cursor myC= db1.rawQuery(myQuery, null);

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
                    Sonuc.add( kAd );

                }while(myC.moveToNext());
            }
        }


        db1.close();
        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line,Sonuc);
        lv.setAdapter(myAdapter);


        //tv.setText(Sonuclar);

    }
  /*  private void DbAraGoster(String Ad)
    {
        Sonuclar.clear();
        SQLiteDatabase db1=myDb.getWritableDatabase();
        String myQuery="Select * From kisiler where Kisi_Adi like '%"+Ad+"%'";
        Cursor myC= db1.rawQuery(myQuery,null);

        if(myC!=null){
            if(myC.moveToFirst()){
                do{
                    String kId=Integer.toString(myC.getInt(myC.getColumnIndex("Kisi_Id")));
                    String kAd=myC.getString(myC.getInt(myC.getColumnIndex("Kisi_Adi")));
                    String kSoyAd=myC.getString(myC.getInt(myC.getColumnIndex("Kisi_Soyadi")));
                    String kTel=myC.getString(myC.getInt(myC.getColumnIndex("Kisi_Tel")));
                    String kYas=myC.getString(myC.getInt(myC.getColumnIndex("Kisi_Yasi")));
                    Sonuclar.add("Id:"+kId+",Ad:"+kAd+",Soyad:"+kSoyAd+",Telefon:"+kTel+",Yası:"+kYas);

                }while(myC.moveToNext());
            }
        }
        db1.close();
        ArrayAdapter<String> myAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line);
        lv.setAdapter(myAdapter);

    }*/
}
