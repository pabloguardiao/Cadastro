package br.com.caelum.cadastro;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by android6519 on 01/09/16.
 */
public class ListaAlunosAdapter extends BaseAdapter {

    private List<Aluno> alunos;
    private Activity activity;

    public ListaAlunosAdapter(List<Aluno> alunos, Activity activity) {
        this.alunos = alunos;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        int qtd = 0;
        if (alunos != null) {
            qtd = alunos.size();
        }
        return qtd;
    }

    @Override
    public Aluno getItem(int position) {
        Aluno aluno = null;
        if (position < getCount()) {
            aluno = alunos.get(position);
        }
        return aluno;
    }

    @Override
    public long getItemId(int position) {
        int id = -1;
        if (position < getCount()) {
            id = alunos.get(position).getId().intValue();
        }
        return id;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = activity.getLayoutInflater().inflate(R.layout.item, parent, false);
        Aluno aluno = getItem(position);

        TextView txNome = (TextView)view.findViewById(R.id.item_nome);
        txNome.setText(aluno.getNome());

        Bitmap bm = null;
        ImageView ivFoto = (ImageView) view.findViewById(R.id.item_foto);
        if (aluno.getCaminhoFoto() != null) {
            bm = BitmapFactory.decodeFile(aluno.getCaminhoFoto());
        }
        if (bm == null) {
            bm = BitmapFactory.decodeResource(activity.getResources(), R.drawable.ic_no_image);
        }
        ivFoto.setImageBitmap(Bitmap.createScaledBitmap(bm, 100, 100, true));
        if (position%2 == 0) {
            view.setBackgroundColor(activity.getResources().getColor(R.color.linha_par));
        } else {
            view.setBackgroundColor(activity.getResources().getColor(R.color.linha_impar));
        }
        return view;
    }
}
