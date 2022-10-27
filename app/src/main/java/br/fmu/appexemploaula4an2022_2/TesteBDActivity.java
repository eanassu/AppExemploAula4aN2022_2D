package br.fmu.appexemploaula4an2022_2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TesteBDActivity extends AppCompatActivity {
    private EditText editTextRe;
    private EditText editTextNome;
    private EditText editTextDataAdmissao;
    private EditText editTextSalario;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teste_bdactivity);
        editTextRe = findViewById(R.id.editTextRe);
        editTextNome = findViewById(R.id.editTextNome);
        editTextDataAdmissao = findViewById(R.id.editTextDataAdm);
        editTextSalario = findViewById(R.id.editTextSalario);
    }

    public void cadastrar( View view ) {
        AppDatabase db = AppDatabase.getInstance(this);
        FuncionarioDao dao = db.funcionarioDao();
        int re = Integer.parseInt(editTextRe.getText().toString());
        String nome = editTextNome.getText().toString();
        Date dataAdmissao = null;
        try {
            dataAdmissao = dateFormat.parse(editTextDataAdmissao.getText().toString());
        } catch (ParseException e) {
            dataAdmissao = new Date();
        }
        double salario = Double.parseDouble(editTextSalario.getText().toString());
        Funcionario funcionario = new Funcionario(re,nome,dataAdmissao,salario);
        dao.insert(funcionario);
    }

    public void buscarPeloRe(View view) {
        AppDatabase db = AppDatabase.getInstance(this);
        FuncionarioDao dao = db.funcionarioDao();
        int re = Integer.parseInt(editTextRe.getText().toString());
        Funcionario funcionario = dao.buscarPeloRe(re);
        if (funcionario == null) return;
        editTextNome.setText(funcionario.getNome());
        editTextDataAdmissao.setText(dateFormat.format(funcionario.getDataAdmissao()));
        editTextSalario.setText(Double.toString(funcionario.getSalario()));
    }

    public void excluir(View view) {
        AppDatabase db = AppDatabase.getInstance(this);
        FuncionarioDao dao = db.funcionarioDao();
        int re = Integer.parseInt(editTextRe.getText().toString());
        Funcionario funcionario = dao.buscarPeloRe(re);
        dao.delete(funcionario);
    }

    public void alterar(View view) {
        AppDatabase db = AppDatabase.getInstance(this);
        FuncionarioDao dao = db.funcionarioDao();
        int re = Integer.parseInt(editTextRe.getText().toString());
        String nome = editTextNome.getText().toString();
        Date dataAdmissao = null;
        try {
            dataAdmissao = dateFormat.parse(editTextDataAdmissao.getText().toString());
        } catch (ParseException e) {
            dataAdmissao = new Date();
        }
        double salario = Double.parseDouble(editTextSalario.getText().toString());
        Funcionario funcionario = new Funcionario(re,nome,dataAdmissao,salario);
        dao.update(funcionario);
    }
}