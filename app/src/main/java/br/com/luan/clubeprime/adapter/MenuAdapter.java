package br.com.luan.clubeprime.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import br.com.luan.clubeprime.R;
import br.com.luan.clubeprime.model.LeftMenu;


/**
 * Created by DevMaker on 17/08/2015.
 */
public class MenuAdapter extends BaseAdapter {

    private Context mContext;
    public ArrayList<LeftMenu> newList = new ArrayList<LeftMenu>();
    private LayoutInflater mLayoutInflater;


    public MenuAdapter(Context c, ArrayList<LeftMenu> l) {
        mContext = c;
        mLayoutInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        newList.addAll(l);
    }


    @Override
    public int getCount() {
        return  newList.size();
    }

    @Override
    public LeftMenu getItem(int position) {
        return newList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        convertView = mLayoutInflater.inflate(R.layout.item_menu, parent, false);

        LinearLayout conteiner = (LinearLayout) convertView.findViewById(R.id.conteiner);
        conteiner.setBackgroundColor(mContext.getResources().getColor(getItem(position).getBackgoundColor()));

        ImageView imageView = (ImageView) convertView.findViewById(R.id.icon);
        TextView textView = (TextView) convertView.findViewById(R.id.name);

        textView.setText(getItem(position).getName());
        //imageView.setImageResource(getItem(position).getIcon_active());
        if(getItem(position).isActive()){
//            textView.setTextColor(convertView.getResources().getColor(R.color.active));
            (convertView.findViewById(R.id.line)).setBackgroundColor(getItem(position).getBackgoundColor());
            imageView.setImageResource(getItem(position).getIcon_active());
        }else{
//            textView.setTextColor(convertView.getResources().getColor(R.color.white));
            (convertView.findViewById(R.id.line)).setBackgroundColor(getItem(position).getBackgoundColor());
            imageView.setImageResource(getItem(position).getIcon());
        }

        if(position == (newList.size()-1))
            convertView.findViewById(R.id.line).setVisibility(View.INVISIBLE);

        return convertView;
    }


}
