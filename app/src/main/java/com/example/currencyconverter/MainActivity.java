package com.example.currencyconverter;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    private EditText fromAmountEditText, toAmountEditText;
    private Spinner fromCurrencySpinner, toCurrencySpinner;
    private TextView resultTextView;

    private static final String[] CURRENCIES = {"USD", "EUR", "GBP", "JPY", "RUB", "CNY"};
    private final double[] exchangeRates = {1.0, 0.85, 0.72, 110.15, 74.56, 6.47};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fromAmountEditText = findViewById(R.id.fromAmountEditText);
        toAmountEditText = findViewById(R.id.toAmountEditText);
        fromCurrencySpinner = findViewById(R.id.fromCurrencySpinner);
        toCurrencySpinner = findViewById(R.id.toCurrencySpinner);
        Button convertButton = findViewById(R.id.convertButton);
        resultTextView = findViewById(R.id.resultTextView);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, CURRENCIES);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        fromCurrencySpinner.setAdapter(adapter);
        toCurrencySpinner.setAdapter(adapter);

        convertButton.setOnClickListener(v -> convertCurrency());
    }

    @SuppressLint("DefaultLocale")
    private void convertCurrency() {
        double fromAmount = Double.parseDouble(fromAmountEditText.getText().toString());
        int fromIndex = fromCurrencySpinner.getSelectedItemPosition();
        int toIndex = toCurrencySpinner.getSelectedItemPosition();

        double result = (fromAmount * exchangeRates[toIndex]) / exchangeRates[fromIndex];
        toAmountEditText.setText(String.format("%.2f", result));
        resultTextView.setText(String.format("%s %.2f", CURRENCIES[toIndex], result));
    }
}
