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
    ArrayList<String> nombresss =new ArrayList<>();
    static ArrayList<String>urlimg=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lista=findViewById(R.id.listapkm);
        //preejecucion
        System.out.println("Hola mundoss");
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Document rescompleto = Jsoup.connect("https://www.pokemon.com/es/pokedex/").get();
                    nombresss = (ArrayList<String>) rescompleto.select("[href^=/es/pokedex/]").eachText();
                    nombresss.remove(0);//En esta pagina el elemento 0 no nos vale
                    for (int i = 0; i< nombresss.size(); i++){
                        String numpkm=String.format("%03d",i+1);
                        //conformar la lista de URLS.
                        urlimg.add("https://assets.pokemon.com/assets/cms2/img/pokedex/full/"+numpkm+".png");
                        pokemons.add(new Pokemon(nombresss.get(i)));//coformar la lista de nombres.
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