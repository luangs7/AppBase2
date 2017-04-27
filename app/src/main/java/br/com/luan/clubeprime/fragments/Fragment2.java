package br.com.luan.clubeprime.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import br.com.luan.clubeprime.R;
import br.com.luan.clubeprime.adapter.SorteioAdapter;
import br.com.luan.clubeprime.model.Evento;


public class Fragment2 extends Fragment {

    private ListView listView;
    SorteioAdapter adapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true);

        return inflater.inflate(R.layout.fragment_2, null);



    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        List<Evento> eventos = new ArrayList<>();


        eventos.add(new Evento("Diogo Nogueira", "12 DE NOVEMBRO\n" +
                "Tetro Positivo",R.drawable.diogo));


        listView = (ListView) view.findViewById(R.id.listView2);
        adapter = new SorteioAdapter(getActivity(),eventos,"2");
        listView.setAdapter(adapter);



    }



    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        super.onCreateOptionsMenu(menu, inflater);
    }




}
