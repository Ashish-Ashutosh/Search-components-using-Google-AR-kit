package com.example.asus.ar_application;

import android.content.Intent;
import android.database.SQLException;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.io.IOException;


public class MainActivity extends AppCompatActivity {

    private ImageButton imageButton;
    private TextView adminlink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        /////
        DataBaseHelper myDbHelper = new DataBaseHelper(this);

        try {

            myDbHelper.createDataBase();

        } catch (IOException ioe) {

            throw new Error("Unable to create database");

        }

        try {

            myDbHelper.openDataBase();

        }catch(SQLException sqle){

            throw sqle;

        }
        /////

        imageButton = (ImageButton) findViewById(R.id.enter);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openItemList();
            }
        });

        adminlink = (TextView) findViewById(R.id.adminlink);
        adminlink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAddcomponent();
            }
        });
    }

    public void openItemList(){
        Intent intent = new Intent(this,SQliteSearch.class);
        startActivity(intent);
    }

    public void openAddcomponent(){
        Intent intent = new Intent(this,HelpVideo.class);
        startActivity(intent);
    }

}
