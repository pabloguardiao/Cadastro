package br.com.caelum.cadastro;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;

/**
 * Created by android6519 on 01/09/16.
 */
public class SMSReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
        Object[] mensagens = (Object[])bundle.get("pdus");
        byte[] mensagem = (byte[])mensagens[0];
        String formato = (String) bundle.get("format");
        SmsMessage sms;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            sms = SmsMessage.createFromPdu(mensagem, formato);
        } else {
            sms = SmsMessage.createFromPdu(mensagem);
        }
        MediaPlayer mp = MediaPlayer.create(context, R.raw.msg);
        mp.start();
    }
}
