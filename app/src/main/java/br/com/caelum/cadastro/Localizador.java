package br.com.caelum.cadastro;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;

import com.google.android.gms.maps.model.LatLng;

import java.io.IOException;
import java.util.List;

/**
 * Created by android6519 on 12/09/16.
 */
public class Localizador {
    private Geocoder geo;

    public Localizador(Context context) {
        this.geo = new Geocoder(context);
    }

    public LatLng getCoordenada(String endereco) {
        try {
            List<Address> lista = geo.getFromLocationName(endereco, 1);
            lista.get(0);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
