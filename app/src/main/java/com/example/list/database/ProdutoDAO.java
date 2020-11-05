package com.example.list.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.list.database.DBGateway;
import com.example.list.database.entity.LocalEntity;
import com.example.list.database.entity.ProdutoEntity;
import com.example.list.modelo.Evento;
import com.example.list.modelo.Local;

import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {

    private final String SQL_LISTAR_TODOS = "SELECT Eventos._id, Eventos.Nome as nomeEvento, Eventos.Id_Local, Eventos.Data, Local.Nome, Local.Cidade, Local.Bairro, Local.Capacidade FROM " + ProdutoEntity.TABLE_NAME +
            " INNER JOIN " + LocalEntity.TABLE_NAME + " ON " + ProdutoEntity.COLUMN_NAME_ID_LOCAL + " = " +LocalEntity.TABLE_NAME + "." + LocalEntity._ID;
    private DBGateway dbGateway;

    public ProdutoDAO(Context context){
        dbGateway = DBGateway.getInstance(context);
    }

    public boolean salvar(Evento evento){
        ContentValues contentValues = new ContentValues();
        contentValues.put(ProdutoEntity.COLUMN_NAME_NOME, evento.getNomeEvento());
        contentValues.put(ProdutoEntity.COLUMN_NAME_DATA, evento.getDataEvento());
        contentValues.put(ProdutoEntity.COLUMN_NAME_ID_LOCAL, evento.getLocal().getId());

        if (evento.getId() > 0 ){
            return dbGateway.getDatabase().update(ProdutoEntity.TABLE_NAME,
                    contentValues,
                    ProdutoEntity._ID + "=?",
                    new String[]{String.valueOf(evento.getId())}) > 0;
        }
        return dbGateway.getDatabase().insert(ProdutoEntity.TABLE_NAME, null, contentValues) > 0;
    }

     public List<Evento> listar(){
        List<Evento> eventos = new ArrayList<Evento>();
         Cursor cursor = dbGateway.getDatabase().rawQuery(SQL_LISTAR_TODOS, null);
         while (cursor.moveToNext()){
             int id = cursor.getInt(cursor.getColumnIndex(ProdutoEntity._ID));
             String nome = cursor.getString(cursor.getColumnIndex("nomeEvento"));
             String data = cursor.getString(cursor.getColumnIndex(ProdutoEntity.COLUMN_NAME_DATA));
             int idcategoria = cursor.getInt(cursor.getColumnIndex(ProdutoEntity.COLUMN_NAME_ID_LOCAL));
             String nomecategoria = cursor.getString(cursor.getColumnIndex(LocalEntity.COLUMN_NAME_NOME));
             String bairro = cursor.getString(cursor.getColumnIndex(LocalEntity.COLUMN_NAME_BAIRRO));
             String cidade = cursor.getString(cursor.getColumnIndex(LocalEntity.COLUMN_NAME_CIDADE));
             int capacidade = cursor.getInt(cursor.getColumnIndex(LocalEntity.COLUMN_NAME_CAPACIDADE));
             Local local = new Local(idcategoria, nomecategoria, bairro, cidade, capacidade);
             eventos.add(new Evento(id, nome, data, local));
         }
         cursor.close();
         return eventos;
     }
     public int ExcluirProduto(Evento evento){
         //ContentValues contentValues = new ContentValues();
         //contentValues.put(ProdutoEntity.COLUMN_NAME_NOME, produto.getNome());
         //contentValues.put(ProdutoEntity.COLUMN_NAME_VALOR, produto.getValor());
         int result = dbGateway.getDatabase().delete(ProdutoEntity.TABLE_NAME, ProdutoEntity._ID + "=?", new String[]{String.valueOf(evento.getId())});
         return result;
     }
}
