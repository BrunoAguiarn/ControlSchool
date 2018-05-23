package br.com.brunoaguiar.geradoreleitordeqr;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.regex.Pattern;

public class TelaInicial extends AppCompatActivity {

    Button ler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_inicial);


        ler = findViewById(R.id.ler);


        final Activity activity = this;


        ler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentIntegrator integrator = new IntentIntegrator(activity);
                integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
                integrator.setPrompt("Camera Scan");
                integrator.setCameraId(0);
                integrator.initiateScan();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() != null) {

                String qrCode = result.toString();
                String[] resultado = qrCode.split(Pattern.quote("."));
                resultado[0] = resultado[0].trim();
                resultado[1] = resultado[1].trim();
                resultado[2] = resultado[2].trim();
                resultado[3] = resultado[3].trim();

                String[] turmanum = resultado[0].split(Pattern.quote(" "));
                String turma = turmanum[turmanum.length - 1];

                String[] nome = resultado[1].split(Pattern.quote(" "));
                Toast.makeText(this, turma, Toast.LENGTH_SHORT).show();

                SharedPreferences prefs = getSharedPreferences("chamada", MODE_PRIVATE);
                SharedPreferences.Editor editor = prefs.edit();


                if (turma.equals("310")) {
                    if (nome[0].equalsIgnoreCase("Andre")) {
                        editor.putInt("aluno", 1);
                        editor.apply();
                        editor.commit();

                    }
                    if (nome[0].equalsIgnoreCase("Bruno")) {
                        editor.putInt("aluno", 2);
                        editor.apply();
                        editor.commit();

                        Toast.makeText(this, "AAAAAA", Toast.LENGTH_SHORT).show();
                    }
                    if (nome[0].equalsIgnoreCase("Everson")) {
                        editor.putInt("aluno", 3);
                        editor.apply();
                        editor.commit();

                    }
                    if (nome[0].equalsIgnoreCase("Nilson")) {
                        editor.putInt("aluno", 4);
                        editor.apply();
                        editor.commit();

                    }
                } else {
                    Toast.makeText(this, "Aluno não cadastrado corretamente", Toast.LENGTH_SHORT).show();
                }

                Intent intent = new Intent(this, TelaInicial.class);
                startActivity(intent);
                finish();

            } else {
                /*alert("Scan Cancelado");*/
            }

        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_principal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.deslogar:

                Intent intent = new Intent(this, LoginActivity.class);

                SharedPreferences prefs = getSharedPreferences("login", 0);
                SharedPreferences.Editor editor = prefs.edit();
                editor.putBoolean("estaLogado", false);

                editor.commit();

                startActivity(intent);

                finish();


                Toast.makeText(this, "Você Saiu", Toast.LENGTH_SHORT).show();
                break;
            default:
                super.onOptionsItemSelected(item);
        }
        return true;
    }


   public void gerador(View view){

        Intent intent = new Intent(getBaseContext(), GeradorActivity.class);
        startActivity(intent);}


    private void alert (String msg){
        Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_SHORT).show();
    }

    public void cadastroAluno(View view) {

        Intent intent = new Intent(getBaseContext(), CadastroAlunoActivity.class);
        startActivity(intent);
    }
   public void consultaAlunos(View view){

        Intent intent = new Intent(getBaseContext(),ConsultaAlunoActivity.class);
        startActivity(intent);
    }
}

   /* public void abrirChamada(View view) {
        Intent intent = new Intent(getBaseContext(),ChamadaActivity.class);
        startActivity(intent);
    }*/


