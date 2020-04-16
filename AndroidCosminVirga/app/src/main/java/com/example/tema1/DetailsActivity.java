package com.example.tema1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class DetailsActivity extends AppCompatActivity {
    private TextView descriptionTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.DETAILS_EXTRA);
        descriptionTextView = findViewById(R.id.detail_text);

        descriptionTextView.setText(message);


    }

    public void onBackButtonClick(View view) {
        finish();
    }
}
