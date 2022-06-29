package com.example.budget_planner;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class OperationsList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_operations_list);

        Database db = new Database(OperationsList.this);
        ListView list = findViewById(R.id.list);
        db.insertOperation(12.8);
        ArrayAdapter<Double> operations = new ArrayAdapter<Double>(list.getContext(), R.layout.operation_list);
        db.latestOp(operations);
        list.setAdapter(operations);
    }
}