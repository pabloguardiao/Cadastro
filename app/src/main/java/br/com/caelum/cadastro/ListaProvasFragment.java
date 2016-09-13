package br.com.caelum.cadastro;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;

import br.com.caelum.cadastro.Prova;
import br.com.caelum.cadastro.R;

public class ListaProvasFragment extends Fragment {

    public ListaProvasFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View layoutProvas = inflater.inflate(R.layout.fragment_lista_provas, container, false);
        ListView lvProvas = (ListView) layoutProvas.findViewById(R.id.lista_provas_listview);

        Prova p1 = new Prova("20/06/2015", "Matematica");
        p1.setTopicos(Arrays.asList("Algebra linear", "Calculo", "Estatistica"));

        Prova p2 = new Prova("25/07/2015", "Portugues");
        p1.setTopicos(Arrays.asList("Complemento Nominal", "Oracoes Subordinadas", "Analise Sintatica"));

        List<Prova> provas = Arrays.asList(p1, p2);

        lvProvas.setAdapter(new ArrayAdapter<Prova>(getActivity(), android.R.layout.simple_list_item_1, provas));

        lvProvas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Prova selecionada = (Prova) parent.getItemAtPosition(position);

                ProvasActivity provasActivity = (ProvasActivity)getActivity();
                provasActivity.selecionarProva(selecionada);
//                Toast.makeText(getActivity(), "Prova Selecionada: " + selecionada, Toast.LENGTH_LONG).show();
            }
        });

        return layoutProvas;
    }

}
