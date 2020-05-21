package com.a_adevelopers.selfcare;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


import com.a_adevelopers.selfcare.Adapter.Adapter;
import com.a_adevelopers.selfcare.Adapter.GroupAdapter;
import com.a_adevelopers.selfcare.Model.GroupModel;
import com.a_adevelopers.selfcare.Model.Model;
import com.a_adevelopers.selfcare.db.SqlLite;
import com.a_adevelopers.selfcare.view.AddProductActivity;
import com.a_adevelopers.selfcare.view.GroupsActivity;
import com.a_adevelopers.selfcare.view.NotificationActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.infideap.drawerbehavior.AdvanceDrawerLayout;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private AdvanceDrawerLayout drawer;
    FloatingActionButton floatingActionButton;
    RecyclerView mRecyclerView;
    Adapter adapter;
    private ArrayList<Model> arrayList;
    private ArrayList<Model> search=new ArrayList<>(  );

    private SqlLite databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
         init();
        mRecyclerView = findViewById(R.id.recycler);

        databaseHelper = new SqlLite(this);

        showRecord();
         floatingActionButton.setOnClickListener( new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                checkPermission();
             }
         } );

    }

    private void showRecord() {

        adapter = new Adapter(MainActivity.this,
                databaseHelper.getAllData());

        mRecyclerView.setAdapter(adapter);
    }

    private void init() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        floatingActionButton =  findViewById(R.id.floatingActionButton);
        setSupportActionBar(toolbar);
        drawer = (AdvanceDrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        drawer.setViewScale( Gravity.START, 0.9f);
        drawer.setViewElevation(Gravity.START, 20);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        drawer.closeDrawer( GravityCompat.START);
        switch (menuItem.getItemId()) {
            case R.id.notification:
                    startActivity( new Intent( MainActivity.this, NotificationActivity.class ) );
                break;


            case R.id.product:
                startActivity( new Intent( MainActivity.this, GroupsActivity.class ) );
                break;



        }

        return true;
    }

    private void checkPermission() {
        Dexter.withActivity( this )
                .withPermissions( Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.CAMERA,Manifest.permission.WRITE_EXTERNAL_STORAGE )
                .withListener( new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport multiplePermissionsReport) {
                       Intent intent=new Intent( MainActivity.this,AddProductActivity.class );
                       intent.putExtra( "tag","add" );
                       startActivity( intent );
                       // finish();
                    }




                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> list, PermissionToken permissionToken) {

                        permissionToken.continuePermissionRequest();
                    }
                } ).check();







                /*.withListener( new PermissionsListener() {
                    @Override
                    public void onPermissionsGranted(PermissionGrantedResponse permissionGrantedResponse) {

                    }



                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {


                    }
                } ).check();*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.searchview, menu);

        SearchManager searchManager = (SearchManager) getSystemService( Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView)menu.findItem(R.id.appSearchBar).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setQueryHint("Search");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
               search(newText);
                return true;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    private void search(String newText) {
        search.clear();
        //arrayList.clear();
        arrayList=databaseHelper.getAllData();
        for(int i=0;i<arrayList.size();i++){
            Model groupModel=arrayList.get( i );
            if(groupModel.getTag().contains( newText )){
                search.add( groupModel );
            }
        }


        adapter = new Adapter(MainActivity.this,
                search);

        mRecyclerView.setAdapter(adapter);


    }
}
