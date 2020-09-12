package com.example.rezguimarwanelab1;

import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    TextView tv_valider = null;
    EditText et_password = null;
    CheckBox cb_showPassword = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_valider = (TextView) findViewById(R.id.textViewMessageValidation);
        et_password = (EditText) findViewById(R.id.editTextTextPassword);
        cb_showPassword = (CheckBox) findViewById(R.id.checkBoxAfficherMotDePasse);
    }

    public void onClick_checkBoxAfficherMotDePasse(View view) {
        //activer/desactiver vision mot de passe
        if (cb_showPassword.isChecked()) {
            et_password.setTransformationMethod(null);
        } else {
            et_password.setTransformationMethod(new PasswordTransformationMethod());
        }
    }

    public void onClick_buttonValiderMotDePasse(View view) {
        String password = et_password.getText().toString();

        if (siPasswordValide(password)) {
            tv_valider.setText("Le mot de passe est valide");
            tv_valider.setTextColor(getResources().getColor(R.color.green));
        } else {
            tv_valider.setText("Le mot de passe n'est pas valide");
            tv_valider.setTextColor(getResources().getColor(R.color.red));
        }
    }

    private boolean siPasswordValide(String password) {
        //verifier le mot de passe : min 10 car, une lettre maj, min, un chiffre, un car special.
        Pattern patternTenMin = Pattern.compile(
                "^.{10,}$"
        );//10 min et seulement caracteres demandes
        Pattern patternlettreMaj = Pattern.compile(
                "(.*[A-Z].*)"
        );//letter maj 1
        Pattern patternlettreMin = Pattern.compile(
                "(.*[a-z].*)"
        );//letter min 1
        Pattern patternChiffre = Pattern.compile(
                "(.*[0-9].*)"
        );//chiffre 1
        Pattern patternCar = Pattern.compile(
                "(.*[@#$%&(){}\\]\\[_=<>+\\-!?*/|:;,.'\"~^].*)"
        );//1 car special
        Pattern patternPassword = Pattern.compile(
                "^.{10,}$" +
                        "(.*[A-Z].*)" +
                        "(.*[@#$%&(){}\\]\\[_=<>+\\-!?*/|:;,.'\"~^].*)+" +
                        "(.*[a-z].*)" +
                        "(.*[0-9].*)"

        );

        return patternTenMin.matcher(password).matches()
                && patternlettreMaj.matcher(password).matches()
                && patternlettreMin.matcher(password).matches()
                && patternChiffre.matcher(password).matches()
                && patternCar.matcher(password).matches();
    }
}