package br.com.luan.clubeprime;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class SorteioDetalhesActivity extends AppCompatActivity {

    private android.support.v7.widget.Toolbar toolbar;
    private android.widget.ImageView foto;
    private android.widget.ScrollView scrollView3;
    private android.widget.TextView title;
    private android.widget.LinearLayout linearLayout2;
    private android.widget.ImageView imageView8;
    private android.widget.TextView data;
    private android.widget.ImageView imageView9;
    private android.widget.TextView local;
    private android.widget.TextView descricao;
    private android.widget.Button btnSorteio;
    private android.widget.ImageView cart;
    private android.widget.ImageView view;
    private android.widget.ImageView favorite;
    private android.widget.ImageView share;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_sorteio_detalhes);
        this.share = (ImageView) findViewById(R.id.share);
        this.favorite = (ImageView) findViewById(R.id.favorite);
        this.view = (ImageView) findViewById(R.id.view);
        this.cart = (ImageView) findViewById(R.id.cart);
        this.btnSorteio = (Button) findViewById(R.id.btnSorteio);
        this.descricao = (TextView) findViewById(R.id.descricao);
        this.local = (TextView) findViewById(R.id.local);
        this.imageView9 = (ImageView) findViewById(R.id.imageView9);
        this.data = (TextView) findViewById(R.id.data);
        this.imageView8 = (ImageView) findViewById(R.id.imageView8);
        this.linearLayout2 = (LinearLayout) findViewById(R.id.linearLayout2);
        this.title = (TextView) findViewById(R.id.title);
        this.scrollView3 = (ScrollView) findViewById(R.id.scrollView3);
        this.foto = (ImageView) findViewById(R.id.foto);
        this.toolbar = (Toolbar) findViewById(R.id.toolbar);

        Intent it = getIntent();
        String status = it.getStringExtra("status");

        if(status.equalsIgnoreCase("1")){
            btnSorteio.setText("PARTICIPAR");
            btnSorteio.setBackground(getResources().getDrawable(R.drawable.button_sorteio_ativo));
            btnSorteio.setEnabled(true);
        }else if(status.equalsIgnoreCase("2")){
            btnSorteio.setText("DEIXAR DE PARTICIPAR");
            btnSorteio.setBackground(getResources().getDrawable(R.drawable.button_sorteio_ativo));
            btnSorteio.setEnabled(true);
        }else{
            btnSorteio.setText("ENCERRADO");
            btnSorteio.setBackground(getResources().getDrawable(R.drawable.button_sorteio));
            btnSorteio.setEnabled(false);
        }

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();


        if (id == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);

    }
}
