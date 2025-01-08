package com.example.cloudium;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.net.HttpURLConnection;

public class ActivityLogin extends AppCompatActivity
{
    EditText txtLogUser, txtLogSenha;
    Button btnLogin;
    TextView btnRegistro, btnEsqueciASenha;

    String host = "https://sollitew.serv00.net/TCC";
    String url, ret;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnRegistro = findViewById(R.id.btnLogRegistro);
        btnLogin = findViewById(R.id.btnLogin);
        txtLogSenha = findViewById(R.id.txtLogSenha);
        txtLogUser = findViewById(R.id.txtLogUser);
        btnEsqueciASenha = findViewById(R.id.btnEsqueceuSenha);

        btnRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Registro = new Intent(ActivityLogin.this, ActivityRegistro.class);
                startActivity(Registro);
            }
        });//btnCadastro

        btnLogin.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String login = txtLogUser.getText().toString();
                String senha = txtLogSenha.getText().toString();

                url = host + "/Consulta.php";

                Ion.with(ActivityLogin.this)
                        .load(url)
                        .setBodyParameter("Funcao", "Login")
                        .setBodyParameter("Login", login)
                        .setBodyParameter("Senha" , senha)
                        .asJsonObject()
                        .setCallback(new FutureCallback<JsonObject>() {
                            @Override
                            public void onCompleted(Exception e, JsonObject result)
                            {
                                if (e != null)
                                {
                                    Toast.makeText(ActivityLogin.this,
                                            "Erro ao fazer login",
                                            Toast.LENGTH_SHORT).show();
                                }

                                ret = result.get("info").getAsString();
                                if (ret.equals("ok"))
                                {
                                    Toast.makeText(ActivityLogin.this,
                                            "Logado com sucesso!",
                                            Toast.LENGTH_SHORT).show();

                                    Intent Menu = new Intent(ActivityLogin.this, Teste.class);
                                    startActivity(Menu);
                                }
                                else
                                {
                                    Toast.makeText(ActivityLogin.this,
                                            "Usuario ou login incorretos!",
                                            Toast.LENGTH_SHORT).show();

                                    txtLogUser.setText("");
                                    txtLogSenha.setText("");
                                }
                            }
                        });
            }
        });//btnLogin

        btnEsqueciASenha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent EsqueciASenha = new Intent(ActivityLogin.this, ActivityEsqueciASenha.class);
                startActivity(EsqueciASenha);
            }
        });//btnEsqueciASenha
    }
}