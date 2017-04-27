package br.com.luan.clubeprime.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import br.com.luan.clubeprime.DetalhesActivity;
import br.com.luan.clubeprime.R;
import br.com.luan.clubeprime.model.Evento;

public class FavoritosAdapter extends BaseAdapter {

    private List<Evento> objects = new ArrayList<>();

    private Context context;
    private LayoutInflater layoutInflater;

    public FavoritosAdapter(Context context, List<Evento> dados) {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        this.objects = dados;
    }

    @Override
    public int getCount() {
        return objects.size();
    }

    @Override
    public Evento getItem(int position) {
        return objects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.adapter_favoritos, null);
            convertView.setTag(new ViewHolder(convertView));
        }
        initializeViews((Evento)getItem(position), (ViewHolder) convertView.getTag());
        return convertView;
    }

    private void initializeViews(Evento object, ViewHolder holder) {

        holder.titulo.setText(object.getTitle());
        holder.local.setText(object.getDescricao());
        holder.imageView4.setImageResource(object.getResource());
        int[] colors = context.getResources().getIntArray(R.array.cards_color);


        holder.layoutColor.setBackgroundColor(colors[new Random().nextInt(colors.length)]);

        holder.layoutColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(context, DetalhesActivity.class);
                it.putExtra("isfav",true);
                context.startActivity(it);
            }
        });

    }

    protected class ViewHolder {
        private RelativeLayout layoutColor;
        private ImageView imageView4;
        private TextView titulo;
        private TextView local;
        private ImageView add;

        public ViewHolder(View view) {
            layoutColor = (RelativeLayout) view.findViewById(R.id.layout_color);
            imageView4 = (ImageView) view.findViewById(R.id.imageView4);
            titulo = (TextView) view.findViewById(R.id.titulo);
            local = (TextView) view.findViewById(R.id.local);
            add = (ImageView) view.findViewById(R.id.add);
        }
    }
}