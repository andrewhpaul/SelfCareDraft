package com.a_adevelopers.selfcare.view;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.a_adevelopers.selfcare.R;
import com.a_adevelopers.selfcare.db.SqlLite;

public class CreateGroupActivity extends AppCompatActivity {

    EditText name;
    Spinner day;
    Button addGroup,edit,delete;
    SqlLite database;
    String TAG,ID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_create_group );
        database=new SqlLite( this );
        name=findViewById( R.id.editText );
        day=findViewById( R.id.day );
        addGroup=findViewById( R.id.addGroup );
        edit=findViewById( R.id.edit );
        delete=findViewById( R.id.delete );
        delete.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                database.deleteGroup(ID );
                Intent intent=new Intent( CreateGroupActivity.this,GroupsActivity.class );
                startActivity( intent );
                finish();
            }
        } );
        edit.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String _name=""+name.getText().toString();
                String _day=""+day.getSelectedItem().toString();
                database.updateGroup(ID, _name,_day );
                Intent intent=new Intent( CreateGroupActivity.this,GroupsActivity.class );
                startActivity( intent );
                finish();
            }
        } );
        addGroup.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String _name=""+name.getText().toString();
                String _day=""+day.getSelectedItem().toString();
            long a=  database.insertInfo( _name,_day );

                Intent intent=new Intent( CreateGroupActivity.this,GroupsActivity.class );
                startActivity( intent );
                finish();
            }
        } );
        TAG=getIntent().getStringExtra( "tag" );
        if(TAG.equals( "add" )){
            edit.setVisibility( View.INVISIBLE );
            delete.setVisibility( View.INVISIBLE );
        }else {
            addGroup.setVisibility( View.INVISIBLE );
            ID=getIntent().getStringExtra( "ID" );
            name.setText( getIntent().getStringExtra( "NAME" ) );
            String _day=getIntent().getStringExtra( "DAY" );

            if(_day.equals( "Date Not Set" )){
                day.setSelection( 0 );
            }else if(_day.equals( "Monday" )){
                day.setSelection( 1 );
            }else if(_day.equals( "Tuesday" )){
                day.setSelection( 2 );
            }else if(_day.equals( "Wednesday" )){
                day.setSelection( 3);
            }else if(_day.equals( "Thursday" )){
                day.setSelection( 4 );
            }else if(_day.equals( "Friday" )){
                day.setSelection( 5);
            }else if(_day.equals( "Saturday" )){
                day.setSelection( 6 );
            }else if(_day.equals( "Sunday" )){
                day.setSelection( 7 );
            }

        }


    }
}
