package com.example.tatiana.parcial;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    Button btnRegistrar,btnIngresar;

    FirebaseDatabase database = FirebaseDatabase.getInstance();//obtengo una instancia luego de conectarse a firebase
    //para leer y escribir en la base de datos, necesitas una instancia de Database
    DatabaseReference myRef = database.getReference();
    EditText nomUsuario, contraseña;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nomUsuario = (EditText) findViewById(R.id.txtNom);
        contraseña = (EditText) findViewById(R.id.txtContraseña);
        btnRegistrar = (Button) findViewById(R.id.butregis);
        btnIngresar=(Button) findViewById(R.id.butingre);


        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ActivityRegistro.class);
                startActivity(intent);
            }
        });

        btnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inte = new Intent(MainActivity.this,ActivityLista.class);
                startActivity(inte);
            }
        });
    }

    public void Excribir(View b) {
        // Write a message to the database
        String nom = nomUsuario.getText().toString().trim();// trae lo que hay en nombre edinomb
        String con = contraseña.getText().toString().trim();

        if (nom.matches("") || con.matches("")) {
            Toast.makeText(this, "Debe escribir algo para guardar", Toast.LENGTH_LONG).show();
        } else {
            myRef.child("Usuario").child(nom).setValue(nom);
            myRef.child("Usuario").child(con).setValue(con);

            //   myRef.child("Usuario").setValue(valor);

        }
    }
}