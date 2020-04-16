package com.example.tema1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SettingsActivity extends AppCompatActivity {
    private TextView nameTextView;
    private Button editButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        nameTextView = findViewById(R.id.name_text);
        editButton = findViewById(R.id.edit_button);

        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAlertDialogButtonClicked();
            }
        });
    }

    public void onEditButtonPress(View view) {

    }


    public void showAlertDialogButtonClicked() {

        AlertDialog.Builder builder = new AlertDialog.Builder(SettingsActivity.this);

        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.alertdialog_edit, null);


        builder.setCancelable(false);


        builder.setView(dialogView);


        Button btn_positive = (Button) dialogView.findViewById(R.id.dialog_positive_btn);
        Button btn_negative = (Button) dialogView.findViewById(R.id.dialog_negative_btn);
        final EditText et_name = (EditText) dialogView.findViewById(R.id.et_name);


        final AlertDialog dialog = builder.create();


        btn_positive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Dismiss the alert dialog
                dialog.cancel();
                String name = et_name.getText().toString();
                nameTextView.setText(name);
            }
        });

        btn_negative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.dismiss();
                Toast.makeText(getApplication(),
                        "No button clicked", Toast.LENGTH_SHORT).show();
            }
        });

        dialog.show();
    }
}
