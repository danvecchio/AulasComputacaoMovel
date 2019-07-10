package br.ufmt.ic.computacaomovel;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class OutraActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_outra);

        //Recupera o layout
        final TextView txtView = (TextView) findViewById(R.id.txtView);
        //Recupera o Extra do Intent
        String texto = getIntent().getStringExtra("msg");
        txtView.setText(texto);
    }
}
