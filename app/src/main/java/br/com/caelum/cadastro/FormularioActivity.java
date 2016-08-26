package br.com.caelum.cadastro;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class FormularioActivity extends AppCompatActivity {

    private FormularioHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);

        helper = new FormularioHelper(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuItem mi = menu.add("Inserir");
//        mi.setIcon(R.drawable.ic_confirmar);
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_formulario_ok){

            if (helper.temNome()) {
                Aluno aluno = helper.carregarAlunoDoFormulario();
                AlunoDAO dao = new AlunoDAO(this);
                dao.salvar(aluno);
                dao.close();
                finish();
            } else {
                helper.mostrarErro();
            }
            return false;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }
}
