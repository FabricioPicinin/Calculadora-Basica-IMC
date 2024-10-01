package com.example.calculadorabasicaeimc;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ImcActivity extends AppCompatActivity {

    private EditText editPeso;
    private EditText editAltura;
    private TextView textResultadoImc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.imc_main);

        editPeso = findViewById(R.id.editPeso);
        editAltura = findViewById(R.id.editAltura);
        textResultadoImc = findViewById(R.id.textResultado);

        Button btnCalcular = findViewById(R.id.buttonCalcular);
        Button btBackToCalculator = findViewById(R.id.btBackToCalculator);

        btnCalcular.setOnClickListener(v -> calcularImc());
        btBackToCalculator.setOnClickListener(v -> {
            Intent intent = new Intent(ImcActivity.this, MainActivity.class);
            startActivity(intent);
        });
    }

    private void calcularImc() {
        try {
            double peso = Double.parseDouble(editPeso.getText().toString());
            double altura = Double.parseDouble(editAltura.getText().toString());

            if (altura == 0) {
                textResultadoImc.setText("Altura não pode ser zero.");
                return;
            }

            double resultado = peso / (altura * altura);

            if (resultado < 19) {
                textResultadoImc.setText("Abaixo do Peso");
            } else if (resultado >= 19 && resultado < 25) {
                textResultadoImc.setText("Peso normal");
            } else if (resultado >= 25 && resultado < 30) {
                textResultadoImc.setText("Sobrepeso");
            } else if (resultado >= 30 && resultado < 40) {
                textResultadoImc.setText("Obesidade Grau 1");
            } else {
                textResultadoImc.setText("Obesidade Grau 2");
            }

        } catch (NumberFormatException e) {
            textResultadoImc.setText("Por favor, insira valores válidos.");
        }
    }
}
