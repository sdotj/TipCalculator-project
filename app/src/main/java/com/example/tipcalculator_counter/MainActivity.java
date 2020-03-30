package com.example.tipcalculator_counter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class MainActivity extends AppCompatActivity {

    private EditText inputAmount;
    private EditText numPeople;
    private EditText otherAmountText;

    private TextView amountLabel;
    private TextView numPeopleLable;
    private TextView totalWithTip;
    private TextView totalTip;
    private TextView totalPerPerson;

    private Button clearBtn;
    private Button calculateTip;

    private RadioGroup percents;
    private RadioButton selectedTip;
    private RadioButton otherAmount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputAmount = findViewById(R.id.inputAmount);
        numPeople = findViewById(R.id.numPeople);
        otherAmountText = findViewById(R.id.tipOther);

        amountLabel = findViewById(R.id.amountLabel);
        numPeopleLable = findViewById(R.id.numPeopleLabel);

        totalWithTip = findViewById(R.id.totalWithTip);
        totalTip = findViewById(R.id.totalTip);
        totalPerPerson = findViewById(R.id.totalperPerson);

        percents = findViewById(R.id.radios);
        otherAmount = findViewById(R.id.otherTip);

        clearBtn = findViewById(R.id.clearButton);
        calculateTip = findViewById(R.id.calculateButton);

        //clear button event listener
        clearBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                inputAmount.getText().clear();
                numPeople.getText().clear();
                otherAmountText.getText().clear();
                percents.clearCheck();
                totalTip.setText("");
                totalWithTip.setText("");
                totalPerPerson.setText("");
            }
        });

        calculateTip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int radioID = percents.getCheckedRadioButtonId();
                selectedTip = findViewById(radioID);

                String totalBillString = inputAmount.getText().toString();
                Double totalBill = Double.parseDouble(totalBillString);
                Double total;

                String totalPeopleString = numPeople.getText().toString();
                Double totalPeople = Double.parseDouble(totalPeopleString);
                Double numSplit;

                if (selectedTip.getText().equals("Other")) {
                    String tipOtherString = ((EditText) findViewById(R.id.tipOther)).getText().toString();
                    Double tipOther = Double.parseDouble(tipOtherString);

                    String temp = ((EditText) findViewById(R.id.tipOther)).getText().toString();
                    //tip.setText(temp);
                    //total bill plus tip
                    total = Math.round((totalBill + tipOther) * 100.0) / 100.0;
                    totalWithTip.setText(Double.toString(total));

                    numSplit = Math.round((total/totalPeople) * 100.0)/100.0;
                    totalPerPerson.setText(numSplit.toString());
                }
                else if(selectedTip.getText().equals("10%")){
                    Double tipAmount = Math.round((totalBill * .10) * 100.0)/100.0;
                    totalTip.setText(tipAmount.toString());

                    total = Math.round((tipAmount + totalBill) * 100.0)/100.0;
                    totalWithTip.setText(total.toString());

                    numSplit = Math.round((total/totalPeople) * 100.0) / 100.0;
                    totalPerPerson.setText(numSplit.toString());

                }
                else if(selectedTip.getText().equals("15%")){
                    Double tipAmount = Math.round((totalBill * .15) * 100.0)/100.0;
                    totalTip.setText(tipAmount.toString());

                    total = Math.round((tipAmount + totalBill) * 100.0)/100.0;
                    totalWithTip.setText(total.toString());

                    numSplit = Math.round((total/totalPeople) * 100.0) / 100.0;
                    totalPerPerson.setText(numSplit.toString());

                }
                else if(selectedTip.getText().equals("20%")){
                    Double tipAmount = Math.round((totalBill * .20) * 100.0)/100.0;

                    totalTip.setText(tipAmount.toString());

                    total = Math.round((tipAmount + totalBill) * 100.0)/100.0;
                    totalWithTip.setText(total.toString());

                    numSplit = Math.round((total/totalPeople) * 100.0) / 100.0;
                    totalPerPerson.setText(numSplit.toString());

                }
                else if(selectedTip.getText().equals("No Tip")){
                    totalTip.setText("0");

                    totalWithTip.setText(totalBill.toString());

                    numSplit = Math.round((totalBill/totalPeople) * 100.0)/100.0;
                    totalPerPerson.setText(numSplit.toString());
                }
            }
        });

        percents.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                if(checkedId == R.id.otherTip){
                    otherAmountText.setVisibility(View.VISIBLE);
                    calculateTip.setEnabled(false);
                }
                else{
                    otherAmountText.setVisibility(View.INVISIBLE);
                    String totalBill = totalPerPerson.getText().toString();
                    String totalPeople = numPeople.getText().toString();

                    if(!totalBill.isEmpty() && !totalPeople.isEmpty() && percents.getCheckedRadioButtonId() != -1){
                        Double totalBillNum = Double.parseDouble(totalBill);
                        Integer totalPeopleNum = Integer.parseInt(totalPeople);
                        if(totalBillNum > 0 && totalPeopleNum > 0) {
                            calculateTip.setEnabled(true);
                        }
                    }
                }
            }
        });

    }


}
