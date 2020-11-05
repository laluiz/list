package com.example.list.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.list.database.contract.LocalContract;
import com.example.list.database.contract.ProdutoContract;

public class DatabaseDBHelper extends SQLiteOpenHelper {



    private static final String DATABASE_NAME = "db.produto";
    private static final int DATABASE_VERSION = 6;

    public DatabaseDBHelper(@Nullable Context context ) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(LocalContract.criarTabela());
        db.execSQL(ProdutoContract.criarTabela());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(ProdutoContract.removerTabela());
        db.execSQL(LocalContract.removerTabela());
        db.execSQL(ProdutoContract.criarTabela());
        db.execSQL(LocalContract.criarTabela());

    }
}
