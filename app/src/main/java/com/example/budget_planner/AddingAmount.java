package com.example.budget_planner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddingAmount extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adding_amount);

        Database db = new Database(AddingAmount.this);

        EditText amountToAdd = findViewById(R.id.amountInput);

        Button addingAmount = findViewById(R.id.amountAddButton);
        addingAmount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddingAmount.this, MainActivity.class);
                startActivity(intent);
                double amount = db.latest();
                Log.i("Amount add", String.valueOf(amount));
                double adding = Double.parseDouble(amountToAdd.getText().toString());
                db.insertData(amount + adding);
                Toast.makeText(AddingAmount.this, "Amount added !", Toast.LENGTH_SHORT).show();
            }
        });
    }
}