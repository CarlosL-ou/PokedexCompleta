package com.example.pokedexcompleta;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter {
    ArrayList<Pokemon>pokemons;
    Context ctx;

    public CustomAdapter(ArrayList<Pokemon> pokemons, Context ctx) {
        this.pokemons = pokemons;
        this.ctx = ctx;
    }

    @Override
    public int getCount()
    {
        return pokemons.size();
    }

    @Override
    public Object getItem(int i)
    {
        return pokemons.get(i);
    }

    @Override
    public long getItemId(int i)
    {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup)
    {
        //geera un view nuevo a partir del layout de "Item_pkm" generado.
        View viewinflado=LayoutInflater.from(ctx).inflate(R.layout.item_pkm,null);
        TextView nombre = viewinflado.findViewById(R.id.nombrepkm);
        ImageView imagenpkm = viewinflado.findViewById(R.id.imagenpkm);
        nombre.setText(pokemons.get(i).getNombre());
        Picasso.get().load(MainActivity.urlimg.get(i)).into(imagenpkm);
        return viewinflado;
    }
}
