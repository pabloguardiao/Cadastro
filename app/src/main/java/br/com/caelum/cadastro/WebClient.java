package br.com.caelum.cadastro;

import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by android6519 on 05/09/16.
 */
public class WebClient {

    public String post(String json) {
        String jsonResposta = "";
        try {
            URL url = new URL("https://www.caelum.com.br/mobile");
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestProperty("Content-type", "application/json");
            connection.setDoInput(true);
            connection.setDoOutput(true);

            connection.connect();
            PrintStream saida = new PrintStream(connection.getOutputStream());
            saida.println(json);


            jsonResposta = new Scanner(connection.getInputStream()).next();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonResposta;
    }
}
