package com.example.budget_planner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

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

        ArrayAdapter<Double> operations = new ArrayAdapter<Double>(this, R.layout.multi_columns_layout);
        ArrayAdapter<String> motives = new ArrayAdapter<String>(this, R.layout.multi_columns_layout);
        ArrayAdapter<String> dates = new ArrayAdapter<String>(this, R.layout.multi_columns_layout);
        db.latestOp(operations);
        db.latestMotives(motives);
        db.latestDates(dates);


        populateList(operations, motives, dates);
        listViewAdapter adapter = new listViewAdapter(this, ops);

        list.setAdapter(adapter);

        TextView currentCount = findViewById(R.id.opCurrentCount);
        currentCount.setText("€ "+ String.valueOf(db.latest()));

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