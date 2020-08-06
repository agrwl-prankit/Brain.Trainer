package com.prankit.braintrainer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import androidx.gridlayout.widget.GridLayout;

import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();

        ListView listView = (ListView) findViewById(R.id.listView);
        final ArrayList arrayList = new ArrayList();
        arrayList.add("Addition");
        arrayList.add("Subtraction");
        arrayList.add("Multiplication");
        arrayList.add("Division");

        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,arrayList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (i == 0){
                    Intent intent = new Intent(getApplicationContext(),AdditionActivity.class);
                    startActivity(intent);
                }
                else if (i == 1){
                    Intent intent = new Intent(getApplicationContext(),SubtractionActivity.class);
                    startActivity(intent);
                }
                else if (i == 2){
                    Intent intent = new Intent(getApplicationContext(),MultiplicationActivity.class);
                    startActivity(intent);
                }
                else if (i == 3){
                    Intent intent = new Intent(getApplicationContext(),DivisionActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
}