package com.example.tatiana.parcial;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ActivityListaad extends AppCompatActivity {
    EditText Nomb,ape,iden;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);
        Nomb=(EditText)findViewById(R.id.ediNomb);
        ape=(EditText)findViewById(R.id.ediape);
        iden=(EditText) findViewById(R.id.edidato);// obtenemos lo que hay en texto identificacion

    }



    public void leer(View b){
       String dato= iden.getText().toString().trim();  // lo que tenemos en  iden lo guardamos en dato es decir la identificacino
        FirebaseDatabase data =FirebaseDatabase.getInstance();
        DatabaseReference myRef=data.getReference("Usuario").child(dato); //-Nos vamos a la referencia  usuario y la identificacion que  ingresamos

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) { // con ondata se muestra el resultado en casa de que es haga un cambio el automatimante nos lo envia modificado
             Nomb.setText(dataSnapshot.child("Nombre").getValue().toString()); // en el texto nombre agregamos lo que haya en nombre de la identificacion ingresada
             ape.setText(dataSnapshot.child("Apellido").getValue().toString());// igual pero con el apellido

            }

            @Override
            public void onCancelled(DatabaseError error) {
                Nomb.setText(""+error.toException());
            }// en caso de  que haya un error  se muestra en el campo nombre
        });
    }

}