package com.task.basilischi.funjebret;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUp extends AppCompatActivity {
    EditText name, uname, email, pass, repass;
    String nameStr, unameStr, emailStr, passStr, repassStr;
    Button signup;
    DatabaseHelper helper;
    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);

        name = (EditText)findViewById(R.id.name);
        uname = (EditText)findViewById(R.id.uname);
        email = (EditText)findViewById(R.id.email);
        pass = (EditText)findViewById(R.id.pass);
        repass = (EditText)findViewById(R.id.repass);
        signup = (Button)findViewById(R.id.signup);
        helper = new DatabaseHelper(this);
        auth = FirebaseAuth.getInstance();

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nameStr = name.getText().toString();
                unameStr = uname.getText().toString();
                emailStr = email.getText().toString().trim();
                passStr = pass.getText().toString().trim();
                repassStr = repass.getText().toString();

                if (TextUtils.isEmpty(nameStr)) {
                    name.setError("Name don't Empty!!");
                }else if(TextUtils.isEmpty(unameStr)) {
                    uname.setError("User Name don't Empty!!");
                }else if(TextUtils.isEmpty(emailStr)) {
                    email.setError("Email don't Empty!!");
                }else if(TextUtils.isEmpty(passStr)) {
                    pass.setError("Password don't Empty");
                }else if(passStr.length() < 6) {
                    pass.setError("Password too Shrot!");
                }else if(!passStr.equals(repassStr)){
                    repass.setError("Password Not Same!");
                }else {
                    auth.createUserWithEmailAndPassword(emailStr, passStr)
                            .addOnCompleteListener(SignUp.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (!task.isSuccessful()) {
                                        email.setError("Email Invalid!");
                                    }else{
                                        Toast.makeText(SignUp.this, "Signup Success!!", Toast.LENGTH_SHORT).show();
                                        finish();
                                    }
                                }
                            });
//                    Register register = new Register();
//                    register.setName(nameStr);
//                    register.setUserName(unameStr);
//                    register.setEmail(emailStr);
//                    register.setPassword(passStr);
//
//                    helper.insertContact(register);
                }
            }
        });
    }
}
