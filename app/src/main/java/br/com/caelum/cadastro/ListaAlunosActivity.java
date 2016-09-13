package br.com.caelum.cadastro;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ListaAlunosActivity extends AppCompatActivity {

    private List<Aluno> alunos = new ArrayList<Aluno>();
    private ListView listaAlunos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_alunos);

//        List<Aluno> alunos = new AlunoDAO(this).getLista();
//        final ArrayAdapter<Aluno> adapter = new ArrayAdapter<Aluno>(this,
//                android.R.layout.simple_list_item_1, alunos);

        final ListaAlunosAdapter adapter = new ListaAlunosAdapter(alunos, this);
        listaAlunos = (ListView)findViewById(R.id.lista_alunos);
        listaAlunos.setAdapter(adapter);

        listaAlunos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(ListaAlunosActivity.this, "Posicao selecionada: " + position, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ListaAlunosActivity.this, FormularioActivity.class);
                intent.putExtra(Aluno.class.getName(), alunos.get(position));
                startActivity(intent);
            }
        });

//        listaAlunos.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
//            @Override
//            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
//                Aluno aluno = adapter.getItem(position);
//                Toast.makeText(ListaAlunosActivity.this, "Clique long: " + aluno.getNome(), Toast.LENGTH_LONG).show();
//                return true;
//            }
//        });

        Button botao = (Button)findViewById(R.id.botao_ins);
        botao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListaAlunosActivity.this, FormularioActivity.class);
                startActivity(intent);
            }
        });

        registerForContextMenu(listaAlunos);
    }

    @Override
    protected void onResume() {
        super.onResume();
        carregarLista();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        int pos = ((AdapterView.AdapterContextMenuInfo)menuInfo).position;
        final Aluno alunoSelecionado = (Aluno) listaAlunos.getAdapter().getItem(pos);
        MenuItem itemLigar = menu.add("Ligar");
        MenuItem itemSMS = menu.add("Enviar SMS");
        MenuItem itemMapa = menu.add("Achar no mapa");
        MenuItem itemSite = menu.add("Navegar no site");
        MenuItem itemDeletar = menu.add("Deletar");
        MenuItem itemEnviarNotas = menu.add("Enviar Notas");
        MenuItem itemReceberProvas = menu.add("Receber Provas");
        MenuItem itemGeo = menu.add("Geo");

        // Eventos
        Intent intent;
        intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:"+alunoSelecionado.getTelefone()));
        itemLigar.setIntent(intent);

        intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("sms:"+alunoSelecionado.getTelefone()));
        intent.putExtra("sms_body", "AAAAAA");
        itemSMS.setIntent(intent);

        intent = new Intent(Intent.ACTION_VIEW);
        String site = alunoSelecionado.getSite();
        if (!site.startsWith("http://")) {
            site = "http://" + site;
        }
        intent.setData(Uri.parse(site));
        itemSite.setIntent(intent);

        intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("geo:0,0?z=14&q="+Uri.encode(alunoSelecionado.getEndereco())));
        itemMapa.setIntent(intent);

        itemDeletar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(ListaAlunosActivity.this);
                dialog.setIcon(R.drawable.ic_opcoes).setTitle("Deletar").setMessage("Deseja mesmo deletar?");
                dialog.setPositiveButton("Quero", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            AlunoDAO dao = new AlunoDAO(ListaAlunosActivity.this);
                            dao.remover(alunoSelecionado);
                            dao.close();
                            carregarLista();
                        }
                });

                dialog.setNegativeButton("Nao", null);
                dialog.show();

                return false;
            }
        });

        itemEnviarNotas.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                new EnviaAlunosTask(ListaAlunosActivity.this).execute();
                return false;
            }
        });

        itemReceberProvas.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Intent intent = new Intent(ListaAlunosActivity.this, ProvasActivity.class);
                startActivity(intent);
                return false;
            }
        });

        itemGeo.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Intent intent = new Intent(ListaAlunosActivity.this, MostraAlunosActivity.class);
                startActivity(intent);
                return false;
            }
        });

        Permissao.verificarPermissao(this);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        for (int i=0; i<permissions.length; i++) {
            if (grantResults[i] == PackageManager.PERMISSION_DENIED) {
                Toast.makeText(ListaAlunosActivity.this, permissions[i], Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void carregarLista() {
        alunos.clear();
        alunos.addAll(new AlunoDAO(this).getLista());
    }

}
