package com.example.budget_planner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Database db = new Database(MainActivity.this);
        double wallet = db.latest();
        Log.i("Wallet state", String.valueOf(wallet));

        TextView currentWallet = findViewById(R.id.currentCount);
        currentWallet.setText(String.valueOf(db.latest()) + " €");

        /**
         * Add Button redirection
         */
        Button add = findViewById(R.id.addButton);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddingAmount.class);
                startActivity(intent);
            }
        });

        /**
         * Remove Button redirection
         */
        Button remove = findViewById(R.id.minusButton);
        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, RemoveAmount.class);
                startActivity(intent);
            }
        });

        /**
         * Date update
         */
        TextView dateDisplay = findViewById(R.id.date);
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String date = dateFormat.format(calendar.getTime());
        dateDisplay.setText(date);
    }
}