package com.task.basilischi.funjebret;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

public class MainActivity extends AppCompatActivity {

    LoginButton loginButton;
    CallbackManager callbackManager;
    Button signin, signup;
    EditText email,pass;
    String emailStr, passStr;
    TextView textView;
    Typeface tf;
    DatabaseHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.login_activity);

        helper = new DatabaseHelper(this);
        textView = (TextView)findViewById(R.id.titleApp);
        loginButton = (LoginButton)findViewById(R.id.login_button);
        signin = (Button)findViewById(R.id.signIn);
        signup = (Button)findViewById(R.id.signUp);
        email = (EditText)findViewById(R.id.inputEmail);
        pass = (EditText)findViewById(R.id.inputPassword);

        callbackManager = CallbackManager.Factory.create();
        tf = Typeface.createFromAsset(getAssets(),"RECOGNITION.ttf");
        textView.setTypeface(tf);

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                emailStr = email.getText().toString();
                passStr = pass.getText().toString();

                String password = helper.searchPass(passStr);

                if(passStr.equals(password)){
                    Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                    intent.putExtra("email",emailStr);
                    startActivity(intent);
                }else{
                    Toast item = Toast.makeText(MainActivity.this, "Email or Password is Wrong!!", Toast.LENGTH_SHORT);
                    item.show();
                }
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SignUp.class);
                startActivity(intent);
            }
        });

        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Intent intent = new Intent(MainActivity.this,LoginActivity.class);
                intent.putExtra("loginStatus", "Your id \n"+ loginResult.getAccessToken().getUserId() +
                        "\n"+"Your Token access \n"+loginResult.getAccessToken().getToken());
                startActivity(intent);
            }

            @Override
            public void onCancel() {
//                textView.setText("Login Failed!!");
            }

            @Override
            public void onError(FacebookException error) {

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        callbackManager.onActivityResult(requestCode,resultCode,data);
    }
}
