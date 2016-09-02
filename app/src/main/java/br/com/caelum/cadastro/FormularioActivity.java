package br.com.caelum.cadastro;

import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.io.File;

public class FormularioActivity extends AppCompatActivity {

    private FormularioHelper helper;
    private String localArquivoFoto;
    private static final int CODE = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);
        helper = new FormularioHelper(this);

        Aluno a = (Aluno) getIntent().getSerializableExtra(Aluno.class.getName());
        if (a != null) {
            helper.colocarAlunoNoFormulario(a);
        }

        helper.getBtFoto().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                localArquivoFoto = getExternalFilesDir(null) + "/" + System.currentTimeMillis() + ".jpg";
                Uri localFoto = Uri.fromFile(new File(localArquivoFoto));
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, localFoto);
                startActivityForResult(intent, CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CODE) {
            if (resultCode == RESULT_OK) {
                helper.carregarImagem(this.localArquivoFoto);
            } else {
                localArquivoFoto = null;
            }
        }
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
                if (aluno.getId()!= null) {
                    dao.alterar(aluno);
                } else {
                    dao.inserir(aluno);
                }
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
