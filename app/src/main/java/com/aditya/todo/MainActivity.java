package com.aditya.todo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.view.*;
import android.widget.AdapterView.OnItemClickListener;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import android.widget.*;

import org.apache.commons.io.FileUtils;


public class MainActivity extends AppCompatActivity {
    ArrayList<String> toDoItems=new ArrayList<String>();
    ArrayAdapter<String> atoDoAdapter;
    ListView lvItems;
    EditText etEditText,editTask;
    Button b1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
            populateArrayItems();
            lvItems = (ListView) findViewById(R.id.lvItems);
            etEditText = (EditText) findViewById(R.id.etEdit);
            editTask= (EditText) findViewById(R.id.editTask);
            b1= (Button) findViewById(R.id.btnAddItem);
            atoDoAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,toDoItems);


            lvItems.setAdapter(atoDoAdapter);





/*
        b1.setOnClickListener(new AdapterView.OnClickListener() {
            @Override
            public void onClick(View view) {
            startActivity(new Intent(MainActivity.this,pop.class));


            }


        }); */






            b1.setOnClickListener(new AdapterView.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("test1","This is a hit");
                if(etEditText.getText().toString()!=null) {
                    toDoItems.add(etEditText.getText().toString());
                    atoDoAdapter.notifyDataSetChanged();
                    etEditText.setText("");
                    writeItems();

                }


            }
        });

            lvItems.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                toDoItems.remove(position);
                atoDoAdapter.notifyDataSetChanged();

                writeItems();
                return true;
            }

        });

        lvItems.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            startActivity(new Intent(MainActivity.this, pop.class));

                Intent i=getIntent();
                final String text = i.getStringExtra("Text");
                etEditText.setText(text);
                toDoItems.add(etEditText.getText().toString());
                atoDoAdapter.notifyDataSetChanged();





            }


        });

    }

    public void populateArrayItems() {

            try {
                //lvItems.setAdapter(atoDoAdapter);
                readItems();


            }catch(NullPointerException e) {
                e.printStackTrace();
            }


    }
    private void readItems() {
        File filesDir = getFilesDir();
        File file = new File(filesDir, "toDo.txt");

        try {
            toDoItems = new ArrayList<String>(FileUtils.readLines(file));
        }catch(IOException e){
            e.printStackTrace();
        }

    }

    private void writeItems() {
        File filesDir= getFilesDir();
        File file= new File(filesDir,"toDo.txt");

        try {

           FileUtils.writeLines(file,toDoItems);
        }catch(IOException e){


        }



    }



   /* public void onAddItem(View view){

        try {
            toDoItems.add(etEditText.getText().toString());
            lvItems.setAdapter(atoDoAdapter);
            //atoDoAdapter.add(etEditText.getText().toString());
            atoDoAdapter.notifyDataSetChanged();
            etEditText.setText("");
            writeItems();
        }catch(NullPointerException e){
            e.printStackTrace();
        }
    }
    */
}
