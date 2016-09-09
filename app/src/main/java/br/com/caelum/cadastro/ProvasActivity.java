package br.com.caelum.cadastro;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

public class ProvasActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provas);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (isTablet()) {
            transaction
                    .replace(R.id.lista_provas, new ListaProvasFragment())
                    .replace(R.id.detalhes_provas, new DetalhesProvaFragment());
        } else {
            transaction.replace(R.id.provas_view, new ListaProvasFragment());
        }
        transaction.commit();
    }

    public boolean isTablet() {
        return getResources().getBoolean(R.bool.isTablet);
    }

    public void selecionarProva(Prova prova){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (isTablet()) {
            transaction
                    .replace(R.id.lista_provas, new ListaProvasFragment())
                    .replace(R.id.detalhes_provas, new DetalhesProvaFragment());
        } else {
            transaction.replace(R.id.provas_view, new ListaProvasFragment());
        }
        transaction.commit();
    }
}
