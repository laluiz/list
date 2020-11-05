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

public class cadastro_local extends AppCompatActivity {

    private int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_local);
        setTitle("Cadastro local");
        carregarEvento();
    }

    private void carregarEvento(){
        Intent intent = getIntent();
        if (intent != null && intent.getExtras() != null && intent.getExtras().get("localEdicao") != null){
            Local local = (Local) intent.getExtras().get("localEdicao");
            EditText etnome = findViewById(R.id.ET_nome);
            EditText etbairro = findViewById(R.id.ET_bairro);
            EditText etcidade = findViewById(R.id.ET_cidade);
            EditText etcapacidade = findViewById(R.id.ET_capacidade);
            etnome.setText(local.getNome());
            etbairro.setText(local.getBairro());
            etcidade.setText(local.getCidade());
            etcapacidade.setText(String.valueOf(local.getCapacidadeMaxima()));
            id = local.getId();
        }
    }

    public void onClickVoltar(View v){
        finish();
    }

    public void onClicSalvar(View v){
        EditText etnome = findViewById(R.id.ET_nome);
        EditText etbairro = findViewById(R.id.ET_bairro);
        EditText etcidade = findViewById(R.id.ET_cidade);
        EditText etcapacidade = findViewById(R.id.ET_capacidade);

        String nome = etnome.getText().toString();
        String bairro = etbairro.getText().toString();
        String cidade = etcidade.getText().toString();
        String capacidade = etcapacidade.getText().toString();

        Local local = new Local(id, nome, bairro, cidade, Integer.valueOf(capacidade));
        LocalDAO localDAO = new LocalDAO(getBaseContext());
        boolean salvou = localDAO.salvar(local);
        if (salvou){
            finish();
        }else{
            Toast.makeText(cadastro_local.this, "erro ao salvar", Toast.LENGTH_LONG);
        }
    }

}