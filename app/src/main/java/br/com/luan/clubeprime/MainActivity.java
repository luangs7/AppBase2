package br.com.luan.clubeprime;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import br.com.luan.clubeprime.adapter.EventoAdapter;
import br.com.luan.clubeprime.model.Evento;

public class MainActivity extends BaseActivity {

    private android.support.v7.widget.Toolbar toolbar;
    List<Evento> eventos = new ArrayList<>();
    EventoAdapter adapter;
    private android.widget.ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.listView = (ListView) findViewById(R.id.listView);

        this.toolbar = (Toolbar) findViewById(R.id.toolbar);
        if(toolbar != null) {
            toolbar.setTitleTextColor(getResources().getColor(R.color.white));
            toolbar.setTitle("");
            toolbar.setNavigationIcon(R.drawable.menu);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    drawerLayout.openDrawer(Gravity.LEFT);
                }
            });

            setSupportActionBar(toolbar);
        }

        eventos.add(new Evento("NENHUM DE NÓS E ROBERTA CAMPOS", "4 DE NOVEMBRO\n" +
                "Tetro Positivo", R.drawable.diogo));

        eventos.add(new Evento("Diogo Nogueira", "12 DE NOVEMBRO\n" +
                "Tetro Positivo",R.drawable.nando));

        eventos.add(new Evento("Diogo Nogueira", "12 DE NOVEMBRO\n" +
                "Tetro Positivo",R.drawable.city));

        eventos.add(new Evento("Diogo Nogueira", "12 DE NOVEMBRO\n" +
                "Tetro Positivo",R.drawable.nando));

        eventos.add(new Evento("Diogo Nogueira", "12 DE NOVEMBRO\n" +
                "Tetro Positivo",R.drawable.diogo));

        adapter = new EventoAdapter(this,eventos);
        listView.setAdapter(adapter);

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (super.onCreateOptionsMenu(menu)) {
            getMenuInflater().inflate(R.menu.menu_eventos, menu);
            return true;
        } else {
            return false;
        }
    }
}
