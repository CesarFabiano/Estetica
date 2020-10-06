package com.example.estticafacial;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.estticafacial.model.Trabalho;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.net.URLEncoder;

public class CadastrarTrabalho extends AppCompatActivity {

    TextView editNome;
    TextView editPreco;
    TextView editVideo;
    ImageView editImagem;
    Button btnVoltar;
    Button btnMarcar;
    FirebaseDatabase database;
    DatabaseReference referenceTrabalho;
    String key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_trabalho);

        AlertDialog alerta = new AlertDialog.Builder(CadastrarTrabalho.this)
                .setTitle("Aviso da Ju")
                .setMessage("Para Marcar um horário é preciso me adicionar na sua lista de contatos! 5551982416802")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                })
                .create();
        alerta.show();
        editNome = findViewById(R.id.editNome);
        editPreco = findViewById(R.id.editPreco);
        editVideo = findViewById(R.id.editVideos);
        editImagem = findViewById(R.id.editImagem);
        btnVoltar = findViewById(R.id.btnVoltar);
        btnMarcar = findViewById(R.id.btnMarcar);

        database = FirebaseDatabase.getInstance();
        referenceTrabalho = database.getReference("trabalhos");

        Bundle b = getIntent().getExtras();
        key = b.getString("key");
        //getSupportActionBar().setTitle();
        referenceTrabalho.child(key).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Trabalho trabalho = snapshot.getValue(Trabalho.class);
                editNome.setText(trabalho.nome);
                editVideo.setText("     " + trabalho.video);
                editPreco.setText(String.valueOf("Valor: R$ " + trabalho.preco).replace(".", ","));
                Picasso.get().load(trabalho.imagem).into(editImagem);
                getSupportActionBar().setTitle(trabalho.nome);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(CadastrarTrabalho.this, "Erro ao consultar dados!", Toast.LENGTH_SHORT).show();
            }
        });
        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CadastrarTrabalho.this, TelaInicial.class));
            }
        });

        btnMarcar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                referenceTrabalho.child(key).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        Trabalho trabalho = snapshot.getValue(Trabalho.class);
                        String TelTeste = "5551982416802";
                        String textTeste = "Olá Juliana, gostaria de marcar um horário para " + trabalho.nome;
                        PackageManager pm = getPackageManager();
                        Intent i = new Intent(Intent.ACTION_VIEW);

                        try {
                            String url = "https://api.whatsapp.com/send?phone="+ TelTeste +"&text=" + URLEncoder.encode(textTeste, "UTF-8");
                            i.setPackage("com.whatsapp");
                            i.setData(Uri.parse(url));
                            if (i.resolveActivity(pm) != null) {
                                startActivity(i);
                            }
                        } catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(CadastrarTrabalho.this, "Erro ao consultar dados!", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}