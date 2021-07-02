package com.exemple.pedrapapeltesoura;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private ImageView imgpedra;
    private ImageView imgpapel;
    private ImageView imgtesoura;
    private ImageView imgapp;
    private TextView lblresultado;
    private TextView lbljogador;
    private TextView lblapp;
    private TextView txtescolha;
    private Button bntreset;
    int pontoj=0;
    int pontoa=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // ligar os atributos com os IDs dos objetos
        imgpapel = findViewById(R.id.imgpapel);
        imgpedra = findViewById(R.id.imgpedra);
        imgtesoura = findViewById(R.id.imgtesoura);
        imgapp = findViewById(R.id.imgapp);
        lblresultado= findViewById(R.id.lblresultado);
        lbljogador= findViewById(R.id.lbljogador);
        lblapp= findViewById(R.id.lblapp);
        bntreset= findViewById(R.id.bntreset);
        txtescolha = findViewById(R.id.txtescolha);
        // chamar o objeto
        EscutadorClickiImagem eci = new EscutadorClickiImagem();
        // Associando o objeto criado acima nas imagens:
        imgpedra.setOnClickListener( eci );
        imgpapel.setOnClickListener( eci );
        imgtesoura.setOnClickListener( eci );
        // chamando objeto resetar
        EscutadorBotao e= new EscutadorBotao();
        //associando ao botão
        bntreset.setOnClickListener(e);
    }
    private class EscutadorClickiImagem implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            // guarda a escolha do usario:
            int escolhaUsuario = 0;

            // identifica a imagem que o usuario clicou.

            ImageView img = (ImageView) v;
            switch ( img.getId() ) {
                case R.id.imgpedra:
                    escolhaUsuario = 1;
                    txtescolha.setText("Você escolheu Pedra");
                    break;
                case R.id.imgpapel:
                    escolhaUsuario = 2;
                    txtescolha.setText("Você escolheu Papel");
                    break;
                case R.id.imgtesoura:
                    escolhaUsuario = 3;
                    txtescolha.setText("Você escolheu Tesoura");
                    break;
            }

            int escolhaApp = new Random().nextInt(3) + 1;
            // Precisamos colocar a imagem correta que reflete
            // A escolha do App:
            switch ( escolhaApp ) {
                case 1:
                    imgapp.setImageResource(R.drawable.pedra);
                    break;
                case 2:
                    imgapp.setImageResource(R.drawable.papel);
                    break;
                case 3:
                    imgapp.setImageResource(R.drawable.tesoura);
                    break;
            }
            // encontra o ganhador e informar o resultado
            if ( ( escolhaApp == 1 && escolhaUsuario == 3 ) ||
                    ( escolhaApp == 2 && escolhaUsuario == 1 ) ||
                    ( escolhaApp == 3 && escolhaUsuario == 2 ) )
            {
                lblresultado.setText("O app ganhou!!!!");
                pontoa=pontoa+1;
                lblapp.setText(Integer.toString(pontoa));
            }
            else
            {
                if ( (escolhaApp == 3 && escolhaUsuario == 1) ||
                        (escolhaApp == 1 && escolhaUsuario == 2) ||
                        (escolhaApp == 2 && escolhaUsuario == 3) )
                {
                    lblresultado.setText("Você ganhou!!");
                    pontoj= pontoj+1;
                    lbljogador.setText(Integer.toString(pontoj));
                }
                else
                {
                    lblresultado.setText("Deu empate!");
                }
            }




        }
    }
    private class EscutadorBotao implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            imgapp.setImageResource(R.drawable.vazio);
            lblresultado.setText("");
            pontoa = 0;
            pontoj= 0;
            lbljogador.setText( Integer.toString(pontoj));
            lblapp.setText( Integer.toString(pontoa));
        }
    }
}