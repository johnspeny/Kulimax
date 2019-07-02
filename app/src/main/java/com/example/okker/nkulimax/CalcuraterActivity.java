package com.example.okker.nkulimax;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


public class CalcuraterActivity extends AppCompatActivity {
    //private Spinner dropdown;
    private EditText estAmountEditText;
    private TextView estIncomeTextView;
    private TextView estProfitTextView;
    private TextView estSquareUnits;
    //table info
    private TextView bagsExpenseTextView;
    private TextView seedsExpenseTextView;
    private TextView cottonExpenseTextView;
    private TextView bagsQtyTextView;
    private TextView cottonQtyTextView;
    private TextView seedsQtyTextView;
    private Button runButton;
    private int capital,income, profit;
    //Totals
    private int totalBagsAmount, totalSeedsAmount, totalCottonAmount;
    private double totalBagQty, totalSeedQty, totalCottonQty;
    //Amount per bag
    private int BAG = 100, SEED =2000, COTTON =1500;
    private double bagQty = 1, seedQty = 0.5, cottonQty= 1.5;
    //total per square unit
    private int  bagNo = 9;
    private int BAG_AMOUNT = BAG*bagNo, SEED_AMOUNT = SEED*bagNo, COTTON_AMOUNT = COTTON*bagNo;
    private double BAG_QTY = bagQty*bagNo , SEED_QTY = seedQty*bagNo, COTTON_QTY = cottonQty*bagNo;
    //SquareUnits
    private int sqIncome, sqNo, sqExpense = BAG_AMOUNT+SEED_AMOUNT+COTTON_AMOUNT;
    //income
    int bagAmount = 10000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calcurater);
        /*/get the spinner from the xml.
        dropdown = findViewById(R.id.spinner1);
        //create a list of items for the spinner.
        String[] items = new String[]{"m", "inch", "cm"};
        //create an adapter to describe how the items are displayed, adapters are used in several places in android.
        //There are multiple variations of this, but this is the basic variant.
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        //set the spinners adapter to the previously created one.
        dropdown.setAdapter(adapter);
        */

        estAmountEditText = findViewById(R.id.est_amount);
        estIncomeTextView = findViewById(R.id.est_income_value);
        estProfitTextView = findViewById(R.id.est_profit_value);
        estSquareUnits = findViewById(R.id.calc_total_yeilds);
        bagsExpenseTextView = findViewById(R.id.bags_amount);
        cottonExpenseTextView = findViewById(R.id.cotton_amount);
        seedsExpenseTextView = findViewById(R.id.seeds_amount);
        bagsQtyTextView = findViewById(R.id.bags_qty);
        seedsQtyTextView = findViewById(R.id.seeds_qty);
        cottonQtyTextView = findViewById(R.id.cotton_qty);
        runButton = findViewById(R.id.cal_run_button);

        runButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String value = estAmountEditText.getText().toString();
                if(!TextUtils.isEmpty(value)){
                    capital = Integer.parseInt(value);
                    if (capital>sqExpense){
                        sqNo = getSquareUnits(capital);
                        String sqtext ="Total Yields For: "+String.valueOf(sqNo)+" SqUnits";

                        income = getincome(sqNo);
                        profit = income-capital;
                        estIncomeTextView.setText(String.valueOf(income));
                        estProfitTextView.setText(String.valueOf(profit));
                        initialisingtotals(sqNo);
                        bagsExpenseTextView.setText(String.valueOf(totalBagsAmount));
                        seedsExpenseTextView.setText(String.valueOf(totalSeedsAmount));
                        cottonExpenseTextView.setText(String.valueOf(totalCottonAmount));
                        bagsQtyTextView.setText(String.valueOf(totalBagQty));
                        seedsQtyTextView.setText(String.valueOf(totalSeedQty));
                        cottonQtyTextView.setText(String.valueOf(totalCottonQty));
                        estSquareUnits.setText(sqtext);
                    }else {
                        Toast.makeText(CalcuraterActivity.this, "Value Must be greater than "+sqExpense, Toast.LENGTH_SHORT).show();

                    }

                }else {
                    Toast.makeText(CalcuraterActivity.this, "Enter Amount Please", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        bagsExpenseTextView.setText(String.valueOf(BAG_AMOUNT));
        seedsExpenseTextView.setText(String.valueOf(SEED_AMOUNT));
        cottonExpenseTextView.setText(String.valueOf(COTTON_AMOUNT));
        bagsQtyTextView.setText(String.valueOf(BAG_QTY));
        seedsQtyTextView.setText((String.valueOf(SEED_QTY)));
        cottonQtyTextView.setText(String.valueOf(COTTON_QTY));
    }

    public void initialisingtotals(int sqNo){
        totalBagsAmount = sqNo*BAG_AMOUNT;
        totalSeedsAmount = sqNo*SEED_AMOUNT;
        totalCottonAmount = sqNo*COTTON_AMOUNT;
        totalCottonQty = sqNo*COTTON_QTY;
        totalBagQty = sqNo* BAG_QTY;
        totalSeedQty = sqNo*SEED_QTY;

    }
    public int getSquareUnits(int capital){
        sqNo = capital/sqExpense;
        return sqNo;
    }

    public int getsqunitIncome(){
        //ESTIMATED INCOME FROM A BAG
        int sq = bagAmount*bagNo;
        return sq;
    }

    public  int getincome(int sqNo){
        int income;
        income = getsqunitIncome();
        sqIncome = income * sqNo;
        return sqIncome;
    }
}
