package br.com.brunoaguiar.geradoreleitordeqr;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class PerfilActivity extends AppCompatActivity {

    private TextView textUsuario, textID;
    private Button btnLogout;

    private FirebaseAuth auth;
    private FirebaseUser user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        inicializarComponentes();

    }



    private void inicializarComponentes(){
        textUsuario = findViewById(R.id.textUsuario);
        textID = findViewById(R.id.textPerfilId);
        btnLogout = findViewById(R.id.btnPerfilLogout);


    }

    @Override
    protected void onStart(){
        super.onStart();
        auth = Conexao.getFirebaseAuth();
        user = Conexao.getFirebaseUser();
        verificaUser();
    }

    private void verificaUser() {
        if (user == null){
            finish();
        }else{
            textUsuario.setText("Usuario: " + user.getEmail());
            textID.setText("ID: " + user.getUid());
        }

    }

    public void logOut(View view) {
        Conexao.logout();
        finish();

    }
}
