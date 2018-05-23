/*package br.com.brunoaguiar.geradoreleitordeqr.controller;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import br.com.brunoaguiar.geradoreleitordeqr.db.BancoDeDados;
import br.com.brunoaguiar.geradoreleitordeqr.modelo.Aluno;

public class AlunoController {

    private SQLiteDatabase instanciaDb;
    private BancoDeDados db;

    public AlunoController(Context context) {
        this.db = new BancoDeDados(context);
    }

    public long create(final Aluno aluno) {
        ContentValues dados = new ContentValues();
        long resultado;

        instanciaDb = db.getWritableDatabase();
        dados.put("matricula", aluno.getMatricula());
        dados.put("nome", aluno.getNome());
        dados.put("idade", aluno.getIdade());
        dados.put("sexo", aluno.getSexo());
        dados.put("turma", aluno.getTurma());
        dados.put("telefone", aluno.getTelefone());
        dados.put("pai", aluno.getPai());
        dados.put("mae", aluno.getMae());
        dados.put("usuario", aluno.getUsuario());
        dados.put("senha", aluno.getSenha());

        resultado = instanciaDb.insert("aluno", null, dados);
        instanciaDb.close();
        return resultado;
    }
    public Aluno getById(int id) {

        String[] campos = {"_id", "matricula", "nome", "idade", "sexo", "turma", "telefone", "pai", "mae", "usuario", "senha"};
        String Where = "_id = " + id;
        instanciaDb = db.getReadableDatabase();

        Cursor cursor = instanciaDb.query("aluno", campos, Where, null, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }

        instanciaDb.close();
        Aluno aluno = new Aluno();
        aluno.setId(cursor.getInt(cursor.getColumnIndexOrThrow("_id")));
        aluno.setMatricula(cursor.getColumnIndexOrThrow("matricula"));
        aluno.setNome(cursor.getString(cursor.getColumnIndexOrThrow("nome")));
        aluno.setIdade(cursor.getInt(cursor.getColumnIndexOrThrow("idade")));
        aluno.setSexo(cursor.getString(cursor.getColumnIndexOrThrow("sexo")));
        aluno.setTurma(cursor.getInt(cursor.getColumnIndexOrThrow("turma")));
        aluno.setTelefone(cursor.getInt(cursor.getColumnIndexOrThrow("telefone")));
        aluno.setPai(cursor.getString(cursor.getColumnIndexOrThrow("pai")));
        aluno.setMae(cursor.getString(cursor.getColumnIndexOrThrow("mae")));
        aluno.setUsuario(cursor.getString(cursor.getColumnIndexOrThrow("usuario")));
        aluno.setSenha(cursor.getString(cursor.getColumnIndexOrThrow("senha")));

        return aluno;
    }


    public Cursor retrieve() {
        String[] campos = {"_id", "matricula", "nome", "idade", "sexo", "turma", "telefone", "pai", "mae", "usuario", "senha"};
        instanciaDb = db.getReadableDatabase();

        Cursor cursor = instanciaDb.query("aluno", campos, null, null, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }

        instanciaDb.close();
        return cursor;
    }

    public Aluno dados(String usuario){

        String[] campos = {"_id", "matricula", "nome", "idade", "sexo", "turma", "telefone", "pai", "mae", "usuario", "senha"};
        String Where = "usuario = '" + usuario + "'";
        instanciaDb = db.getReadableDatabase();

        Cursor cursor = instanciaDb.query("aluno", campos, Where, null, null, null, null);

        if(cursor != null){
            cursor.moveToFirst();
        }
        instanciaDb.close();

        Aluno aluno = new Aluno();
        aluno.setId(cursor.getInt(cursor.getColumnIndexOrThrow("_id")));
        aluno.setMatricula(cursor.getColumnIndexOrThrow("matricula"));
        aluno.setNome(cursor.getString(cursor.getColumnIndexOrThrow("nome")));
        aluno.setIdade(cursor.getInt(cursor.getColumnIndexOrThrow("idade")));
        aluno.setSexo(cursor.getString(cursor.getColumnIndexOrThrow("sexo")));
        aluno.setTurma(cursor.getInt(cursor.getColumnIndexOrThrow("turma")));
        aluno.setTelefone(cursor.getInt(cursor.getColumnIndexOrThrow("telefone")));
        aluno.setPai(cursor.getString(cursor.getColumnIndexOrThrow("pai")));
        aluno.setMae(cursor.getString(cursor.getColumnIndexOrThrow("mae")));
        aluno.setUsuario(cursor.getString(cursor.getColumnIndexOrThrow("usuario")));
        aluno.setSenha(cursor.getString(cursor.getColumnIndexOrThrow("senha")));

        return aluno;
    }

}*/
