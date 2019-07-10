package br.ufmt.ic.computacaomovel;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d("ComputacaoMovel","onCreate");

        //Buscar as views no layout
        final EditText edtText = (EditText) findViewById(R.id.edtText);
        Button btn = (Button) findViewById(R.id.btn);

        //Definir o que fazer quando clicar no botão
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Recupera o texto do EditText
                String texto = edtText.getText().toString();
                //Cria o Intent
                Intent it = new Intent(MainActivity.this, OutraActivity.class);
                it.putExtra("msg", texto);
                startActivity(it);
                Toast.makeText(MainActivity.this,"Cliquei!", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("ComputacaoMovel","onStart");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("ComputacaoMovel","onRestart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("ComputacaoMovel","onResume");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("ComputacaoMovel","onStop");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("ComputacaoMovel","onPause");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("ComputacaoMovel","onDestroy");
    }
}
