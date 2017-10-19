package com.example.tatiana.parcial;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    Button btnRegistrar,btnIngresar;

    FirebaseDatabase database = FirebaseDatabase.getInstance();//obtengo una instancia luego de conectarse a firebase
    //para leer y escribir en la base de datos, necesitas una instancia de Database
    DatabaseReference myRef = database.getReference();
    EditText identi, contraseña;

    String Ide,contr,contrBD="";
    String tip="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        identi = (EditText) findViewById(R.id.txIden);// obtenemos la identificacion para compararla con la BD
        contraseña = (EditText) findViewById(R.id.txtContraseña); // obtenemos la contraseña para compararla con la BD
        btnRegistrar = (Button) findViewById(R.id.butregis);
        btnIngresar=(Button) findViewById(R.id.butingre);




        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, ActivityRegistro.class);
                startActivity(intent);
            }
        });

      /* btnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inte = new Intent(MainActivity.this,ActivityLista.class);
                startActivity(inte);
            }
        });*/
    }


    public void Verificar(View b){
        Ide = identi.getText().toString().trim();// lo que hay en el campo texto  identi lo guardamos en un string Ide
        contr=contraseña.getText().toString().trim();//lo que hay en  el compo texto contraseña lo guardamos en string contr
        FirebaseDatabase data = FirebaseDatabase.getInstance();
        DatabaseReference myRef = data.getReference("Usuario").child(Ide);// vamos la USUARIO  y a la identificacion ingresada
       myRef.addValueEventListener(new ValueEventListener() {
           @Override
           public void onDataChange(DataSnapshot dataSnapshot) {
                contrBD = dataSnapshot.child("Contraseña").getValue().toString();/*una vez estamos dentro de los atributos del usuario con la identificacion
                                                                                        ingresada nos vamos al atributo Contraseña y la guardamos en contrBD para luego
                                                                                        compararla con la que se ingreso que seta en Ide*/
                tip=dataSnapshot.child("Tipo").getValue().toString();

           }

           @Override
           public void onCancelled(DatabaseError error) {
               identi.setText(""+error.toException());
           }
       });
        if (contr.matches(contrBD)){
            switch (tip){
                case "Administrador":

                    Toast.makeText(this, "Si es igual administrador", Toast.LENGTH_LONG).show();
                    Intent inte = new Intent(MainActivity.this,ActivityListaad.class);    // llamo a la activity  lista administrador
                    startActivity(inte);

                    break;
                case "Estudiante":

                    Toast.makeText(this, "Si es igual", Toast.LENGTH_LONG).show();
                    Intent ite = new Intent(MainActivity.this,ActivityLista.class);    // llamo a la activity  lista estudiante
                    startActivity(ite);

                    break;
            }

        }else {
            Toast.makeText(this, "Identificacion o contraseña incorrecta", Toast.LENGTH_SHORT).show();

        }


    }

   /* public void Excribir(View b) {
        // Write a message to the database
        String nom = identi.getText().toString().trim();// trae lo que hay en nombre edinomb
        String con = contraseña.getText().toString().trim();

        if (nom.matches("") || con.matches("")) {
            Toast.makeText(this, "Debe escribir algo para guardar", Toast.LENGTH_LONG).show();
        } else {
            myRef.child("Usuario").child(nom).setValue(nom);
            myRef.child("Usuario").child(con).setValue(con);

            //   myRef.child("Usuario").setValue(valor);

        }
    }*/
}