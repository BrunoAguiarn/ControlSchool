package br.com.brunoaguiar.geradoreleitordeqr;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import br.com.brunoaguiar.geradoreleitordeqr.modelo.Aluno;

public class GeradorActivity extends Activity {

    ImageView ivQrCode;
    String resultadoQr;
    TextView txtResultado;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    FirebaseAuth auth;
    String[] alunos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gerador_activity);


        inicializarComponentes();
        iniciarFirebase();
        gerarQr();


    }


    private void inicializarComponentes() {
        ivQrCode =  findViewById(R.id.ivQrCode);
        txtResultado = findViewById(R.id.txtResultado);
    }

    private void iniciarFirebase() {

        FirebaseApp.initializeApp(GeradorActivity.this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference  = firebaseDatabase.getReference();
    }


    public void gerarQr() {

        SharedPreferences prefs = getSharedPreferences("login", Context.MODE_PRIVATE);
        final String user = prefs.getString("usuario", "Não encontrado");

        databaseReference.child("aluno").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot:dataSnapshot.getChildren()){
                    Aluno aluno = snapshot.getValue(Aluno.class);

                    final List bot = new ArrayList();
                    bot.add(String.valueOf(aluno.getTurma()));
                    bot.add(String.valueOf(aluno.getMatricula()));
                    bot.add(aluno.getNome());
                    bot.add(aluno.getUsuario());


                    /*alunos[2] = String.valueOf(aluno.getMatricula());
                    alunos[3] = aluno.getNome();
                    alunos[4] = aluno.getUsuario();*/


                    /*String turma = alunos[1];
                    String matricula = alunos[2];
                    String nome = alunos[3];
                    String usuario = alunos[4];
                    String fim = turma + matricula + nome + usuario;*/

                    Random random = new Random();
                    Object a = bot.get(random.nextInt(bot.size()));
                    String texto = a.toString();
                    Toast.makeText(GeradorActivity.this, texto, Toast.LENGTH_SHORT).show();

                }


            }





            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

            /*String turma = String.valueOf(aluno.getTurma());
            String nome = aluno.getNome();
            String matricula = String.valueOf(aluno.getMatricula());
            String usuario = aluno.getUsuario();

            resultadoQr = turma + "." + nome + "." + usuario + "." + matricula;

            Toast.makeText(this, resultadoQr, Toast.LENGTH_SHORT).show();

            /*MultiFormatWriter multiFormatWriter = new MultiFormatWriter();

            try {
                BitMatrix bitMatrix = multiFormatWriter.encode(resultadoQr, BarcodeFormat.QR_CODE, 2000, 2000);
                BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
                Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
                ivQrCode.setImageBitmap(bitmap);
            } catch (WriterException e) {
                e.printStackTrace();
            }*/
        }



    private void alert(String mensagem) {
        Toast.makeText(this, mensagem, Toast.LENGTH_SHORT).show();
    }

}






