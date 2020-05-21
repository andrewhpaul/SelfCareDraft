package com.a_adevelopers.selfcare.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.a_adevelopers.selfcare.Adapter.Adapter;
import com.a_adevelopers.selfcare.Adapter.SelectAdapterNotification;
import com.a_adevelopers.selfcare.MainActivity;
import com.a_adevelopers.selfcare.R;
import com.a_adevelopers.selfcare.db.SqlLite;

public class SelecteNotificationProduct extends AppCompatActivity {
    RecyclerView mRecyclerView;
    SelectAdapterNotification adapter;
    private SqlLite databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_selecte_notification_product );
        mRecyclerView = findViewById(R.id.recycler);
        databaseHelper = new SqlLite(this);
        showRecord();
    }
    private void showRecord() {

        adapter = new SelectAdapterNotification( SelecteNotificationProduct.this,databaseHelper.getAllData());

        mRecyclerView.setAdapter(adapter);
    }
}
