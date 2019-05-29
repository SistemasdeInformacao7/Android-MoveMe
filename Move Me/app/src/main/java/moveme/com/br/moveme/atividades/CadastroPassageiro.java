package moveme.com.br.moveme.atividades;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

import moveme.com.br.moveme.R;
import moveme.com.br.moveme.conexao.webservices.HttpServicePassageiro;
import moveme.com.br.moveme.modelos.Passageiro;

public class CadastroPassageiro extends AppCompatActivity {

    private EditText nome, sobrenome, cpf, email, telefone, senha;
    private Button btnCadastrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_passageiro);

        nome = (EditText) findViewById(R.id.edtNome);
        sobrenome = (EditText) findViewById(R.id.edtSobrenome);
        cpf = (EditText) findViewById(R.id.edtCPF);
        email = (EditText) findViewById(R.id.edtEmail);
        telefone = (EditText) findViewById(R.id.edtTelefone);
        senha = (EditText) findViewById(R.id.edtSenha);
    }

    //Metodo para cadastrar o passageiro
    public void cadastrarPassgairo(View v){

        //Pega os valores dos EditText
        String nomePassageiro = nome.getText().toString();
        String sobrenomePassageiro = sobrenome.getText().toString();
        String cpfPassageiro = cpf.getText().toString();
        String telefonePassageiro = telefone.getText().toString();
        String emailPassageiro = email.getText().toString();
        String senhaPassageiro = senha.getText().toString();

        //Cria um objeto passageiro
        Passageiro passageiro = new Passageiro();
        passageiro.setNome(nomePassageiro);
        passageiro.setSobrenome(sobrenomePassageiro);
        passageiro.setCpf(cpfPassageiro);
        passageiro.setEmail(emailPassageiro);
        passageiro.setTelefone(telefonePassageiro);
        passageiro.setSenha(senhaPassageiro);

        //Converte o objeto Passageiro para Json
        Gson gson = new Gson();
        String jsonUsuario = gson.toJson(passageiro);

        String r = "";

        try {
            //Conecta com web service e passa o Json para ser tratado
            //HttpServicePassageiro - classe que cria um thread para acessar o web service
            Passageiro retorno = new HttpServicePassageiro(jsonUsuario).execute().get();
            r = retorno.toString();

            //Imprimi o saída do web service
            System.out.println("Objeto retornado pelo web service: " + r.toString());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}