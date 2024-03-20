package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.db.DbContactos;
import com.example.myapplication.entidades.Contactos;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class VerActivity2 extends AppCompatActivity {

    EditText txtNombre,txtTelefono,txtCorreo;
    Button btnGuarda;

    FloatingActionButton fbEditar,fbEliminar;

    Contactos contactos;
    int id = 0;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_ver2);

        txtNombre = findViewById(R.id.txtNombre);
        txtTelefono = findViewById(R.id.txttelefono);
        txtCorreo = findViewById(R.id.txtcorreo);
        btnGuarda = findViewById(R.id.btnguardar);
        fbEditar = findViewById(R.id.fabEditar);
        fbEliminar = findViewById(R.id.fabEliminar);

        if (savedInstanceState == null ){
            Bundle extras = getIntent().getExtras();
            if(extras == null){
                id = Integer.parseInt(null);
            }else {
                id = extras.getInt("ID");
            }
        } else {
            id = (int)  savedInstanceState.getSerializable("ID");
        }
        DbContactos dbContactos = new DbContactos(VerActivity2.this);
        contactos = dbContactos.verContacto(id);

        if(contactos != null){
            txtNombre.setText(contactos.getNombre());
            txtTelefono.setText(contactos.getTelefono());
            txtCorreo.setText(contactos.getCorreo_electronico());
            btnGuarda.setVisibility(View.INVISIBLE);
            txtNombre.setInputType(InputType.TYPE_NULL);
            txtTelefono.setInputType(InputType.TYPE_NULL);
            txtCorreo.setInputType(InputType.TYPE_NULL);
        }
       fbEditar.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent = new Intent(VerActivity2.this,EdictarActivity.class);
               intent.putExtra("ID", id);
               startActivity(intent);
           }
       });

        fbEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(VerActivity2.this);
                builder.setMessage("¿Desea eliminar este coctacto?")
                        .setPositiveButton("SI", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if(dbContactos.EliminarContacto(id)){
                                    lista();
                                }
                            }
                        })
                        .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        }).show();
            }
        });
    }
    private  void lista(){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}