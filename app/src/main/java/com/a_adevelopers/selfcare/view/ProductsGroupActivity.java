package com.a_adevelopers.selfcare.view;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.a_adevelopers.selfcare.Model.GroupModel;
import com.a_adevelopers.selfcare.R;
import com.a_adevelopers.selfcare.db.SqlLite;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ProductsGroupActivity extends AppCompatActivity {

    ListView list;
    FloatingActionButton add;
    String id;
    SqlLite database;
    ArrayList<String> arrayList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_products_group );
        database=new SqlLite( this );
        id=getIntent().getStringExtra( "ID" );
        list=findViewById( R.id.list );
        add=findViewById( R.id.add );
        add.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogBox( id );
            }
        } );
        getAllProducts(Integer.parseInt( id ));
    }

    private void showDialogBox(final String id) {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature( Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog);

        final EditText text =  dialog.findViewById(R.id.editText2);
        Button dialogButton = (Button) dialog.findViewById(R.id.add);
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               insertInfo( text.getText().toString(),id );
               dialog.dismiss();
            }
        });

        dialog.show();
    }

    public void getAllProducts(int id) {



        // query for select info
        String selectQuery = "SELECT * FROM p_groups WHERE g_id="+id;

        SQLiteDatabase db = database.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToNext()) {
            do {
               String name=cursor.getString(cursor.getColumnIndex("name"));
                arrayList.add(name);
            }while (cursor.moveToNext());
        }

        db.close();
       setListview(arrayList);
    }

    private void setListview(ArrayList<String> arrayList) {
        ArrayAdapter<String> itemsAdapter =
                new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrayList );
        list.setAdapter( itemsAdapter );

    }


    public long insertInfo(String name, String id ) {

        SQLiteDatabase db = database.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("g_id", id);

        long iid = db.insert("p_groups", null, values);
        db.close();
        getAllProducts( Integer.parseInt( id ) );
        return iid;
    }
}
