package com.example.budget_planner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RemoveAmount extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove_amount);

        Database db = new Database(RemoveAmount.this);
        EditText amountToRemove = findViewById(R.id.amountRemoveInput);
        EditText removeMotive = findViewById(R.id.motifRemoveInput);

        Button removeAmount = findViewById(R.id.amountRemoveButton);
        removeAmount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RemoveAmount.this, MainActivity.class);
                startActivity(intent);
                double amount = db.latest();
                Log.i("Amount remove", String.valueOf(amount));
                double remove = Double.parseDouble(amountToRemove.getText().toString());
                String motive = removeMotive.getText().toString();
                Log.i("Amount to remove", String.valueOf(remove));
                db.insertData(amount - remove);
                db.insertOperation(-remove, motive);
                Toast.makeText(RemoveAmount.this, "Amount removed !", Toast.LENGTH_SHORT).show();
            }
        });

        Button removeReturn = findViewById(R.id.removeReturnButton);
        removeReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RemoveAmount.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}