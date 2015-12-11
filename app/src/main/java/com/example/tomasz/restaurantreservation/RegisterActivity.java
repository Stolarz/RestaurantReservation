package com.example.tomasz.restaurantreservation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseClassName;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SignUpCallback;


public class RegisterActivity extends AppCompatActivity  {

    ParseUser user = new ParseUser();
    private EditText _login;
    private EditText _password;
    private EditText _email;
    private Button _regButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        _login = (EditText) findViewById(R.id.edtRegLogin);
        _password = (EditText) findViewById(R.id.edtRegPassword);
        _email = (EditText) findViewById(R.id.edtRegEmail);
        _regButton = (Button) findViewById(R.id.btnRegRegister);
        _regButton.setOnClickListener(register);
    }

    private View.OnClickListener register = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            user.setUsername(_login.getText().toString());
            user.setEmail(_email.getText().toString());
            user.setPassword(_password.getText().toString());

            user.signUpInBackground(new SignUpCallback() {
                @Override
                public void done(ParseException e) {
                    if (e == null){
                        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(RegisterActivity.this, "Błąd rejestracji: "+ e, Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
    };
}

