package com.aditya.todo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

/**
 * Created by Aniruddh on 9/29/16.
 */
public class pop extends Activity {

    Button b2;
    EditText editTask;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pop);
        Spinner dropDown= (Spinner) findViewById(R.id.spinner);
        String[] items = new String[]{"High", "Medium", "Low"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropDown.setAdapter(adapter);
        editTask=(EditText) findViewById(R.id.editTask);
        final Intent intent= new Intent(pop.this, MainActivity.class);
        intent.putExtra("Text",editTask.getText().toString());
        //startActivityForResult(intent,0);
        b2=(Button) findViewById(R.id.okButton);

        b2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                //startActivityForResult(intent,0);
                finish();
            }
        });

    }
}
