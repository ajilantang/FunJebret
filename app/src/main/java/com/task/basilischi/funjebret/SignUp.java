package com.task.basilischi.funjebret;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignUp extends AppCompatActivity {
    EditText name, uname, email, pass, repass;
    String nameStr, unameStr, emailStr, passStr, repassStr;
    Button signup;
    DatabaseHelper helper;

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

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nameStr = name.getText().toString();
                unameStr = uname.getText().toString();
                emailStr = email.getText().toString();
                passStr = pass.getText().toString();
                repassStr = repass.getText().toString();
                if(!passStr.equals(repassStr)){
                    Toast passwr = Toast.makeText(SignUp.this, "Password Not Same!!", Toast.LENGTH_SHORT);
                    passwr.show();
                }else{
                    Register register = new Register();
                    register.setName(nameStr);
                    register.setUserName(unameStr);
                    register.setEmail(emailStr);
                    register.setPassword(passStr);

                    helper.insertContact(register);

                    Toast item = Toast.makeText(SignUp.this, "Success!!", Toast.LENGTH_SHORT);
                    item.show();
                    finish();
                }

            }
        });
    }
}
