package br.com.caelum.cadastro;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by android6519 on 25/08/16.
 */
public class AlunoDAO extends SQLiteOpenHelper {

    private static final int VERSAO = 1;
    private static final String DATABASE = "CadastroCaelum";
    private static final String TABELA = "aluno";

    public AlunoDAO(Context context) {
        super(context, DATABASE, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String ddl = "CREATE TABLE " + TABELA + "(" +
                " id INTEGER PRIMARY KEY, " +
                " nome TEXT NOT NULL, " +
                " telefone TEXT, " +
                " endereco TEXT, " +
                " site TEXT, " +
                " nota REAL " +
                " )";
        db.execSQL(ddl);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion == 2) {

        }
    }

    public void salvar(Aluno aluno){
        if (aluno.getId() == null || aluno.getId() == 0) {
            getWritableDatabase().insert(TABELA, null, aluno.toContentValues());
        } else {
            getWritableDatabase().update(TABELA, aluno.toContentValues(), "where id = ?", new String[]{aluno.getId().toString()});
        }
    }

    public List<Aluno> getLista() {
        List<Aluno> lista = new ArrayList<Aluno>();
        Cursor c = getReadableDatabase().rawQuery("select * from " + TABELA, null);
        Aluno alunoAux;
        while (c.moveToNext()) {
            alunoAux = new Aluno();

            alunoAux.setId(c.getLong(c.getColumnIndex("id")));
            alunoAux.setNome(c.getString(c.getColumnIndex("nome")));
            alunoAux.setEndereco(c.getString(c.getColumnIndex("endereco")));
            alunoAux.setTelefone(c.getString(c.getColumnIndex("nome")));
            alunoAux.setSite(c.getString(c.getColumnIndex("site")));
            alunoAux.setNota(c.getDouble(c.getColumnIndex("nota")));

            lista.add(alunoAux);
        }
        c.close();
        return lista;
    }
}
