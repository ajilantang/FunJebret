package com.task.basilischi.funjebret;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity {

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
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener firebaseAuthListener;
    private FirebaseAnalytics mFirebaseAnalytics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);

        helper = new DatabaseHelper(this);
        loginButton = (LoginButton)findViewById(R.id.login_button);
        signin = (Button)findViewById(R.id.signIn);
        signup = (TextView) findViewById(R.id.signUp);
        email = (EditText)findViewById(R.id.inputEmail);
        pass = (EditText)findViewById(R.id.inputPassword);
        firebaseAuth = FirebaseAuth.getInstance();
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        callbackManager = CallbackManager.Factory.create();
        //tf = Typeface.createFromAsset(getAssets(),"RECOGNITION.ttf");
        //textView.setTypeface(tf);

        firebaseAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d("signin", "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    // User is signed out
                    Log.d("signout", "onAuthStateChanged:signed_out");
                }
                // ...
            }
        };

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                emailStr = email.getText().toString();
                passStr = pass.getText().toString();

                String password = helper.searchPass(emailStr);

                if(passStr.equals(password)){
                    ActivityCompat.finishAffinity(LoginActivity.this);
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    intent.putExtra("email",emailStr);
                    startActivity(intent);
                }else{
                    Toast item = Toast.makeText(LoginActivity.this, "Email or Password is Wrong!!", Toast.LENGTH_SHORT);
                    item.show();
                }
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, SignUp.class);
                startActivity(intent);
            }
        });

        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
//                intent.putExtra("loginStatus", "Your id \n"+ loginResult.getAccessToken().getUserId() +
//                        "\n"+"Your Token access \n"+loginResult.getAccessToken().getToken());
                handleFacebookAccessToken(loginResult.getAccessToken());
            }

            @Override
            public void onCancel() {
                Toast.makeText(LoginActivity.this, "Login Canceled!!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(FacebookException error) {
                Toast.makeText(LoginActivity.this, "No Network Internet!!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void handleFacebookAccessToken(AccessToken token){
        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(LoginActivity.this, "Auth Success!", Toast.LENGTH_SHORT).show();
                            firebaseAuth.addAuthStateListener(firebaseAuthListener);
                            ActivityCompat.finishAffinity(LoginActivity.this);
                            Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                            startActivity(intent);
                        }else{
                            Toast.makeText(LoginActivity.this, "Auth Failed!", Toast.LENGTH_SHORT).show();
                            firebaseAuth.removeAuthStateListener(firebaseAuthListener);
                        }
                    }
                });
    }

    @Override
    public void onStart() {
        super.onStart();
        firebaseAuth.addAuthStateListener(firebaseAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (firebaseAuthListener != null) {
            firebaseAuth.removeAuthStateListener(firebaseAuthListener);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        callbackManager.onActivityResult(requestCode,resultCode,data);
    }
}
