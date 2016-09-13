package br.com.caelum.cadastro;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MostraAlunosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostra_alunos);

        MapaFragment mapaFragment = new MapaFragment();
        FragmentTransaction trans = getSupportFragmentManager().beginTransaction();
        trans.replace(R.id.mostra_alunos_mapa, mapaFragment);
        trans.commit();
    }
}
