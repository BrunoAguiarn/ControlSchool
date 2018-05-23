package br.com.brunoaguiar.geradoreleitordeqr;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.List;

import br.com.brunoaguiar.geradoreleitordeqr.modelo.Aluno;

public class ConsultaAlunoActivity extends AppCompatActivity {

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    ListView listViewDados;

    private List<Aluno> listAluno = new ArrayList<Aluno>();
    private ArrayAdapter<Aluno> arrayAdapterAluno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta_aluno);

        listViewDados = findViewById(R.id.listaAluno);

        iniciarFirebase();

        mostrarCadastros();

    }


    private void iniciarFirebase() {

        FirebaseApp.initializeApp(ConsultaAlunoActivity.this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }


    private void mostrarCadastros() {
        databaseReference.child("aluno").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                    listAluno.clear();
                    for (DataSnapshot snapshot:dataSnapshot.getChildren()){
                        Aluno aluno = snapshot.getValue(Aluno.class);
                        listAluno.add(aluno);
                    }
                    arrayAdapterAluno = new ArrayAdapter<Aluno>(ConsultaAlunoActivity.this, android.R.layout.simple_list_item_1, listAluno);
                listViewDados.setAdapter(arrayAdapterAluno);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}

//Codigo com Banco Local

        /*ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Consulta Aluno");
        actionBar.setDisplayHomeAsUpEnabled(true);

        AlunoController crud = new AlunoController(getBaseContext());
        final Cursor cursor = crud.retrieve();

        String[] campos = {"_id", "matricula", "nome", "idade", "sexo", "turma", "telefone", "pai", "mae", "senha"};
        int[] componentes = {R.id.textoCodigo, R.id.textoMatricula, R.id.textoNome, R.id.textoIdade, R.id.textoSexo, R.id.textoTurma, R.id.textoTelefone, R.id.textoPai, R.id.textoMae};

        SimpleCursorAdapter adapter = new SimpleCursorAdapter(getBaseContext(),
                R.layout.aluno, cursor, campos, componentes, 0);

        ListView lista = findViewById(R.id.listaAluno);

        lista.setAdapter(adapter);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                cursor.moveToPosition(i);
                String idAluno = cursor.getString(cursor.getColumnIndexOrThrow("_id"));

                Intent intent = new Intent(ConsultaAlunoActivity.this, CadastroAlunoActivity.class);
                intent.putExtra("id", idAluno);

                startActivity(intent);
                finish();*/