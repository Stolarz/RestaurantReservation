package com.example.tomasz.restaurantreservation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuActivity extends AppCompatActivity {

    private Button btnUserMenu;
    private Button btnListMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        btnListMenu = (Button) findViewById(R.id.btnList);
        btnListMenu.setOnClickListener(listmenu);
        btnUserMenu = (Button) findViewById(R.id.btnUserMenu);
        btnUserMenu.setOnClickListener(usermenu);
    }

        View.OnClickListener listmenu = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, MainActivity.class);
                startActivity(intent);
            }
        };

        View.OnClickListener usermenu = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, DisplayUser.class);
                startActivity(intent);
            }
        };

}
