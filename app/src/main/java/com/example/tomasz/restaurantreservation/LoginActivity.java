package com.example.tomasz.restaurantreservation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseClassName;
import com.parse.ParseException;
import com.parse.ParseUser;


public class LoginActivity extends AppCompatActivity {
    private Button _btnRegistartion;
    private Button _btnLogin;
    private EditText _edtLogin;
    private EditText _edtPassword;

    ParseUser currentUser = ParseUser.getCurrentUser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if(currentUser !=null)
        {
            Intent intent = new Intent(LoginActivity.this, MenuActivity.class);
            startActivity(intent);
        }
        else{

        _btnRegistartion = (Button) findViewById(R.id.btnRegister);
        _btnRegistartion.setOnClickListener(register);
        _btnLogin = (Button) findViewById(R.id.btnLogin);
        _btnLogin.setOnClickListener(login);

        _edtLogin = (EditText) findViewById(R.id.edtName);
        _edtPassword = (EditText) findViewById(R.id.edtPassword);
        }
    }

    private View.OnClickListener register = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(intent);
        }
    };

    private View.OnClickListener login = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            ParseUser.logInInBackground(_edtLogin.getText().toString(), _edtPassword.getText().toString(), new LogInCallback() {
                @Override
                public void done(ParseUser parseUser, ParseException e) {
                    if (parseUser != null){
                        Intent intent = new Intent(LoginActivity.this, MenuActivity.class);
                        startActivity(intent);
                    }
                }
            });
        }
    };

}
