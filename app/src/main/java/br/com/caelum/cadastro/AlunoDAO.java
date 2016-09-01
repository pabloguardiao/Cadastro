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

    private static final int VERSAO = 2;
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
            String sql = "ALTER TABLE " + TABELA + " ADD COLUMN caminhoFoto TEXT; ";
            db.execSQL(sql);
        }
    }

    public void inserir(Aluno aluno){
        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABELA, null, aluno.toContentValues());
        db.close();
    }

    public void alterar(Aluno aluno){
        SQLiteDatabase db = getWritableDatabase();
        db.update(TABELA, aluno.toContentValues(), "id = ?", new String[]{aluno.getId().toString()});
        db.close();
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
            alunoAux.setTelefone(c.getString(c.getColumnIndex("telefone")));
            alunoAux.setSite(c.getString(c.getColumnIndex("site")));
            alunoAux.setNota(c.getDouble(c.getColumnIndex("nota")));
            alunoAux.setCaminhoFoto(c.getString(c.getColumnIndex("caminhoFoto")));

            lista.add(alunoAux);
        }
        c.close();
        return lista;
    }

    public void remover(Aluno aluno) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABELA, "id=?", new String[]{aluno.getId().toString()});
        db.close();
    }
}
