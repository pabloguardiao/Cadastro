package br.com.caelum.cadastro;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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
    private ImageView ivFoto;
    private Button btFoto;

    public FormularioHelper(Activity activity) {
        txNome = (EditText)activity.findViewById(R.id.nome);
        txTelefone = (EditText)activity.findViewById(R.id.telefone);
        txSite= (EditText)activity.findViewById(R.id.site);
        txEndereco = (EditText)activity.findViewById(R.id.endereco);
        rbNota = (RatingBar) activity.findViewById(R.id.avaliacao);
        ivFoto = (ImageView) activity.findViewById(R.id.formulario_foto);
        btFoto = (Button) activity.findViewById(R.id.formulario_foto_button);

        aluno = new Aluno();
    }

    public Aluno carregarAlunoDoFormulario() {
        aluno.setNome(txNome.getText().toString());
        aluno.setTelefone(txTelefone.getText().toString());
        aluno.setSite(txSite.getText().toString());
        aluno.setEndereco(txEndereco.getText().toString());
        aluno.setNota(new Double(rbNota.getRating()));
        aluno.setCaminhoFoto((String)ivFoto.getTag());
        return aluno;
    }

    public void colocarAlunoNoFormulario(Aluno aluno1) {
        this.aluno = aluno1;
        txNome.setText(aluno.getNome());
        txTelefone.setText(aluno.getTelefone());
        txSite.setText(aluno.getSite());
        txEndereco.setText(aluno.getEndereco());
        rbNota.setRating(aluno.getNota().floatValue());
        ivFoto.setImageBitmap(BitmapFactory.decodeFile(aluno.getCaminhoFoto()));

    }

    public boolean temNome() {
        return !txNome.getText().toString().isEmpty();
    }

    public void mostrarErro() {
        txNome.setError("O campo nome nao pode ser vazio!");
    }

    public Button getBtFoto() {
        return btFoto;
    }

    public ImageView getIvFoto() {
        return ivFoto;
    }

    public void carregarImagem(String localArquivoFoto) {
        Bitmap imgFoto = BitmapFactory.decodeFile(localArquivoFoto);
        Bitmap imgMenor = Bitmap.createScaledBitmap(imgFoto, ivFoto.getWidth(), 300, true);
        imgFoto.recycle();
        ivFoto.setImageBitmap(imgMenor);
        ivFoto.setTag(localArquivoFoto);
        ivFoto.setScaleType(ImageView.ScaleType.FIT_XY);
    }
}
