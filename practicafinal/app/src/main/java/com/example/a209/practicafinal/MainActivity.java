package com.example.a209.practicafinal;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import javax.xml.validation.Validator;

public class MainActivity extends AppCompatActivity {

    EditText txtCorreo, txtContraseña;

    Button btnIniciar, btnRegistrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtCorreo = findViewById(R.id.txtCorreo);
        txtContraseña = findViewById(R.id.txtContraseña);
        btnIniciar = findViewById(R.id.btnIniciar);
        btnRegistrar = findViewById(R.id.btnRegistrar);


        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(MainActivity.this, Main2Activity.class));
            }
        });
        btnIniciar.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v) {
                //startActivity(new Intent(MainActivity.this, Main3Activity.class));


                consultar(txtCorreo.getText().toString(), txtContraseña.getText().toString());

            }
        });

}

    private void consultar (String correo, String contra)
        {

            //Creacion de la BD
            DataBase helper = new DataBase(this, "BDEMPLEADOS", null, 1);
           SQLiteDatabase db = helper.getWritableDatabase();
            try {
                String sqlComand = "Select * From Inicio1 where email =? and clave =?";
                String[] args ={correo , contra};
                Cursor p = db.rawQuery(sqlComand,args);
                if (p.moveToFirst()) {

                    txtCorreo.setText("" + p.getString(0));
                    txtContraseña.setText("" + p.getString(1));

                    startActivity(new Intent(MainActivity.this, Main3Activity.class));

                } else {
                    Toast.makeText(this, "Verifica los datos", Toast.LENGTH_SHORT).show();
                }
                db.close();
            } catch (Exception e) {
                Toast.makeText(this, "Error:606 " + e.getMessage(), Toast.LENGTH_SHORT).show();
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
