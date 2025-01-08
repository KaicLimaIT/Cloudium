package com.example.cloudium;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void Login(View view){
        Intent intent = new Intent(MainActivity.this, ActivityLogin.class);
        startActivity(intent);
    }

    public void Registro(View view){
        Intent intent = new Intent(MainActivity.this, ActivityRegistro.class);
        startActivity(intent);
    }

    public void Esqueci(View view){
        Intent intent = new Intent(MainActivity.this, ActivityEsqueciASenha.class);
        startActivity(intent);
    }

    public void Principal(View view){
        Intent intent = new Intent(MainActivity.this, MainActivity.class);
        startActivity(intent);
    }
}