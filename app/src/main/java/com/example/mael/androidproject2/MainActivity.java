package com.example.mael.androidproject2;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.mael.androidproject2.formulaire.BaseDonne.AddBaseJSON;
import com.example.mael.androidproject2.formulaire.FormFragment;
import com.example.mael.androidproject2.formulaire.JSONGetter;
import com.example.mael.androidproject2.liste.BaseDonne.AddBase;
import com.example.mael.androidproject2.liste.ItemListeFrigo;
import com.example.mael.androidproject2.map.MapsActivity;
import com.example.mael.androidproject2.reseau.ItemReseau;
import com.google.android.gms.maps.MapsInitializer;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.util.List;
import java.util.concurrent.ExecutionException;

import traitement.Produit;

public class MainActivity extends AppCompatActivity implements OnFragmentInteractionListener {
    Fragment [] Frags = new Fragment[4] ;
    public static FragmentManager fragmentManager;
    BalladesFragsPagerAdapter balladesFragsPagerAdapter;
    ViewPager mViewPager;
    public static Context CONTEXT;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CONTEXT = getBaseContext();
        fragmentManager = getSupportFragmentManager();
        setContentView(R.layout.activity_main);
        Frags[0] = ItemReseau.newInstance(1);
        Frags[1] = new MapsActivity();
        Frags[2] = ItemListeFrigo.newInstance(1);
        Frags[3] = FormFragment.newInstance("form", "form");
        balladesFragsPagerAdapter = new BalladesFragsPagerAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(balladesFragsPagerAdapter);
        addBDJsonAliment();
        //Partie initialisation BD avec le json des aliments
        AddBaseJSON BDJSON = AddBaseJSON.getInstance(CONTEXT);
        List<String> aliment = BDJSON.getProduit();
        for (String s: aliment) {
            System.out.println("ingredient : " +s);
        }
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        ActionBar.TabListener tabListener = new ActionBar.TabListener() {
            @Override
            public void onTabSelected(ActionBar.Tab tab, android.support.v4.app.FragmentTransaction ft) {
                System.out.println(tab.getPosition());
                mViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(ActionBar.Tab tab, android.support.v4.app.FragmentTransaction ft) {

            }

            @Override
            public void onTabReselected(ActionBar.Tab tab, android.support.v4.app.FragmentTransaction ft) {

            }
        };
        actionBar.addTab(actionBar.newTab().setText("Reseau").setTabListener(tabListener));
        actionBar.addTab(actionBar.newTab().setText("Map").setTabListener(tabListener));
        actionBar.addTab(actionBar.newTab().setText("Frigo").setTabListener(tabListener));
        actionBar.addTab(actionBar.newTab().setText("Ajouter").setTabListener(tabListener));
        mViewPager.setOnPageChangeListener(
                new ViewPager.SimpleOnPageChangeListener() {
                    @Override
                    public void onPageSelected(int position) {   // When swiping between pages, select the tab.
                        getSupportActionBar().setSelectedNavigationItem(position);
                    }
                });
    }

    @Override
    public void onFragmentInteraction(String msg) {

    }
    public class BalladesFragsPagerAdapter extends FragmentStatePagerAdapter {
        public BalladesFragsPagerAdapter(FragmentManager fm) {
            super(fm);
        }
        @Override
        public android.support.v4.app.Fragment getItem(int i) {
            return Frags[i];}
        @Override
        public int getCount() {
            return Frags.length ;}
        @Override
        public CharSequence getPageTitle(int position){
            return "OBJECT " + (position + 1);}
    }

    public void addBDJsonAliment() {
        AddBaseJSON ad = AddBaseJSON.getInstance(MainActivity.CONTEXT);
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONGetter("http://phpiutaix2.alwaysdata.net/foods-fr.json").execute().get();
        } catch (MalformedURLException | ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }

        if (jsonObject != null)
            try {
                JSONArray jArray = new JSONArray(jsonObject.getString("foods"));;
                for (int i = 0; i < jArray.length(); ++i) {
                    // On récupère un objet JSON du tableau
                    JSONObject obj = new JSONObject(jArray.getString(i));
                    ad.addProduit(obj.getString("food"));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
    }
}
