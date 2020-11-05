package com.example.list.database.entity;

import android.provider.BaseColumns;

public final class ProdutoEntity implements BaseColumns {

    private ProdutoEntity() {}

    public static final String TABLE_NAME = "Eventos";
    public static final String COLUMN_NAME_NOME = "Nome";
    public static final String COLUMN_NAME_DATA = "Data";
    public static final String COLUMN_NAME_ID_LOCAL = "Id_Local";
}
