package com.task.taskgo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class DisplayEmpDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_emp_details);

        TextView textView = findViewById(R.id.textview);
        Intent intent = getIntent();
        String str = intent.getStringExtra("json");
        textView.setText(str);

    }
}