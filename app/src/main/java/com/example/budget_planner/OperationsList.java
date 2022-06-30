package com.example.budget_planner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;

public class OperationsList extends AppCompatActivity {
    private ArrayList<HashMap> ops;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_operations_list);

        Database db = new Database(OperationsList.this);
        ListView list = findViewById(R.id.list);

        ArrayAdapter<Double> operations = new ArrayAdapter<Double>(this, R.layout.operation_list);
        ArrayAdapter<String> motives = new ArrayAdapter<String>(this, R.layout.operation_list);
        ArrayAdapter<String> dates = new ArrayAdapter<String>(this, R.layout.operation_list);
        db.latestOp(operations);
        db.latestMotives(motives);
        db.latestDates(dates);


        populateList(operations, motives, dates);
        listViewAdapter adapter = new listViewAdapter(this, ops);

        list.setAdapter(adapter);


        /*ArrayAdapter<String> display = new ArrayAdapter<String>(list.getContext(), R.layout.operation_list);
        for(int i = 0; i<motives.getCount(); i++){
            display.add(String.valueOf(operations.getItem(i)) + "     " + motives.getItem(i));
        }
        list.setAdapter(display);*/

        Button returnButton = findViewById(R.id.opReturnButton);
        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(OperationsList.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
    private void populateList(ArrayAdapter<Double> operations, ArrayAdapter<String> motives, ArrayAdapter<String> dates) {
        ops = new ArrayList<HashMap>();
        for(int i = 0; i<motives.getCount(); i++){
            HashMap temp = new HashMap();
            temp.put("First", String.valueOf(operations.getItem(i)));
            temp.put("Second", String.valueOf(motives.getItem(i)));
            temp.put("Third", String.valueOf(dates.getItem(i)));
            ops.add(temp);
        }
    };
}