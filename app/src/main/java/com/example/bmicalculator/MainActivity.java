package com.example.bmicalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    String error,category,color;
    Double result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn_calculate_button = (Button) findViewById(R.id.btn_calculate);
        EditText age = (EditText) findViewById(R.id.txt_age);
        EditText height = (EditText) findViewById(R.id.txt_height);
        EditText weight = (EditText) findViewById(R.id.txt_weight);

        btn_calculate_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (age.getText().toString().equals("") || height.getText().toString().equals("") || weight.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "Enter Values", Toast.LENGTH_SHORT).show();
                }

                else
                {
                    Double height_value = Double.parseDouble(height.getText().toString());
                    Double weight_value = Double.parseDouble(weight.getText().toString());

                    if(Integer.parseInt(age.getText().toString()) < 18)
                    {
                        Toast.makeText(getApplicationContext(),"Enter 18+ Age", Toast.LENGTH_SHORT).show();
                    }

                    else
                    {
                        double meter = height_value/100; // convert cm to m

                        double r = weight_value/(meter*meter);
                        double round = Math.round(r);

                        SetCategory(round);




                        String re = String.valueOf(round);

                        Intent sendValues = new Intent(MainActivity.this, SecondActivity.class);
                        sendValues.putExtra("Category", category);
                        sendValues.putExtra("Result", re);
                        sendValues.putExtra("Color", color);

                        startActivity(sendValues);

                    }
                }


            }


            public void SetCategory(Double r){

                 result = r;

                if(result < 16)
                {
                    category = "Severe Thinness";
                    color ="#c00000";
                }
                else if(16 <= result && result < 17)
                {
                    category = "Moderate Thinness";
                    color ="#eb5834";
                }
                else if(17 <= result && result < 18.5)
                {
                    category = "Mild Thinness";
                    color ="#ffc000";
                }
                else if(18.5 <= result && result < 25)
                {
                    category = "Normal";
                    color ="#00b050";
                }
                else if(25 <= result && result < 30)
                {
                    category = "Overweight";
                    color ="#ffd555";
                }
                else if(30 <= result && result < 35)
                {
                    category = "Obese Class I";
                    color ="#ff0000";
                }
                else if(35 <= result && result < 40)
                {
                    category = "Obese Class II";
                    color ="#ff0000";
                }
                else
                {
                    category = "Obese Class III";
                    color ="#c00000";
                }

            }

        });

    }

    public void onBackPressed() {

        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Exit");
        builder.setIcon(R.mipmap.ic_launcher);
        builder.setMessage("Do you want to exit from the app?")
                .setCancelable(false)
                .setPositiveButton("Exit", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        finish();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();

    }
}