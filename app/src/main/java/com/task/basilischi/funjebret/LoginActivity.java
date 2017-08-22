package com.task.basilischi.funjebret;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.facebook.login.LoginManager;

public class LoginActivity extends AppCompatActivity {

    TextView textView;
    Button signout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = getIntent();
        String status = intent.getStringExtra("loginStatus");
        textView = (TextView)findViewById(R.id.textView);
        textView.setText(status);
        signout = (Button)findViewById(R.id.signOut);
        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent back = new Intent(LoginActivity.this, MainActivity.class);
                LoginManager.getInstance().logOut();
                finish();
            }
        });
    }
}
