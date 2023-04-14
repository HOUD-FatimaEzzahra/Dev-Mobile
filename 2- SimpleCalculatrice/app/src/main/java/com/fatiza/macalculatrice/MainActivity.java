package com.fatiza.macalculatrice;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
public class MainActivity extends AppCompatActivity {
    private int valeur1;
    private int valeur2;
    private String operation;
    private boolean isOp1 = true;
    private TextView mainView,secView;
    Button bc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bc=findViewById(R.id.bc);
        mainView=findViewById(R.id.tvmain);
        secView=findViewById(R.id.tvsec);
        afficherMain();
        afficherSec();


      /*  bc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                valeur1 = 0;
                valeur2 = 0;
                operation = "";
                afficherMain();
                afficherSec();
            }
        });*/
    }
    private void afficherMain() {
        String currentOperand = isOp1 ? String.valueOf(valeur1) : String.valueOf(valeur2);
        mainView.setText(currentOperand);
    }
    private void afficherSec() {
       // String currentOperand = isOp1 ? String.valueOf(valeur1) : String.valueOf(valeur2);
        String currentOperand;
        if(isOp1)
            currentOperand=String.valueOf(valeur1);
          else
            currentOperand= String.valueOf(valeur2);
        secView.setText(currentOperand);
    }
    /*  public void setOperator(View view) {
        Button operatorBtn = (Button) view;
        operation = operatorBtn.getText().toString();
        isOp1 = false;
    }*/
    public void setOperator(View view) {
        if (view instanceof Button) {
            Button operatorBtn = (Button) view;
            operation = operatorBtn.getText().toString();
            isOp1 = false;
        }
    }
    public void ajouterNbr(View view) {
        Button digitBtn = (Button) view;
        int digit = Integer.parseInt(digitBtn.getText().toString());
        if (isOp1) {
            valeur1 = valeur1 * 10 + digit;
        } else {
            valeur2 = valeur2 * 10 + digit;
        }
        afficherSec();
    }
    public void calculer(View view) {
        int result = 0;
        switch (operation) {
            case "+":
                result = valeur1 + valeur2;
                break;
            case "-":
                result = valeur1 - valeur2;
                break;
            case "*":
                result = valeur1 * valeur2;
                break;
            case "/":
                result = valeur1 / valeur2;
                break;
        }
        secView.setText(valeur1+" "+operation+" "+valeur2);
        valeur1 = result;
        valeur2 = 0;
        isOp1 = true;
        afficherMain();
    }
}
