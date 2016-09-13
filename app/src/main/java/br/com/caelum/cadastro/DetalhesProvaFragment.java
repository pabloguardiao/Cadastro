package br.com.caelum.cadastro;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;


public class DetalhesProvaFragment extends Fragment {

    private Prova prova;

    private TextView tvMateria;
    private TextView tvData;
    private ListView lvTopicos;

    public DetalhesProvaFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detalhes_prova, container, false);

        if (getArguments() != null) {
            prova = (Prova) getArguments().getSerializable("prova");
        }

        buscarComponents(view);
        popularCampos(prova);
        return view;
    }

    private void buscarComponents(View layout) {
        tvMateria = (TextView) layout.findViewById(R.id.detalhe_prova_materia);
        tvData = (TextView) layout.findViewById(R.id.detalhe_prova_data);
        lvTopicos = (ListView) layout.findViewById(R.id.detalhe_prova_topicos);
    }

    public void popularCampos(Prova prova) {
        if (prova != null) {
            tvMateria.setText(prova.getMateria());
            tvData.setText(prova.getData());
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, prova.getTopicos());
            lvTopicos.setAdapter(adapter);
        }
    }
}
