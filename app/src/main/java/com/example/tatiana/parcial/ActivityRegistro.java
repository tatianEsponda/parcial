package com.example.tatiana.parcial;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;

import static com.example.tatiana.parcial.R.id.Textnombre;

public class ActivityRegistro extends AppCompatActivity {
    RadioButton si,no;
    String ban= "Estudiante";
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();
    DatabaseReference usuario = FirebaseDatabase.getInstance().getReference().child("Usuario");
    private DatabaseReference dbUsuario;// 3 forma
    private ValueEventListener eventListener;
    //dbUsuario=FirebaseDatabase..getInstance().getReference().child("Usuario");

    EditText Ident,nomb,apell,email,contr;
    String not="0";
    Button volver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        si = (RadioButton)findViewById(R.id.RadioSi);
        no = (RadioButton)findViewById(R.id.RadioNo);
        Ident=(EditText)findViewById(R.id.Textiden);
        nomb=(EditText)findViewById(Textnombre);
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
        volver = (Button) findViewById(R.id.buttonvolv);

        volver.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent volver= new Intent(ActivityRegistro.this,MainActivity.class);
                startActivity(volver);
            }
        });

    }
    public void Ingresar(View b){
        String id=Ident.getText().toString().trim();
        String nom=nomb.getText().toString().trim();
        String ap=apell.getText().toString().trim();
        String ema=email.getText().toString().trim();
        String contra=contr.getText().toString().trim();
        Usuario usu = new Usuario(id,nom,ap,ema,ban,contra,not);
        if(id.matches("")||nom.matches("")||ap.matches("")||ema.matches("")||contra.matches("")){
            Toast.makeText(this,"Todos los campos son obligatorios",Toast.LENGTH_LONG).show();
        }else{
            myRef.child("Usuario").child(id).setValue(usu);
        }

        Ident.setText("");
        nomb.setText("");
        apell.setText("");
        email.setText("");
        contr.setText("");
    }

    public void LeerFirebase(View g){

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Map<String,Object> value =(Map<String,Object>) dataSnapshot.getValue();
                Ident.setText(value.size()+" "+value.containsKey("chats"));
                Ident.append("  "+value.values());


            }

            @Override
            public void onCancelled(DatabaseError error) {
                Ident.setText(""+error.toException());
            }
        });
    }


    //Forma 2 de hacer el json
   /* public void leer(View d){

        DatabaseReference usuario = FirebaseDatabase.getInstance().getReference().child("Usuario").child("9");
        usuario.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //String nombre= (String) dataSnapshot.getValue();
                Ident.setText(dataSnapshot.child("Nombre").getValue().toString());

            }

            @Override
            public void onCancelled(DatabaseError error) {
                Ident.setText(""+error.toException());
            }

        });
        Toast.makeText(this,"entro",Toast.LENGTH_LONG).show();
    }*/

/*  forma 1 de hacer el json
    public void leer(){

        DatabaseReference usuario = FirebaseDatabase.getInstance().getReference().child("Usuario").child("nombre");
        usuario.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String nombre= (String) dataSnapshot.getValue();
                nomb.setText(nombre);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                {
                    nomb.setText(""+error.toException());
                }
            });
        }

    }*/
}

