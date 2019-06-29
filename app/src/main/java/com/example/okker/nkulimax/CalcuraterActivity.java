package com.example.okker.nkulimax;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;


public class CalcuraterActivity extends AppCompatActivity {
    private Spinner dropdown;
    private EditText estAmountEditText;
    private TextView estIncomeTextView;
    private TextView estProfitTextView;
    private int capital;
    private  int sqIncome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calcurater);
        //get the spinner from the xml.
        dropdown = findViewById(R.id.spinner1);
        //create a list of items for the spinner.
        String[] items = new String[]{"m", "inch", "cm"};
        //create an adapter to describe how the items are displayed, adapters are used in several places in android.
        //There are multiple variations of this, but this is the basic variant.
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        //set the spinners adapter to the previously created one.
        dropdown.setAdapter(adapter);

        estAmountEditText = findViewById(R.id.est_amount);
        estIncomeTextView = findViewById(R.id.est_income_value);
        estProfitTextView = findViewById(R.id.est_profit_value);


    }
    public int getSquareUnits(int capital){
        int sq, sqNo;
        int seeds = 1500, cotton=2000, bag = 100;
        int bagNo = 9;
        sq = bagNo*(seeds+cotton+bag);
        sqNo = capital/sq;
        return sqNo;
    }

    public  int getincome(int sqNo){
        int income;
        sqNo = getSquareUnits(this.capital);
        income = this.sqIncome * sqNo;
        return income;
    }
}
