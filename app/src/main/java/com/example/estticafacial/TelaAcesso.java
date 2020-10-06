package com.example.estticafacial;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class TelaAcesso extends AppCompatActivity {

    private EditText editEmail;
    private EditText editSenha;
    private Button btnAcessar;
    private Button btnVoltar;

    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_acesso);

        getSupportActionBar().hide();
        editEmail = findViewById(R.id.editEmail);
        editSenha = findViewById(R.id.editSenha);
        btnAcessar = findViewById(R.id.btnAcessar);
        btnVoltar = findViewById(R.id.btnVoltar);

        auth = FirebaseAuth.getInstance();

        btnAcessar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = editEmail.getText().toString();
                String senha = editSenha.getText().toString();

                if(TextUtils.isEmpty(email) || TextUtils.isEmpty(senha)) {
                    Toast.makeText(TelaAcesso.this, "Preencha todos os campos!!", Toast.LENGTH_SHORT).show();
                } else if(senha.length() < 6){
                    Toast.makeText(TelaAcesso.this, "Campo senha deve conter 6 dígitos ou mais!!", Toast.LENGTH_SHORT).show();
                } else {
                    loginUser(email, senha);
                }
            }
        });
        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(TelaAcesso.this, TelaComeco.class));
                finish();
            }
        });
    }
    private void loginUser(String email, String senha) {

        auth.signInWithEmailAndPassword(email, senha).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                Toast.makeText(TelaAcesso.this, "Login feito com Sucesso!!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(TelaAcesso.this, TelaInicial.class));
                finish();
            }
        });
    }
}