package com.a_adevelopers.selfcare.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.a_adevelopers.selfcare.Adapter.AdapterNotification;
import com.a_adevelopers.selfcare.R;
import com.a_adevelopers.selfcare.db.SqlLite;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class NotificationActivity extends AppCompatActivity {

    FloatingActionButton floatingActionButton;
    RecyclerView recyclerView;
    SqlLite sql;
    SQLiteDatabase database;
    AdapterNotification adapter;
    ImageView back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_notification );

        sql=new SqlLite( this );

        floatingActionButton=findViewById( R.id.floatingActionButton );
        recyclerView=findViewById( R.id.recycler );
        back=findViewById( R.id.back );
        back.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        } );
        floatingActionButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity( new Intent( NotificationActivity.this,SelecteNotificationProduct.class ) );
                finish();
            }
        } );
        showRecord();

    }
    private void showRecord() {

        adapter = new AdapterNotification( NotificationActivity.this,
                sql.getAllNotification());


        recyclerView.setAdapter(adapter);
    }

}
