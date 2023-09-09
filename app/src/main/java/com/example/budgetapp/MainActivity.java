package com.example.budgetapp;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private EditText editTextIncome;
    private EditText editTextExpense;
    private Button buttonAddIncome;
    private Button buttonAddExpense;
    private ListView listViewEntries;
    private TextView textViewTotalExpense;
    private TextView textViewTotalSavings;

    private ArrayList<Double> entries;
    private ArrayAdapter<Double> entriesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextIncome = findViewById(R.id.editTextIncome);
        editTextExpense = findViewById(R.id.editTextExpense);
        buttonAddIncome = findViewById(R.id.buttonAddIncome);
        buttonAddExpense = findViewById(R.id.buttonAddExpense);
        listViewEntries = findViewById(R.id.listViewEntries);
        textViewTotalExpense = findViewById(R.id.textViewTotalExpense);
        textViewTotalSavings = findViewById(R.id.textViewTotalSavings);

        entries = new ArrayList<>();
        entriesAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, entries);
        listViewEntries.setAdapter(entriesAdapter);

        buttonAddIncome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addIncome();
            }
        });

        buttonAddExpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addExpense();
            }
        });
    }

    private void addIncome() {
        String incomeStr = editTextIncome.getText().toString();
        if (!incomeStr.isEmpty()) {
            double income = Double.parseDouble(incomeStr);
            entries.add(income);
            entriesAdapter.notifyDataSetChanged();
            updateTotals();
            editTextIncome.getText().clear();
        }
    }

    private void addExpense() {
        String expenseStr = editTextExpense.getText().toString();
        if (!expenseStr.isEmpty()) {
            double expense = Double.parseDouble(expenseStr);
            entries.add(-expense);
            entriesAdapter.notifyDataSetChanged();
            updateTotals();
            editTextExpense.getText().clear();
        }
    }

    private void updateTotals() {
        double totalExpense = 0;
        for (Double entry : entries) {
            if (entry < 0) {
                totalExpense += Math.abs(entry);
            }
        }

        double totalIncome = 0;
        for (Double entry : entries) {
            if (entry > 0) {
                totalIncome += entry;
            }
        }

        double totalSavings = totalIncome - totalExpense;
        textViewTotalExpense.setText("Total Expense: ₹ " + totalExpense);
        textViewTotalSavings.setText("Total Savings: ₹ " + totalSavings);
    }
}


