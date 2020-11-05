
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

import com.example.list.database.LocalDAO;
import com.example.list.database.ProdutoDAO;
import com.example.list.modelo.Evento;
import com.example.list.modelo.Local;

public class Listar_Locais extends AppCompatActivity {

    private ArrayAdapter<Local> adapterlocais;
    private ListView lista_Locais;
    private int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar__locais);
        setTitle("Locais");
        lista_Locais = findViewById(R.id.lista_locais);
        onClickItemList();
        onLongClickItemListener();
    }

    @Override
    protected void onResume() {
        super.onResume();
        LocalDAO localDAO = new LocalDAO(getBaseContext());
        adapterlocais = new ArrayAdapter<Local>(Listar_Locais.this,
                android.R.layout.simple_list_item_1,
                localDAO.listar());
        lista_Locais.setAdapter(adapterlocais);
    }

    private void onClickItemList(){
        lista_Locais.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Local localClicado = adapterlocais.getItem(position);
                Intent intent = new Intent(Listar_Locais.this, cadastro_local.class);
                intent.putExtra("localEdicao", localClicado);
                startActivity(intent);
            }
        });
    }

    private void onLongClickItemListener(){
        lista_Locais.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                final Local localClicado = adapterlocais.getItem(position);
                new AlertDialog.Builder(Listar_Locais.this)
                        .setIcon((android.R.drawable.ic_delete))
                        .setTitle("Deseja realmente excluir o local?")
                        .setMessage("deseja excluir este local ?")
                        .setPositiveButton("sim", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                LocalDAO localDAO = new LocalDAO(getBaseContext());
                                localDAO.ExcluirProduto(localClicado);
                                recreate();
                            }
                        })
                        .setNegativeButton("no", null).show();
                return false;
            }
        });
    }

    public void onClickNovoProduto(View v){
        Intent intent = new Intent(Listar_Locais.this, cadastro_local.class);
        startActivity(intent);
    }

    public void onClickVoltar(View v){
        finish();
    }
}