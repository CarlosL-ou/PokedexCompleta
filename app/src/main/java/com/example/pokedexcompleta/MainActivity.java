package com.example.pokedexcompleta;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
ListView lista;
    //Jsoup --> JSON.
    ArrayList<Pokemon>pokemons=new ArrayList<>();
    ArrayList<String>nombres=new ArrayList<>();
    static ArrayList<String>urlimg=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        lista=findViewById(R.id.listapkm);
        //preejecucion
        System.out.println("Hola mundoss");
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Document rescompleto = Jsoup.connect("https://www.pokemon.com/es/pokedex/").get();
                    nombres= (ArrayList<String>) rescompleto.select("[href^=/es/pokedex/]").eachText();
                    nombres.remove(0);//En esta pagina el elemento 0 no nos vale
                    for (int i=0;i< nombres.size();i++){
                        String numpkm=String.format("%03d",i+1);
                        //conformar la lista de URLS.
                        urlimg.add("https://assets.pokemon.com/assets/cms2/img/pokedex/full/"+numpkm+".png");
                        pokemons.add(new Pokemon(nombres.get(i)));//coformar la lista de nombres.
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                //---------------Post-ejecution
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        CustomAdapter adapter=new CustomAdapter(pokemons,MainActivity.this);
                        lista.setAdapter(adapter);
                    }
                });
            }
        }).start();


    }
}