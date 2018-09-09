package com.example.asus.ar_application;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.ar.core.examples.java.augmentedimage.AugmentedImageActivity;

import java.io.Serializable;
import java.util.ArrayList;

public class Display_information extends AppCompatActivity {

    Button bt ;
    TextView itemnameview, itemdescview;
    String ItemName, str;
    Context ctx = this;
    ArrayList<Box> boxes = new ArrayList<Box>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_information);

        //get the item name from previous activity
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            ItemName = extras.getString("name");
            }
        itemnameview  = (TextView) findViewById(R.id.name);
        itemnameview.setText(ItemName);

        //get item description from DB
        DatabaseOperations DB = new DatabaseOperations(ctx);
        Cursor info = DB.getInformation(DB, ItemName);
        if (info.getCount() > 0){
            info.moveToFirst();
            str = info.getString(0);
            while(!info.isAfterLast()) {
                Box box = new Box(Integer.parseInt(info.getString(1)),Integer.parseInt(info.getString(2)),Integer.parseInt(info.getString(3)));
                boxes.add(box);
                info.moveToNext();
            }
            info.close();
        }
        itemdescview  = (TextView) findViewById(R.id.description);
        itemdescview.setText(str);

        //
        bt = (Button)findViewById(R.id.button);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                locateItem();
            }
        });



    }

    public void locateItem(){
        Intent intent = new Intent(this,AugmentedImageActivity.class);
        intent.putExtra("Data",(Serializable)boxes);
        startActivity(intent);
    }
}
