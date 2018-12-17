package com.example.a209.practicafinal;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {

    EditText txtCorreo, txtContraseña, txtNombre;
    Button btnIniciar, btnGuardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        txtCorreo=findViewById(R.id.txtCorreo);
        txtNombre=findViewById(R.id.txtNombre);
        txtContraseña=findViewById(R.id.txtContraseña);
        btnGuardar=findViewById(R.id.btnGuardar);
        btnIniciar=findViewById(R.id.btnIniciar);

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                guardar(txtCorreo.getText().toString(),txtContraseña.getText().toString(),txtNombre.getText().toString());
            }
        });
        btnIniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(Main2Activity.this, MainActivity.class));
            }
        });

    }

    private void guardar (String correo, String contra, String name)
    {
        DataBase helper = new DataBase(this,"BDEMPLEADOS",
                null,1);
        SQLiteDatabase db= helper.getWritableDatabase();
        try
        {
            //Contenedor de datos del contacto
            ContentValues p = new ContentValues();
            p.put("email", correo);
            p.put("clave",contra);
            p.put("nombre",name);
            db.insert("Inicio1",null,p);
            db.close();
            Toast.makeText(this,"Registro agregado correctamente...",
                    Toast.LENGTH_SHORT).show();
        }
        catch (Exception e)
        {
            Toast.makeText(this,"Error: "+e.getMessage(),
                    Toast.LENGTH_SHORT).show();
        }
    }

    public class DataBase extends SQLiteOpenHelper
    {

        String Tabla = "CREATE TABLE Inicio1(email TEXT PRIMARY KEY ,clave TEXT, nombre TEXT )";

        public DataBase(Context context, String name, SQLiteDatabase.CursorFactory factory, int version)
        {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db)
        {
            db.execSQL(Tabla);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
        {
            db.execSQL("DROP TABLE Inicio1");
            db.execSQL(Tabla);
        }
    }


}
