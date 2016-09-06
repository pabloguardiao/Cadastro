package br.com.caelum.cadastro;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

/**
 * Created by android6519 on 05/09/16.
 */
public class EnviaAlunosTask extends AsyncTask<Object, Object, String> {

    private final Context context;
    private ProgressDialog progress;
    private String endereco = "https://www.caelum.com.br/mobile";

    public EnviaAlunosTask(Context context) {
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progress = ProgressDialog.show(context, "Aguarde...", "Envio de dados para a web", true, true);

    }

    @Override
    protected String doInBackground(Object... params) {
        AlunoDAO dao = new AlunoDAO(context);
        String json = new AlunoConverter().toJSON(dao.getLista());
//                Toast.makeText(ListaAlunosActivity.this, json, Toast.LENGTH_LONG).show();
        WebClient client = new WebClient(endereco);
        String resposta = client.post(json);
        return resposta;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        progress.dismiss();

        Toast.makeText(context, s, Toast.LENGTH_LONG).show();

    }
}
