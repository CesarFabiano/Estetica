package com.example.estticafacial;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.estticafacial.model.Trabalho;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class TelaInicial extends AppCompatActivity {

    ListView listTrabalho;
    ArrayList<Trabalho> trabalhos = new ArrayList<>();
    TrabalhoAdapter adapterTrabalho;
    FirebaseDatabase database;
    DatabaseReference referenceTrabalho;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_comeco);

        getSupportActionBar().setTitle("Procedimentos");
        listTrabalho = findViewById(R.id.listTrabalho);
        adapterTrabalho = new TrabalhoAdapter(TelaInicial.this, trabalhos);
        listTrabalho.setAdapter(adapterTrabalho);
        database = FirebaseDatabase.getInstance();
        referenceTrabalho = database.getReference("trabalhos");
        referenceTrabalho.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                trabalhos.clear();
                for(DataSnapshot dataTrabalho : snapshot.getChildren()){
                    Trabalho trabalho = dataTrabalho.getValue(Trabalho.class);
                    trabalho.setKey(dataTrabalho.getKey());
                    trabalhos.add(trabalho);
                }
                adapterTrabalho.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(TelaInicial.this, "Não foi possivel trazer informações do banco.", Toast.LENGTH_SHORT).show();
            }
        });
        listTrabalho.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Trabalho trabalhoClicado = trabalhos.get(i);
                Intent intentCadastrar = new Intent(TelaInicial.this, CadastrarTrabalho.class);
                Bundle b = new Bundle();
                b.putString("key", trabalhoClicado.getKey());
                intentCadastrar.putExtras(b);
                startActivity(intentCadastrar);
            }
        });
    }
}

