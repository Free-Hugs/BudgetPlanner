package com.example.budget_planner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class OperationsList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_operations_list);

        Database db = new Database(OperationsList.this);
        ListView list = findViewById(R.id.list);
        ArrayAdapter<Double> operations = new ArrayAdapter<Double>(list.getContext(), R.layout.operation_list);
        db.latestOp(operations);
        list.setAdapter(operations);

        Button returnButton = findViewById(R.id.opReturnButton);
        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(OperationsList.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}