package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.db.DbContactos;
import com.example.myapplication.entidades.Contactos;

public class EdictarActivity extends AppCompatActivity {

    EditText txtNombre,txtTelefono,txtCorreo;
    Button btnGuarda;
boolean correcto = false;
    Contactos contactos;
    int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_ver2);

        txtNombre = findViewById(R.id.txtNombre);
        txtTelefono = findViewById(R.id.txttelefono);
        txtCorreo = findViewById(R.id.txtcorreo);
        btnGuarda = findViewById(R.id.btnguardar);

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
        DbContactos dbContactos = new DbContactos(EdictarActivity.this);
        contactos = dbContactos.verContacto(id);

        if(contactos != null){
            txtNombre.setText(contactos.getNombre());
            txtTelefono.setText(contactos.getTelefono());
            txtCorreo.setText(contactos.getCorreo_electronico());

        }
        btnGuarda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!txtNombre.getText().toString().equals("") && !txtTelefono.getText().toString().equals("")){
                 correcto = dbContactos.EdictarContacto(id,txtNombre.getText().toString(),txtTelefono.getText().toString(),txtCorreo.getText().toString());
                    if (correcto){
                        Toast.makeText(EdictarActivity.this, "RESGITRO MODIFICADO", Toast.LENGTH_LONG).show();
                        verResgistro();
                    }else {
                        Toast.makeText(EdictarActivity.this, "ERROR AL MODIFICAR RESGITRO ", Toast.LENGTH_LONG).show();
                    }
                }else {
                    Toast.makeText(EdictarActivity.this, "DEBE LLENAR LOS CAMPOS OBLIGATORIOS", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    private  void verResgistro(){
        Intent intent = new Intent(this,VerActivity2.class);
        intent.putExtra("ID", id );
        startActivity(intent);

    }
}