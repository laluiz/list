package com.example.list;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.list.database.ProdutoDAO;
import com.example.list.modelo.Evento;

public class MainActivity extends AppCompatActivity {

    private ArrayAdapter<Evento> adaptereventos;
    private ListView lista_eventos;
    private int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Produtos");
        lista_eventos = findViewById(R.id.lista_produtos);
        onClickItemList();
        onLongClickItemListener();
    }

    @Override
    protected void onResume() {
        super.onResume();
        ProdutoDAO produtoDAO = new ProdutoDAO(getBaseContext());
        adaptereventos = new ArrayAdapter<Evento>(MainActivity.this,
                android.R.layout.simple_list_item_1,
                produtoDAO.listar());
        lista_eventos.setAdapter(adaptereventos);
    }

    private void onClickItemList(){
        lista_eventos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Evento eventoClicado = adaptereventos.getItem(position);
                Intent intent = new Intent(MainActivity.this, cadastro.class);
                intent.putExtra("eventoEdicao", eventoClicado);
                startActivity(intent);
            }
        });
    }

    private void onLongClickItemListener(){
        lista_eventos.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                final Evento eventoClicado = adaptereventos.getItem(position);
                new AlertDialog.Builder(MainActivity.this)
                        .setIcon((android.R.drawable.ic_delete))
                        .setTitle("Deseja realmente excluir o produto?")
                        .setMessage("deseja excluir este item ?")
                        .setPositiveButton("sim", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                ProdutoDAO produtoDAO = new ProdutoDAO(getBaseContext());
                                produtoDAO.ExcluirProduto(eventoClicado);
                                recreate();
                            }
                        })
                        .setNegativeButton("no", null).show();
                return false;
            }
        });
    }

    public void onClickNovoProduto(View v){
        Intent intent = new Intent(MainActivity.this, cadastro.class);
        startActivity(intent);
    }

    public void onClickLocais(View v){
        Intent intent = new Intent(MainActivity.this, Listar_Locais.class);
        startActivity(intent);
    }
}