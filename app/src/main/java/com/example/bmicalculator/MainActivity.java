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

public class MainActivity extends AppCompatActivity {

    String error,category;
    Float result;

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

                SetValidation();

                Float height_value = Float.parseFloat(height.getText().toString());
                Float weight_value = Float.parseFloat(weight.getText().toString());

                if(Integer.parseInt(age.getText().toString()) < 12)
                {
                    Toast.makeText(getApplicationContext(),"Enter 18+ Age", Toast.LENGTH_SHORT).show();
                }

                else
                {
                    Float meter = height_value/100;

                    Float result = weight_value/(meter*meter);

                //    SetCategory();

                  Toast.makeText(getApplicationContext(), result.toString(), Toast.LENGTH_SHORT).show();
                }






//
//                Intent activity2Intent = new Intent(getApplicationContext(), SecondActivity.class);
//                startActivity(activity2Intent);
//
//                Intent sendIntent = new Intent();
//                sendIntent.setAction(Intent.ACTION_SEND);
//                sendIntent.putExtra(Intent.EXTRA_SUBJECT,"Message Test");
//                sendIntent.setType("text/plain");
//
//                startActivity(sendIntent);
            }

            public void SetCategory(){

                if(result < 16)
                {
                    category = "Severe Thinness";
                }
                else if(16 <= result || result < 17)
                {
                    category = "Moderate Thinness";
                }
                else if(17 <= result || result < 18.5)
                {
                    category = "Mild Thinness";
                }
                else if(18.5 <= result || result < 25)
                {
                    category = "Normal";
                }
                else if(25 <= result || result < 30)
                {
                    category = "Overweight";
                }
                else if(30 <= result || result < 35)
                {
                    category = "Obese Class I";
                }
                else if(35 <= result || result < 40)
                {
                    category = "Obese Class II";
                }
                else
                {
                    category = "Obese Class III";
                }

                Toast.makeText(getApplicationContext(), category.toString(), Toast.LENGTH_SHORT).show();
            }

            public void SetValidation() {

                if (age.getText().toString().isEmpty()) {
                    error = "Enter Age";
                    if (height.getText().toString().isEmpty()) {
                         if (weight.getText().toString().isEmpty()) {
                            error = "Enter Values";
                        }
                         else
                         {
                             error = "Enter Age and Height";
                         }
                    }
                    else if(weight.getText().toString().isEmpty())
                    {
                        error = "Enter Age and Weight";
                    }
                    else
                    {
                        error = "Age";
                    }
                  }
                else if(height.getText().toString().isEmpty())
                {
                    error = "Enter Height";
                }
                else
                {
                    error = "Enter Weight";
                }

                Toast.makeText(getApplicationContext(), error, Toast.LENGTH_SHORT).show();
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