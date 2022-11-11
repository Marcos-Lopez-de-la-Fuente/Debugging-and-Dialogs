package com.example.dialogs;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // Attributes
    private Button btnDialog;
    private Button goToTest;
    private TextView textViewMain;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        this.btnDialog = this.findViewById(R.id.btnDialog);
        this.goToTest = this.findViewById(R.id.goToTest);
        this.textViewMain = this.findViewById(R.id.textViewMain);


        this.btnDialog.setOnClickListener(view -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);


            builder.setTitle("Hello");

            builder.setMessage("Miassatge Alert");

            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    textViewMain.setText("Has Pitjat CANCEL");
                }
            });

            builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    textViewMain.setText("Has Pitjat OK");
                }
            });


            builder.create().show();
        });


        this.goToTest.setOnClickListener(view ->
                this.startActivity(new Intent(this, TestActivity.class))
        );

    }
}