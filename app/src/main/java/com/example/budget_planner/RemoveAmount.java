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

        Button removeAmount = findViewById(R.id.amountRemoveButton);
        removeAmount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RemoveAmount.this, MainActivity.class);
                startActivity(intent);
                int amount = db.latest();
                Log.i("Amount remove", String.valueOf(amount));
                int adding = Integer.parseInt(amountToRemove.getText().toString());
                db.insertData(amount - adding);
                Toast.makeText(RemoveAmount.this, "Amount removed !", Toast.LENGTH_SHORT).show();
            }
        });
    }
}