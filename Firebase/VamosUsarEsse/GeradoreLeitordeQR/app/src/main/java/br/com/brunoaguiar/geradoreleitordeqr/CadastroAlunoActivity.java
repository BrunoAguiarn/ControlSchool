package br.com.brunoaguiar.geradoreleitordeqr;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.math.BigDecimal;
import java.util.UUID;

import br.com.brunoaguiar.geradoreleitordeqr.modelo.Aluno;

public class CadastroAlunoActivity extends AppCompatActivity {

    EditText editTextMatricula, editTextNome, editTextIdade, editTextTurma, editTextTelefone, editTextPai, editTextMae, editTextSenha, editTextUsuario, editTextEmail;
    RadioButton rdbMasc, rdbFem;
    String resultadoRadioButton;
    Button salvar;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_aluno);

        inicializarComponentes();
        iniciarFirebase();

        salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                if (!validarCampos()) {
                    return;
                }

                verificarRadioButton();


                Aluno aluno = new Aluno();

                aluno.setId(UUID.randomUUID().toString());
                aluno.setMatricula(Integer.parseInt(editTextMatricula.getText().toString()));
                aluno.setNome(editTextNome.getText().toString());
                aluno.setEmail(editTextEmail.getText().toString());
                aluno.setIdade(Integer.parseInt(editTextIdade.getText().toString()));
                aluno.setSexo(resultadoRadioButton);
                aluno.setTurma(Integer.parseInt(editTextTurma.getText().toString()));
                aluno.setTelefone(Integer.parseInt(editTextTelefone.getText().toString()));
                aluno.setPai(editTextPai.getText().toString());
                aluno.setMae(editTextMae.getText().toString());
                aluno.setUsuario(editTextUsuario.getText().toString());
                aluno.setSenha(editTextSenha.getText().toString());

                databaseReference.child("aluno").child(aluno.getId()).setValue(aluno);




            }});
    }

    private void inicializarComponentes(){

        editTextMatricula = findViewById(R.id.editTextMatricula);
        editTextNome = findViewById(R.id.editTextNome);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextIdade = findViewById(R.id.editTextIdade);
        editTextTurma = findViewById(R.id.editTextTurma);
        editTextTelefone = findViewById(R.id.editTextTelefone);
        editTextPai = findViewById(R.id.editTextPai);
        editTextMae = findViewById(R.id.editTextMae);
        editTextUsuario = findViewById(R.id.editTextUsuario);
        editTextSenha = findViewById(R.id.editTextSenha);
        rdbMasc = findViewById(R.id.rdbMasc);
        rdbFem = findViewById(R.id.rdbFem);
        salvar = findViewById(R.id.salvar);
    }

    @Override
    protected void onStart(){
        super.onStart();
        auth = Conexao.getFirebaseAuth();
    }


    private void criarUser(String email, String senha){
        auth.createUserWithEmailAndPassword(email,senha)
                .addOnCompleteListener(CadastroAlunoActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    alert("Deu Certo");

                    Intent intent = new Intent(getBaseContext(), TelaInicial.class);
                    startActivity(intent);

                    finish();
                }else {
                    alert("Não deu");
                    return;
                }
            }
        });
    }

    private void mostrarCadastros() {

    }




    private void alert(String msg){
        Toast.makeText(CadastroAlunoActivity.this, msg, Toast.LENGTH_SHORT).show();
    }

    private void iniciarFirebase() {

        FirebaseApp.initializeApp(CadastroAlunoActivity.this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }


    public void verificarRadioButton(){

        if (rdbMasc.isChecked()){
            resultadoRadioButton = "Masculino";
        }
        if (rdbFem.isChecked()){
            resultadoRadioButton = "Feminino";
        }

    }

    public boolean validarCampos(){

        if(editTextMatricula.getText().toString().trim().equals("")) {
            editTextMatricula.setError(getString(R.string.campo_obrigatorio));
            editTextMatricula.requestFocus();
            return false;
        }else if(editTextNome.getText().toString().trim().equals("")) {
            editTextNome.setError(getString(R.string.campo_obrigatorio));
            editTextNome.requestFocus();
            return false;
        }else if(editTextIdade.getText().toString().trim().equals("")) {
            editTextIdade.setError(getString(R.string.campo_obrigatorio));
            editTextIdade.requestFocus();
            return false;
        }else if(editTextTurma.getText().toString().trim().equals("")) {
            editTextTurma.setError(getString(R.string.campo_obrigatorio));
            editTextTurma.requestFocus();
            return false;
        }else if(editTextTelefone.getText().toString().trim().equals("")) {
            editTextTelefone.setError(getString(R.string.campo_obrigatorio));
            editTextTelefone.requestFocus();
            return false;
        }else if(editTextPai.getText().toString().trim().equals("")) {
            editTextPai.setError(getString(R.string.campo_obrigatorio));
            editTextPai.requestFocus();
            return false;
        }else if(editTextMae.getText().toString().trim().equals("")) {
            editTextMae.setError(getString(R.string.campo_obrigatorio));
            editTextMae.requestFocus();
            return false;
        }else if(editTextSenha.getText().toString().trim().equals("")) {
            editTextSenha.setError(getString(R.string.campo_obrigatorio));
            editTextSenha.requestFocus();
            return false;
        }else if(editTextUsuario.getText().toString().trim().equals("")) {
            editTextUsuario.setError(getString(R.string.campo_obrigatorio));
            editTextUsuario.requestFocus();
            return false;
        }else if(editTextEmail.getText().toString().trim().equals("")) {
            editTextEmail.setError(getString(R.string.campo_obrigatorio));
            editTextEmail.requestFocus();
            return false;
        }
return true;
    }
}


    //Cadastro com banco local

/*verificarRadioButton();

        Aluno aluno = new Aluno();
        aluno.setMatricula(Integer.parseInt(editTextMatricula.getText().toString()));
        aluno.setNome(editTextNome.getText().toString());
        aluno.setIdade(Integer.parseInt(editTextIdade.getText().toString()));
        aluno.setSexo(resultadoRadioButton);
        aluno.setTurma(Integer.parseInt(editTextTurma.getText().toString()));
        aluno.setTelefone(Integer.parseInt(editTextTelefone.getText().toString()));
        aluno.setPai(editTextPai.getText().toString());
        aluno.setMae(editTextMae.getText().toString());
        aluno.setUsuario(editTextUsuario.getText().toString());
        aluno.setSenha(editTextSenha.getText().toString());

        AlunoController crud = new AlunoController((getBaseContext()));
        long resultado = crud.create(aluno);

        if (resultado == -1) {
            Toast.makeText(getBaseContext(), "Erro ao Salvar", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getBaseContext(), "Sucesso ao Salvar", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(getBaseContext(), TelaInicial.class);
            startActivity(intent);
        }

    }
        });*/
