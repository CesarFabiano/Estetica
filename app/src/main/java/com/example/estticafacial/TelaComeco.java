package com.example.estticafacial;

import androidx.appcompat.app.AppCompatActivity;

        import android.content.Intent;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;

public class TelaComeco extends AppCompatActivity {

    private Button btnRegistrar;
    private Button btnAcessar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_inicial);

        getSupportActionBar().hide();
        btnRegistrar = findViewById(R.id.btnRegistrar);
        btnAcessar = findViewById(R.id.btnAcessar);

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(TelaComeco.this, TelaRegistro.class));
                finish();
            }
        });

        btnAcessar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(TelaComeco.this, TelaAcesso.class));
                finish();
            }
        });
    }
}