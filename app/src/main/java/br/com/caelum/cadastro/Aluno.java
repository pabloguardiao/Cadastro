package br.com.caelum.cadastro;

import android.content.ContentValues;

import java.io.Serializable;

/**
 * Created by android6519 on 24/08/16.
 */
public class Aluno implements Serializable {
    private Long id;
    private String nome;
    private String telefone;
    private String endereco;
    private String site;
    private Double nota;

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getEndereco() {
        return endereco;
    }

    public String getSite() {
        return site;
    }

    public Double getNota() {
        return nota;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public void setNota(Double nota) {
        this.nota = nota;
    }

    public ContentValues toContentValues() {
        ContentValues contentValues = new ContentValues();
        contentValues.put("nome", getNome());
        contentValues.put("telefone", getTelefone());
        contentValues.put("endereco", getEndereco());
        contentValues.put("site", getSite());
        contentValues.put("nota", getNota());

        return contentValues;
    }

    @Override
    public String toString() {
        return getNome();
    }
}
