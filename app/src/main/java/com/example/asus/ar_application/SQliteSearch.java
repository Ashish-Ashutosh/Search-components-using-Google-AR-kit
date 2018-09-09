package com.example.asus.ar_application;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.ar.core.examples.java.augmentedimage.AugmentedImageActivity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.ListIterator;
import java.util.Set;


public class SQliteSearch extends AppCompatActivity {


    private Button dispButton;
    private Button locItemButton;
    Context ctx = this;

    ArrayList<String> allNames = new ArrayList<>();

    AutoCompleteTextView autocomplete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite_search);

        DatabaseOperations DB = new DatabaseOperations(ctx);
        allNames = DB.getAllNames(DB);

        //eliminate redundant
        Set<String> hs = new HashSet<>();
        hs.addAll(allNames);
        allNames.clear();
        allNames.addAll(hs);
        //

        autocomplete = (AutoCompleteTextView) findViewById(R.id.autocomplete);
        autocomplete.setAdapter(new ArrayAdapter<String>(this, R.layout.list_details, allNames));


        dispButton = (Button) findViewById(R.id.disp_info);
        dispButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String compName = autocomplete.getText().toString().toLowerCase();

                //get string to lowercqse
                ArrayList<String> strs = allNames;
                ListIterator<String> iterator = strs.listIterator();
                while (iterator.hasNext())
                {
                    iterator.set(iterator.next().toLowerCase());
                }
                allNames = strs;
                //

                if (allNames.contains(compName)) {
                    displayInformation(compName);
                } else {
                    reload();
                }
            }
        });

        locItemButton = (Button) findViewById(R.id.loc_item);
        locItemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String compName = autocomplete.getText().toString().toLowerCase();
                if (allNames.contains(compName)) {
                    locateItem(compName);
                } else {
                    reload();
                }
            }
        });


    }

    @Override
    public void onResume() {
        super.onResume();
        autocomplete.setText("");
        autocomplete.requestFocus();

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public void reload() {
        Toast.makeText(this,"Entered Name doesn't Exist",Toast.LENGTH_SHORT).show();
        autocomplete.setText("");
        autocomplete.requestFocus();
    }

    public void displayInformation(String compName) {
        Intent intent = new Intent(this, Display_information.class);
        intent.putExtra("name", compName);
        startActivity(intent);
    }

    public void locateItem(String compName){
        Intent intent = new Intent(this,AugmentedImageActivity.class);
        intent.putExtra("Data",(Serializable)itemPos(compName));
        startActivity(intent);
    }

    public ArrayList<Box> itemPos(String ItemName) {
        ArrayList<Box> boxes = new ArrayList<Box>();
        DatabaseOperations DB = new DatabaseOperations(ctx);
        Cursor info = DB.getInformation(DB, ItemName);
        if (info.getCount() > 0) {
            info.moveToFirst();
            while (!info.isAfterLast()) {
                Box box = new Box(Integer.parseInt(info.getString(1)), Integer.parseInt(info.getString(2))-1, Integer.parseInt(info.getString(3))-1);
                boxes.add(box);
                info.moveToNext();
            }
            info.close();
        }
        return boxes;
    }
}