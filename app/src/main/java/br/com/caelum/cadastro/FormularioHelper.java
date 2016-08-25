package br.com.caelum.cadastro;

import android.app.Activity;
import android.content.Context;
import android.widget.EditText;
import android.widget.RatingBar;

/**
 * Created by android6519 on 24/08/16.
 */
public class FormularioHelper {

    private Aluno aluno;

    private EditText txNome;
    private EditText txTelefone;
    private EditText txSite;
    private EditText txEndereco;
    private RatingBar rbNota;

    public FormularioHelper(Activity activity) {
        txNome = (EditText)activity.findViewById(R.id.nome);
        txTelefone = (EditText)activity.findViewById(R.id.telefone);
        txSite= (EditText)activity.findViewById(R.id.site);
        txEndereco = (EditText)activity.findViewById(R.id.endereco);
        rbNota = (RatingBar) activity.findViewById(R.id.avaliacao);

        aluno = new Aluno();
    }

    public Aluno carregarAlunoDoFormulario() {
        aluno.setNome(txNome.getText().toString());
        aluno.setTelefone(txTelefone.getText().toString());
        aluno.setSite(txSite.getText().toString());
        aluno.setEndereco(txEndereco.getText().toString());
        aluno.setNota(new Double(rbNota.getRating()));
        return aluno;
    }
}
