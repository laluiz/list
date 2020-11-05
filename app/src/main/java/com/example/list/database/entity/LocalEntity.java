package com.example.list.database.entity;

import android.provider.BaseColumns;

public final class LocalEntity implements BaseColumns {

    private LocalEntity() {}

    public static final String TABLE_NAME = "Local";
    public static final String COLUMN_NAME_NOME = "Nome";
    public static final String COLUMN_NAME_BAIRRO = "Bairro";
    public static final String COLUMN_NAME_CIDADE = "Cidade";
    public static final String COLUMN_NAME_CAPACIDADE = "Capacidade";
}
