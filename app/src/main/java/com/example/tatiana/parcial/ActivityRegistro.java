package com.example.tatiana.parcial;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ActivityRegistro extends AppCompatActivity {
    RadioButton si,no;
    String ban= "Estudiante";
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();

    EditText Ident,nomb,apell,email,contr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        si = (RadioButton)findViewById(R.id.RadioSi);
        no = (RadioButton)findViewById(R.id.RadioNo);
        Ident=(EditText)findViewById(R.id.Textiden);
        nomb=(EditText)findViewById(R.id.Textnombre);
        apell=(EditText)findViewById(R.id.TextApellido);
        email=(EditText)findViewById(R.id.TextEmail);
        contr=(EditText)findViewById(R.id.Textcontra);

       si.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
                ban="Administrador";

           }
       });
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 ban="Estudiandte";
            }
        });

    }
    public void Ingresar(View b){
        String id=Ident.getText().toString().trim();
        String nom=nomb.getText().toString().trim();
        String ap=apell.getText().toString().trim();
        String ema=email.getText().toString().trim();
        String contra=contr.getText().toString().trim();
        Usuario usu = new Usuario(id,nom,ap,ema,ban,contra);
        if(id.matches("")||nom.matches("")||ap.matches("")||ema.matches("")||contra.matches("")){
            Toast.makeText(this,"Todos los campos son obligatorios",Toast.LENGTH_LONG).show();
        }else{
            myRef.child("Usuario").child(id).setValue(usu);
        }


    }
}
