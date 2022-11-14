package com.example.dialogs;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // Attributes
    private Button btnDialog;
    private Button advancedDialog;
    private TextView textViewMain;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        this.btnDialog = this.findViewById(R.id.btnDialog);
        this.advancedDialog = this.findViewById(R.id.advancedDialog);
        this.textViewMain = this.findViewById(R.id.textViewMain);


        this.btnDialog.setOnClickListener(view -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);


            builder.setTitle("Hello");

            builder.setMessage("Message Alert");

            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    textViewMain.setText("You pressed CANCEL");
                }
            });

            builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    textViewMain.setText("You pressed OK");
                }
            });


            builder.create().show();
        });


        this.advancedDialog.setOnClickListener(view -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);

            builder.setView(R.layout.advanced_dialog);


            builder.setMultiChoiceItems(R.array.arrayTest, null, new DialogInterface.OnMultiChoiceClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                }
            });


            builder.setPositiveButton("Sign in", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int id) {
                }
            });
            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                }
            });


            builder.create().show();
        });
    }
}