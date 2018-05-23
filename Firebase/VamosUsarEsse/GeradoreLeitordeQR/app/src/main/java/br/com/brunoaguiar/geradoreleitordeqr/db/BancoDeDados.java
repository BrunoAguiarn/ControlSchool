/*package br.com.brunoaguiar.geradoreleitordeqr.db;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;

import br.com.brunoaguiar.geradoreleitordeqr.R;

public class BancoDeDados extends SQLiteOpenHelper{

    private static final String DB_NOME = "ControlSchool";
    private  static final int DB_VERSAO = 1;

    public BancoDeDados(Context context) {
        super (context, DB_NOME, null, DB_VERSAO);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {


        /*final String create_table_aluno =
                "CREATE TABLE aluno ( " +
                        "_id integer primary key autoincrement," +
                        "matricula integer not null, " +
                        "nome text not null, " +
                        "idade integer not null, " +
                        "sexo text not null, " +
                        "turma integer not null, " +
                        "telefone integer not null, " +
                        "pai text not null, " +
                        "mae text not null, " +
                        "usuario text not null, " +
                        "senha text not null" +               //Completar
                        ")";

        final String create_table_professor =
                "CREATE TABLE professor ( " +
                        "_id integer primary key autoincrement," +
                        "nome text not null," +
                        "email text not null," +
                        "login not null," +
                        "senha not null" +
                        ")";

        db.execSQL(create_table_aluno);
        db.execSQL(create_table_professor);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        if (newVersion == 2) {

        }

    }

    public String validarLogin(String usuario, String senha){


        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM aluno WHERE usuario = ? AND senha = ?", new String[]{usuario, senha});
        if (cursor.getCount()>0){
            return "OK";
        }
        return "ERRO";
    }
}*/
