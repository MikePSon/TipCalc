package com.example.mikepatterson.tipcalc;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.*;
import android.view.inputmethod.*;
import android.widget.*;
import android.widget.TextView.*;
import java.util.ArrayList;
import android.util.Log;

import java.text.DecimalFormat;

public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        billChanged();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void tipAmt(Double percent){
        //Get Bill Total
        EditText billTotalField = (EditText)findViewById(R.id.billAmt);
        Double billTotal = Double.valueOf(billTotalField.getText().toString());
//        Double billTotal = Double.billTotalFl;
//        Double billTotal = 10.00;

        //Get Tip Amt
        Double tipFraction = percent;

        //Do The Math
        Double tipAmt = tipFraction * billTotal;
        String tipAmtFinal = String.format("%.02f", tipAmt);
        String TAG = "TipAmt: ";
        Log.v(TAG, tipAmtFinal);

        //Set the Value
        TextView tipField = (TextView)findViewById(R.id.tipAmtField);
        tipField.setText(tipAmtFinal);

        //Total Bill Amt (with Tip)
        Double mealTotal = billTotal + tipAmt;
        String mealTotalFinal = String.format("%.02f", mealTotal);
        TextView totalField = (TextView)findViewById(R.id.mealTotalField);
        totalField.setText(mealTotalFinal);
    }

    public void billChanged() {
        EditText billField = (EditText) findViewById(R.id.billAmt);
        billField.setOnEditorActionListener(new OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    //Get Selected Radio Button
                    RadioButton ten = (RadioButton)findViewById(R.id.select10);
                    RadioButton fifteen = (RadioButton)findViewById(R.id.select15);
                    RadioButton eighteen = (RadioButton)findViewById(R.id.select18);
                    RadioButton twenty = (RadioButton)findViewById(R.id.select20);
                    RadioButton twentyFive = (RadioButton)findViewById(R.id.select25);

                    if(ten.isChecked()){
                        tipAmt(.10);
                    } else if(fifteen.isChecked()) {
                        tipAmt(.15);
                    } else if(eighteen.isChecked()) {
                        tipAmt(.18);
                    } else if(twenty.isChecked()) {
                        tipAmt(.20);
                    } else if(twentyFive.isChecked()) {
                        tipAmt(.25);
                    }
                    return false;
                }
                return false;
            }
        });
    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.select10:
                if (checked)
                    tipAmt(.10);
                    break;
            case R.id.select15:
                if (checked)
                    tipAmt(.15);
                    break;
            case R.id.select18:
                if (checked)
                    tipAmt(.18);
                    break;
            case R.id.select20:
                if (checked)
                    tipAmt(.20);
                    break;
            case R.id.select25:
                if (checked)
                    tipAmt(.25);
                    break;
        }
    }

    static final String entered = "Entered";
    static final String finalTip = "Tip";
    static final String finalTotal = "Final";

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        TextView billAmtField = (TextView) findViewById(R.id.billAmt);
        String billAmt = billAmtField.getText().toString();
        savedInstanceState.putString(entered, billAmt);

        TextView tipAmtField = (TextView) findViewById(R.id.tipAmtField);
        String tipAmt = tipAmtField.getText().toString();
        savedInstanceState.putString(finalTip, tipAmt);

        TextView mealTotalField = (TextView) findViewById(R.id.mealTotalField);
        String mealTotal = mealTotalField.getText().toString();
        savedInstanceState.putString(finalTotal, mealTotal);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState); // Always call the superclass first

        // Check whether we're recreating a previously destroyed instance
        if (savedInstanceState != null) {
            // Restore value of members from saved state
            TextView billAmtField = (TextView) findViewById(R.id.billAmt);
            String enteredVal = savedInstanceState.getString(entered);
            billAmtField.setText(enteredVal);


            TextView tipAmtField = (TextView) findViewById(R.id.tipAmtField);
            String tip = savedInstanceState.getString(finalTip);
            tipAmtField.setText(tip);

            TextView mealTotalField = (TextView) findViewById(R.id.mealTotalField);
            String total = savedInstanceState.getString(finalTotal);
            mealTotalField.setText(total);
        }
    }
}
