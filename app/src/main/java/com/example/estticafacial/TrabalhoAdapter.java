package com.example.estticafacial;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.estticafacial.model.Trabalho;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class TrabalhoAdapter extends BaseAdapter {

    Context context;
    ArrayList<Trabalho> trabalhos;

    public TrabalhoAdapter(Context context, ArrayList<Trabalho> trabalhos) {
        this.context = context;
        this.trabalhos = trabalhos;
    }


    @Override
    public int getCount() {
        return trabalhos.size();
    }

    @Override
    public Object getItem(int i) {
        return trabalhos.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.adapter_trabalho, viewGroup, false);

        }
        DecimalFormat df = new DecimalFormat("#,###.##");
        Trabalho trabalho = trabalhos.get(i);

        TextView textNome = view.findViewById(R.id.editNome);
        //TextView textPreco = view.findViewById(R.id.textPreco);
        //TextView textVideo = view.findViewById(R.id.textVideo);
        ImageView imagemTrabalho = view.findViewById(R.id.editImagem);


        textNome.setText(trabalho.nome);
        //textVideo.setText(trabalho.video);
        //textPreco.setText("R$ " + trabalho.preco);
        Picasso.get().load(trabalho.imagem).into(imagemTrabalho);

        return view;
    }
}