package com.example.list.database.contract;

import com.example.list.database.entity.LocalEntity;
import com.example.list.database.entity.ProdutoEntity;

public class ProdutoContract {

    private ProdutoContract(){}

    public static final String criarTabela(){
        return ("CREATE TABLE " + ProdutoEntity.TABLE_NAME) + " (" +
                ProdutoEntity._ID + " INTEGER PRIMARY KEY," +
                ProdutoEntity.COLUMN_NAME_NOME + " TEXT," +
                ProdutoEntity.COLUMN_NAME_DATA + " TEXT," +
                ProdutoEntity.COLUMN_NAME_ID_LOCAL + " INTEGER," +
                "FOREIGN KEY (" + ProdutoEntity.COLUMN_NAME_ID_LOCAL + ") REFERENCES " +
                LocalEntity.TABLE_NAME + " (" + LocalEntity._ID + "))";
    }

    public static final String removerTabela(){
        return "DROP TABLE IF EXISTS " + ProdutoEntity.TABLE_NAME;
    }
}
