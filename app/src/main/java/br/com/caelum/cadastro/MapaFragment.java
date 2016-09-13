package br.com.caelum.cadastro;

import android.util.Log;

import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;

/**
 * Created by android6519 on 12/09/16.
 */
public class MapaFragment extends SupportMapFragment {

    @Override
    public void onResume() {
        super.onResume();

//        getMapAsync()
        Localizador localizador = new Localizador(getActivity());
        LatLng local = localizador.getCoordenada("Rua Vergueiro 3185 Vila Mariana");

        Log.i("MAPA", "Coordenada da Caelum: " + local);
    }
}
