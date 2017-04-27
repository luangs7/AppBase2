package br.com.luan.clubeprime.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import br.com.luan.clubeprime.R;
import br.com.luan.clubeprime.adapter.SorteioAdapter;
import br.com.luan.clubeprime.model.Evento;


public class Fragment1 extends Fragment {


    Button btnmed, btnpac, btngrp;
    ListView lista;
    SorteioAdapter adapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_1, null);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        List<Evento> eventos = new ArrayList<>();

        eventos.add(new Evento("NENHUM DE NÓS E ROBERTA CAMPOS", "4 DE NOVEMBRO\n" +
                "Tetro Positivo", R.drawable.diogo));

        eventos.add(new Evento("Diogo Nogueira", "12 DE NOVEMBRO\n" +
                "Tetro Positivo",R.drawable.nando));



        lista = (ListView) view.findViewById(R.id.listView2);
        adapter = new SorteioAdapter(getActivity(),eventos,"1");
        lista.setAdapter(adapter);

    }


}
