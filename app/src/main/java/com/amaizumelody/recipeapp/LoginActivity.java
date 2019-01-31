package com.amaizumelody.recipeapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText emailedt;
    private EditText passwordedt;
    private Button LoginButton;
    String email;
    String password;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        LoginButton = findViewById(R.id.LoginButton);
        emailedt = findViewById(R.id.email);
        passwordedt = findViewById(R.id.password);

        LoginButton.setOnClickListener(this);
    }


    private void loginUser() {


        email = emailedt.getText().toString();
        password = passwordedt.getText().toString();

        if (!dataValidate(email, password)) {
            return;
        }
        if (email.equals("maconzy12@gmail.com") && password.equals("08146500887")){
            Toast.makeText(this, "they match", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }else{
            Toast.makeText(this, "wrong input", Toast.LENGTH_SHORT).show();
        }
        /*if (email == mail && password == pass){
            Toast.makeText(this, "making sense", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
        else{
            Toast.makeText(this, "wrong input", Toast.LENGTH_SHORT).show();
        }*/
        /*if(email == mail && password == pass){
            Toast.makeText(this, "good", Toast.LENGTH_SHORT).show();
        }*/

    }
    private boolean dataValidate(String email, String password) {

        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailedt.setError("Enter a valid email address");
            return false;
        }


        if (password.isEmpty() || password.length() < 2 ) {
            passwordedt.setError("Password not valid");
            return false;
        }


        /*if(email != mail && password != pass ){
            Toast.makeText(this, "Wrong input", Toast.LENGTH_SHORT).show();
            return false;
        }
        */
        return true;
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.LoginButton:
                loginUser();
                break;
        }
    }
}
