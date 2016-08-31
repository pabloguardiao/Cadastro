package br.com.caelum.cadastro;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;

import java.util.ArrayList;

/**
 * Created by android6519 on 31/08/16.
 */
public class Permissao {

    private static final int CODE = 123;
    private static ArrayList<String> listaPermissaoDesejada = new ArrayList<String>();

    public static void verificarPermissao(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            String[] permissoes = {Manifest.permission.CALL_PHONE,
                    Manifest.permission.RECEIVE_SMS, Manifest.permission.INTERNET};

            for (String permissao : permissoes) {
                if (activity.checkSelfPermission(permissao) != PackageManager.PERMISSION_GRANTED)
                    listaPermissaoDesejada.add(permissao);
            }

            if (listaPermissaoDesejada.size() > 0) {
                activity.requestPermissions(listaPermissaoDesejada.toArray(new String[]{}), CODE);
            }
        }
    }
}
