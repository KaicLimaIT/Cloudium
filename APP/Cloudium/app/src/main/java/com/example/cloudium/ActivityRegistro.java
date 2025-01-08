package com.example.cloudium;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

// Biblitecas adicionadas abaixo
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

public class ActivityRegistro extends AppCompatActivity {
    private EditText txtNome, txtCadUser, txtCadEmail, txtCadSenha, txtCadConfirmaSenha;
    String host = "https://sollitew.serv00.net/TCC";
    String url, ret;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        txtCadUser = findViewById(R.id.txtCadUser);
        txtCadEmail = findViewById(R.id.txtCadEmail);
        txtCadSenha = findViewById(R.id.txtCadSenha);
        txtCadConfirmaSenha = findViewById(R.id.txtCadConfirmaSenha);
        TextView lblCadConfirmaSenha = findViewById(R.id.lblCadConfirmaSenha);
        CheckBox cbxTermos = findViewById(R.id.cbxTermos);
        Button btnCadastrar = findViewById(R.id.btnCadastrar);
        btnCadastrar.setEnabled(false);


        String confirmaSenha = txtCadConfirmaSenha.getText().toString();
        boolean Termos = cbxTermos.isActivated();


        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(cbxTermos.isChecked())
                {
                    Cadastro();
                }

                else
                {
                    Toast.makeText(ActivityRegistro.this,
                            "Precisa aceitar os termos!",
                            Toast.LENGTH_LONG).show();
                }
            }
        });

        //Mudanças de texto
        txtCadUser.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String user = txtCadUser.getText().toString();
                VerificarUser(user);
            }
        });//quando alterar o textview user

        txtCadEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String Email = txtCadEmail.getText().toString();
                VerificarEmail(Email);
            }
        });

        txtCadSenha.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String senha = txtCadSenha.getText().toString();

                TextView lblCadSenha = findViewById(R.id.lblCadSenha);

                String senhaRegex = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";
                Pattern padraoSenha = Pattern.compile(senhaRegex);
                Matcher VerificadorSenha = padraoSenha.matcher(senha);

                if (VerificadorSenha.matches()) {
                    lblCadSenha.setText("Senha Valida!");
                } else {
                    lblCadSenha.setText("Senha Invalida!");
                }
            }
        });//Evento de modificação do texto da senha

        txtCadConfirmaSenha.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String ConfirmaSenha = txtCadConfirmaSenha.getText().toString();
                String Senha = txtCadSenha.getText().toString();

                VerificarSenha(Senha, ConfirmaSenha);
            }
        }); //Conferir senhas
    }//Main

    //Outros Metodos
    private boolean VerificarSenha(String senha, String ConfirmaSenha)
    {

        if (ConfirmaSenha.equals(senha))
        {
        }
        else
        {
        }
        return false;
    }
    private boolean RegexEmail(String email)
    {
        Button btnCadastro = findViewById(R.id.btnCadastrar);
        TextView lblCadEmail = (TextView) findViewById(R.id.lblCadEmail);
        boolean verficacao;
        String emailRegex = "^[\\w._%+-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$";

        Pattern padraoEmail = Pattern.compile(emailRegex);
        Matcher VerificadorEmail = padraoEmail.matcher(email);

        if (VerificadorEmail.matches())
        {
            lblCadEmail.setText("Email Valido!");
            verficacao = true;
            btnCadastro.setEnabled(true);
        }
        else
        {
            lblCadEmail.setText("Email invalido!");
            verficacao = false;
        }
        return verficacao;
    }



    //CRUD
    private void Cadastro()
    {
        url = host + "/Cadastro.php";
        Ion.with(ActivityRegistro.this)
                .load(url)
                .setBodyParameter("User", txtCadUser.getText().toString())
                .setBodyParameter("Email", txtCadEmail.getText().toString())
                .setBodyParameter("Senha", txtCadSenha.getText().toString())
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result)
                    {
                        ret = result.get("info").getAsString();

                        if (e != null) {
                            Toast.makeText(ActivityRegistro.this,
                                    "ERRO!",
                                    Toast.LENGTH_LONG).show();
                            return;
                        }
                        if(ret.equals("ok"))
                        {
                            Toast.makeText(ActivityRegistro.this,
                                    "Incluido com sucesso!",
                                    Toast.LENGTH_LONG).show();

                            Intent Login = new Intent(ActivityRegistro.this, ActivityLogin.class);
                            startActivity(Login);
                        }
                        else
                        {
                            Toast.makeText(ActivityRegistro.this,
                                    "ERRO!",
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }


    private boolean VerificarUser(String User) {
        url = host + "/Consulta.php";
        final boolean[] verificacao = new boolean[1];
        Button btnCadastrar = (Button) findViewById(R.id.btnCadastrar);
        btnCadastrar.setEnabled(false); // Desabilita enquanto faz a verificação


        final TextView[] lblRetUser = {(TextView) findViewById(R.id.lblRetUser)};

        Ion.with(ActivityRegistro.this)
                .load(url)
                .setBodyParameter("Funcao", "LocalizarUser")
                .setBodyParameter("User", User)
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {

                        if (e != null) {
                            // Lida com erro (por exemplo, falha de conexão)
                            lblRetUser[0].setText("Erro ao verificar usuário.");
                            btnCadastrar.setEnabled(true); // Reabilita para tentar novamente
                            return;
                        }

                        String ret = result.get("info").getAsString();
                        if (ret.equals("ok"))
                        {
                            lblRetUser[0].setText("Nome de usuário já cadastrado!");
                            btnCadastrar.setEnabled(false); // Não permite cadastrar
                            verificacao[0] = false;
                        }
                        else
                        {
                            lblRetUser[0].setText("Nome de usuário disponível.");
                            btnCadastrar.setEnabled(true); // Permite cadastrar
                            verificacao[0] = true;
                        }

                    }
                });

        return verificacao[0];
    }

    private void VerificarEmail(String Email) {
        url = host + "/Consulta.php";

        Button btnCadastrar = findViewById(R.id.btnCadastrar);
        btnCadastrar.setEnabled(false); // Desabilita enquanto faz a verificação

        TextView lblCadEmail = findViewById(R.id.lblCadEmail);

        Ion.with(ActivityRegistro.this)
                .load(url)
                .setBodyParameter("Funcao", "LocalizarEmail")
                .setBodyParameter("Email", Email)
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {
                        if (e != null) {
                            // Loga a exceção no Logcat
                            Log.e("ErroVerificacaoEmail", "Erro ao fazer requisição", e);

                            lblCadEmail.setText("Erro ao verificar Email.");
                            btnCadastrar.setEnabled(true); // Reabilita para tentar novamente
                            return;
                        }

                        // Loga o conteúdo do campo "info" no Logcat
                        String ret = result.get("info").getAsString();
                        Log.d("InfoLog", "Retorno do servidor: " + ret);

                        if (ret.equals("ok")) {
                            lblCadEmail.setText("Email já cadastrado!");
                            btnCadastrar.setEnabled(false); // Não permite cadastrar
                        } else {
                            lblCadEmail.setText("");
                            RegexEmail(Email); // Permite cadastrar
                        }
                    }
                });

    }
}//Activity