package com.example.list.database.contract;

import com.example.list.database.entity.LocalEntity;
import com.example.list.database.entity.ProdutoEntity;

public final class LocalContract {

    private LocalContract(){}

    public static final String criarTabela(){
        return ("CREATE TABLE " + LocalEntity.TABLE_NAME) + " (" +
                LocalEntity._ID + " INTEGER PRIMARY KEY," +
                LocalEntity.COLUMN_NAME_NOME + " TEXT," +
                LocalEntity.COLUMN_NAME_BAIRRO + " REAL," +
                LocalEntity.COLUMN_NAME_CIDADE + " TEXT," +
                LocalEntity.COLUMN_NAME_CAPACIDADE + " INTEGER)";
    }

    public static final String removerTabela(){
        return "DROP TABLE IF EXISTS " + LocalEntity.TABLE_NAME;
    }

}
