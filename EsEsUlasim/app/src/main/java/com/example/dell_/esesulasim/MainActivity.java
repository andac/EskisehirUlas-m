package com.example.dell_.esesulasim;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TabHost;

public class MainActivity extends TabActivity {

    TabHost SekmeAlanim;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //SekmeAlanim=(TabHost)findViewById(R.id.tabHost);
        // TabHost=android.R.id.tabHost;
        TabHost SekmeAlanim=getTabHost();
        TabHost.TabSpec sekme;
        Intent i;

        i=new Intent(this,SekmeBir.class);
        sekme=SekmeAlanim.newTabSpec("sekme_bir_ad").setIndicator("Ana menü").setContent(i);
        SekmeAlanim.addTab(sekme);


        i=new Intent(this,SekmeIki.class);
        sekme=SekmeAlanim.newTabSpec("sekme_iki_ad").setIndicator("otobüsler ve güzergahlar").setContent(i);
        SekmeAlanim.addTab(sekme);

        i=new Intent(this,SekmeUc.class);
        sekme=SekmeAlanim.newTabSpec("sekme_uc_ad").setIndicator("duraklar ve süreler").setContent(i);
        SekmeAlanim.addTab(sekme);


        i=new Intent(this,SekmeDort.class);
        sekme=SekmeAlanim.newTabSpec("sekme_dort_ad").setIndicator("favoriler").setContent(i);
        SekmeAlanim.addTab(sekme);

        SekmeAlanim.setCurrentTab(0);
    }


}