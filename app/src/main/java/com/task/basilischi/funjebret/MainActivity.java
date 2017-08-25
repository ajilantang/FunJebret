package com.task.basilischi.funjebret;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
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

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.signIn) Button signin;
    @BindView(R.id.login_button) LoginButton loginButton;
    @BindView(R.id.signUp) TextView signup;
    @BindView(R.id.inputEmail) EditText email;
    @BindView(R.id.inputPassword) EditText pass;

//    @BindView(R.id.titleApp) TextView textView;
//    Typeface tf;

    String emailStr, passStr;
    DatabaseHelper helper;
    CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);

        helper = new DatabaseHelper(this);

        callbackManager = CallbackManager.Factory.create();
        //tf = Typeface.createFromAsset(getAssets(),"RECOGNITION.ttf");
        //textView.setTypeface(tf);

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                emailStr = email.getText().toString();
                passStr = pass.getText().toString();

                String password = helper.searchPass(passStr);

                if(passStr.equals(password)){
                    ActivityCompat.finishAffinity(MainActivity.this);
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
//                intent.putExtra("loginStatus", "Your id \n"+ loginResult.getAccessToken().getUserId() +
//                        "\n"+"Your Token access \n"+loginResult.getAccessToken().getToken());
                startActivity(intent);
                ActivityCompat.finishAffinity(MainActivity.this);
            }

            @Override
            public void onCancel() {
                Toast.makeText(MainActivity.this, "Login Canceled!!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(FacebookException error) {
                Toast.makeText(MainActivity.this, "No Network Internet!!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        callbackManager.onActivityResult(requestCode,resultCode,data);
    }
}
