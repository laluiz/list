package com.example.list;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.list.database.LocalDAO;
import com.example.list.database.ProdutoDAO;
import com.example.list.modelo.Evento;
import com.example.list.modelo.Local;

public class cadastro extends AppCompatActivity {

    private int id = 0;
    private Spinner spinnerLocais;
    private ArrayAdapter<Local> localAdapter;
    private EditText etnome;
    private EditText etdata;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        setTitle("Cadastro evento");
        spinnerLocais = findViewById(R.id.spinner_locais);
        etnome = findViewById(R.id.ET_nome);
        etdata = findViewById(R.id.ET_data);
        carregarLocais();
        carregarEvento();
    }

    private void carregarLocais(){
        LocalDAO localDAO = new LocalDAO(getBaseContext());
        localAdapter = new ArrayAdapter<Local>(cadastro.this, android.R.layout.simple_spinner_item, localDAO.listar());
        spinnerLocais.setAdapter(localAdapter);

    }

    private void carregarEvento(){
        Intent intent = getIntent();
        if (intent != null && intent.getExtras() != null && intent.getExtras().get("eventoEdicao") != null){
            Evento evento = (Evento) intent.getExtras().get("eventoEdicao");
            etnome.setText(evento.getNomeEvento());
            etdata.setText(evento.getDataEvento());
            int posicaoLocal = obterPosicaoLocal(evento.getLocal());
            spinnerLocais.setSelection(posicaoLocal);
            id = evento.getId();
        }
    }

    private int obterPosicaoLocal(Local local){
        for (int posicao = 0; posicao < localAdapter.getCount(); posicao++){
            if (localAdapter.getItem(posicao).getId() == local.getId()){
                return posicao;
            }
        }
        return 0;
    }

    public void onClickVoltar(View v){
        finish();
    }

    public void onClicSalvar(View v){
        String nome = etnome.getText().toString();
        String data = String.valueOf(etdata.getText());
        //Local local = (Local) spinnerLocais.getSelectedItem();
        int posicaoLocal = spinnerLocais.getSelectedItemPosition();
        Local local = (Local) localAdapter.getItem(posicaoLocal);
        Evento evento = new Evento(id, nome, data, local);
        ProdutoDAO produtoDAO = new ProdutoDAO(getBaseContext());
        boolean salvou = produtoDAO.salvar(evento);
        if (salvou){
            finish();
        }else{
            Toast.makeText(cadastro.this, "erro ao salvar", Toast.LENGTH_LONG);
        }
        }
    }
