/*package br.com.brunoaguiar.geradoreleitordeqr.controller;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import br.com.brunoaguiar.geradoreleitordeqr.db.BancoDeDados;
import br.com.brunoaguiar.geradoreleitordeqr.modelo.Professor;

public class ProfessorController{

    private SQLiteDatabase instanciaDb;
    private BancoDeDados db;

    public ProfessorController(Context context) {
        this.db = new BancoDeDados(context);
    }

    public long create(final Professor professor) {
        ContentValues dados = new ContentValues();
        long resultado;

        instanciaDb = db.getWritableDatabase();
        dados.put("nome", professor.getNome());
        dados.put("email", professor.getEmail());
        dados.put("login", professor.getLogin());
        dados.put("senha", professor.getSenha());

        resultado = instanciaDb.insert("professor", null, dados);
        instanciaDb.close();
        return resultado;
    }
    public Cursor retrieve() {
        String[] campos = {"_id", "nome", "email", "login", "senha"};
        instanciaDb = db.getReadableDatabase();

        Cursor cursor = instanciaDb.query("professor", campos, null, null, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }

        instanciaDb.close();
        return cursor;
    }
}*/
