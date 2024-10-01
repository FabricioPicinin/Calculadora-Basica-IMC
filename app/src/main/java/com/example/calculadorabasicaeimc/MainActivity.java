package com.example.calculadorabasicaeimc;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.calculadorabasicaeimc.R;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private EditText editValor1;
    private EditText editValor2;
    private TextView textResultado;
    private DecimalFormat decimalFormat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editValor1 = findViewById(R.id.editValor1);
        editValor2 = findViewById(R.id.editValor2);
        textResultado = findViewById(R.id.textResultado);
        decimalFormat = new DecimalFormat("#.#####");

        Button btnSomar = findViewById(R.id.btSomar);
        Button btnSubtrair = findViewById(R.id.btSubtrair);
        Button btnMultiplicar = findViewById(R.id.btMultiplicar);
        Button btnDividir = findViewById(R.id.btDividir);
        Button btGoToImc = findViewById(R.id.btGoToImc);

        btnSomar.setOnClickListener(v -> calcular('+'));
        btnSubtrair.setOnClickListener(v -> calcular('-'));
        btnMultiplicar.setOnClickListener(v -> calcular('*'));
        btnDividir.setOnClickListener(v -> calcular('/'));

        btGoToImc.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ImcActivity.class);
            startActivity(intent);
        });
    }

    private void calcular(char operacao) {
        try {
            double valor1 = Double.parseDouble(editValor1.getText().toString());
            double valor2 = Double.parseDouble(editValor2.getText().toString());
            double resultado = 0;

            switch (operacao) {
                case '+':
                    resultado = valor1 + valor2;
                    break;
                case '-':
                    resultado = valor1 - valor2;
                    break;
                case '*':
                    resultado = valor1 * valor2;
                    break;
                case '/':
                    if (valor2 != 0) {
                        resultado = valor1 / valor2;
                    } else {
                        textResultado.setText("Erro: Divisão por zero");
                        return;
                    }
                    break;
            }

            String resultadoFormatado = decimalFormat.format(resultado);
            textResultado.setText(resultadoFormatado);

        } catch (NumberFormatException e) {
            textResultado.setText("Erro: Entrada inválida");
        }
    }
}
