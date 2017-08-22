package com.task.basilischi.funjebret;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = getIntent();
        String status = intent.getStringExtra("loginStatus");
        textView = (TextView)findViewById(R.id.textView);
        textView.setText(status);
    }
}
