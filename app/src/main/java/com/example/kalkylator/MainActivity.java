package com.example.kalkylator;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Spinner spinnerServices;
    private EditText editTextHours;
    private TextView textViewResult;

    private final String[] services = {"Консультация", "Составление договора", "Представительство в суде"};
    private final double[] prices = {1000, 5000, 15000}; // Цены за час

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinnerServices = findViewById(R.id.spinnerServices);
        editTextHours = findViewById(R.id.editTextHours);
        Button buttonCalculate = findViewById(R.id.buttonCalculate);
        textViewResult = findViewById(R.id.textViewResult);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, services);

        spinnerServices.setAdapter(adapter);

        buttonCalculate.setOnClickListener(v -> calculateCost());
    }

    @SuppressLint("SetTextI18n")
    private void calculateCost() {
        int selectedPosition = spinnerServices.getSelectedItemPosition();

        String hoursString = editTextHours.getText().toString();

        if (!hoursString.isEmpty()) {
            double hours = Double.parseDouble(hoursString);
            double cost = hours * prices[selectedPosition];
            textViewResult.setText("Стоимость услуги: " + cost + " руб.");
        } else {
            textViewResult.setText("Пожалуйста, введите количество часов.");
        }
    }
}