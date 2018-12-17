package com.example.a209.practicafinal;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class Main3Activity extends AppCompatActivity {

    EditText txtNumero, txtValor;
    TextView tvTotal;
    RadioButton rbCinco, rbDiez;
    Button btnGuardar, btnCerrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        txtNumero=findViewById(R.id.txtNumero);
        txtValor=findViewById(R.id.txtValor);
        tvTotal=findViewById(R.id.tvTotal);
        btnCerrar=findViewById(R.id.btnCerrar);
        btnGuardar=findViewById(R.id.btnGuardar);
        rbCinco=findViewById(R.id.rbCinco);
        rbDiez=findViewById(R.id.rbDiez);

        btnCerrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)

            {
                String numero=txtNumero.getText().toString();
                String valor =txtValor.getText().toString();

                int num =Integer.parseInt(numero);
                int val =Integer.parseInt(valor);
                double res,tot;

                res =num * val;
                if (rbCinco.isChecked())
                {
                    tot = res * 5 / 100;
                    res -= tot;
                }
                else
                   if (rbDiez.isChecked())
                   {
                    tot=res*10/100;
                    res-=tot;

                    } else {
                       tot = 0;
                       res-=tot;
                   }
                    tvTotal.setText("es: $ "+ res);



                tabla_valores(txtNumero.getText().toString(),txtValor.getText().toString(),tvTotal.getText().toString());
            }
        });

    }
    private void tabla_valores(String valorunitario, String valor, String total)
    {
        DataBase helper = new DataBase(this,"BDEMPLEADOS",null,1);
        SQLiteDatabase db= helper.getWritableDatabase();
        try
        {
            ContentValues p = new ContentValues();
            p.put("Valorunitario",valorunitario);
            p.put("Valor",valor);
            p.put("Total",total);
            db.insert("Valores",null,p);
            db.close();
            Toast.makeText(this,"Registro guardado correctamente",
                    Toast.LENGTH_SHORT).show();

        }
        catch (Exception e)
        {
            Toast.makeText(this,"Error: "+e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
    public class DataBase extends SQLiteOpenHelper
    {

        String Tabla = "CREATE TABLE Valores(valorunitario TEXT PRIMARY KEY ,valor TEXT, total TEXT)";

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
            db.execSQL("DROP TABLE Valores");
            db.execSQL(Tabla);
        }
    }
}
