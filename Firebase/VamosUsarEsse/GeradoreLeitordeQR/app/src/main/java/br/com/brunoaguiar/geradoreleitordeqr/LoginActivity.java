package br.com.brunoaguiar.geradoreleitordeqr;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class LoginActivity extends AppCompatActivity{

    Button botaoLogar;
    ProgressBar progressBar;
    EditText campoEmail, campoSenha;
    final Context context = this;
    private FirebaseAuth auth;

   @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

       botaoLogar = findViewById(R.id.botaoLogar);
       campoEmail = findViewById(R.id.campoEmail);
       campoSenha = findViewById(R.id.campoSenha);
       progressBar = findViewById(R.id.progressBar);
       progressBar.setVisibility(View.INVISIBLE);


        manterLogin();
        clicks();

   }

    @Override
    protected void onStart(){
        super.onStart();
        auth = Conexao.getFirebaseAuth();
        auth = FirebaseAuth.getInstance();

        FirebaseUser currentUser = auth.getCurrentUser();
    }

    private void clicks() {

        botaoLogar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    String email = campoEmail.getText().toString();
                    String senha = campoSenha.getText().toString();


                    if (email.equalsIgnoreCase("")) {
                        alert("Login Invalido");
                        botaoLogar.setVisibility(View.INVISIBLE);
                        progressBar.setVisibility(View.VISIBLE);
                    }else if (senha.equals("")) {
                        alert("Digite a senha");
                    }else {

                        login(email, senha);

                        Intent intent = new Intent(LoginActivity.this, TelaInicial.class);

                        SharedPreferences prefs =  getSharedPreferences("login", MODE_PRIVATE);
                        SharedPreferences.Editor editor = prefs.edit();
                        editor.putBoolean("estaLogado", true);
                        editor.putString("usuario", campoEmail.getText().toString());
                        editor.apply();
                        editor.commit();

                    }
                }

                private void login(String email, String senha) {
                    auth.signInWithEmailAndPassword(email,senha)
                        .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if(task.isSuccessful()){
                                        Intent intent = new Intent(LoginActivity.this, TelaInicial.class);
                                            startActivity(intent);
                                                finish();
                                        }else{
                                            Toast.makeText(context, "Email e/ou senha errados", Toast.LENGTH_SHORT).show();

                                        }
                                    }
                                });
                }
        });
    }
    private boolean manterLogin(){

        SharedPreferences prefs = getSharedPreferences("login", 0);
        boolean jalogou = prefs.getBoolean("estaLogado", false);

        if(jalogou){
            Intent intent = new Intent(LoginActivity.this, TelaInicial.class);
            startActivity(intent);
            return true;
        }else {
            return false;
        }

    }




    private void alert (String msg){
        Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_SHORT).show();
    }
    }

                   /* if (email.equalsIgnoreCase("")) {
                        alert("Login Invalido");
                    }else if (senha.equals("")) {
                        alert("Digite a senha");
                    }else {


                        alert("Login efetuado com sucesso");

                        Intent intent = new Intent(LoginActivity.this, TelaInicial.class);

                        SharedPreferences prefs =  getSharedPreferences("login", MODE_PRIVATE);
                        SharedPreferences.Editor editor = prefs.edit();
                        editor.putBoolean("estaLogado", true);
                        editor.putString("usuario", campoEmail.getText().toString());
                        editor.apply();
                        editor.commit();

                        startActivity(intent);

                        finish();


                    }




                }
            });*/

