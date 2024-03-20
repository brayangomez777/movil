package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.db.DbContactos;

public class InsertarActivity extends AppCompatActivity {

    EditText txtNomre,txttelefono,txtcorreo;
    Button btnguardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insertar);

        txtNomre = findViewById(R.id.txtNombre);
        txttelefono = findViewById(R.id.txttelefono);
        txtcorreo = findViewById(R.id.txtcorreo);
        btnguardar = findViewById(R.id.btnguardar);

        btnguardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DbContactos dbContactos = new DbContactos(InsertarActivity.this);
                long id =dbContactos.insertarContacto(txtNomre.getText().toString(),txttelefono.getText().toString(),txtcorreo.getText().toString());
                
                if (id > 0){
                    Toast.makeText(InsertarActivity.this, "REGISTRO GUARDADO", Toast.LENGTH_LONG).show();
                    limpiar ();
                } else {
                    Toast.makeText(InsertarActivity.this, "ERROR GUARDADR REGISTRO", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void limpiar (){
        txtNomre.setText("");
        txttelefono.setText("");
        txtcorreo.setText("");
    }


}