package br.com.brunoaguiar.geradoreleitordeqr;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.Toast;

public class ChamadaActivity extends AppCompatActivity {

    CheckBox checkBox1, checkBox2, checkBox3, checkBox4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chamada);

        checkBox1 = findViewById(R.id.checkbox1);
        checkBox2 = findViewById(R.id.checkbox2);
        checkBox3 = findViewById(R.id.checkbox3);
        checkBox4 = findViewById(R.id.checkbox4);


        SharedPreferences prefs = getSharedPreferences("chamada", Context.MODE_PRIVATE);
        int aluno = prefs.getInt("aluno", 0);

        if (aluno == 1){
            checkBox1.setChecked(true);
        }if (aluno == 2){
            checkBox2.setChecked(true);
        }if (aluno == 3){
            checkBox3.setChecked(true);
        }if (aluno == 4){
            checkBox4.setChecked(true);
        }if (aluno == 0){
            Toast.makeText(this, "num deu", Toast.LENGTH_SHORT).show();
        }


        SharedPreferences preferences = getSharedPreferences("chamada", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("aluno", 0);
        editor.apply();
        editor.commit();


    }

}
