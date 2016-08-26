package br.com.caelum.cadastro;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ListaAlunosActivity extends AppCompatActivity {

    public List<Aluno> alunos = new ArrayList<Aluno>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_alunos);

//        List<Aluno> alunos = new AlunoDAO(this).getLista();
        final ArrayAdapter<Aluno> adapter = new ArrayAdapter<Aluno>(this,
                android.R.layout.simple_list_item_1, alunos);

        ListView listaAlunos = (ListView)findViewById(R.id.lista_alunos);
        listaAlunos.setAdapter(adapter);

        listaAlunos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(ListaAlunosActivity.this, "Posicao selecionada: " + position, Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(ListaAlunosActivity.this, FormularioActivity.class);
//                intent.setData();
//                startActivity(intent);
            }
        });

        listaAlunos.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Aluno aluno = adapter.getItem(position);
                Toast.makeText(ListaAlunosActivity.this, "Clique long: " + aluno.getNome(), Toast.LENGTH_LONG).show();
                return true;
            }
        });

        Button botao = (Button)findViewById(R.id.botao_ins);
        botao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListaAlunosActivity.this, FormularioActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        alunos.clear();
        alunos.addAll(new AlunoDAO(this).getLista());

    }
}
