package com.example.morgan.contador;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

    public int contador;
    //Declaro esta variable para que todoo el programa tenga acceso a ella
    TextView textoResultado;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //La inicializo aqui en el contructor junto con el contadir
        textoResultado = (TextView)findViewById(R.id.textViewNum);


        contador = 0;
    }

    public void suma(View vista)
    {
        contador++;

        textoResultado.setText("" + contador);

    }

    public void resta(View vista)
    {
        contador--;

        if (contador < 0)
        {
            CheckBox negativos = (CheckBox)findViewById(R.id.checkBoxNegativo);

            if (!negativos.isChecked())
            {
                contador = 0;
            }
        }

        textoResultado.setText("" + contador);

    }

    public void reset(View vista)
    {
        //Aqui creo la variable de la cual hace referencia al editTextNumeroReseteo
        // del diseño que es donde pondran el numero
        EditText numeroReset = (EditText)findViewById(R.id.editTextNumeroReseteo);

        try
        {
            //Igualo la variable contador al numero que hayan puesto en el editTextNumeroReseteo
            contador = Integer.parseInt(numeroReset.getText().toString());
        }
        catch (Exception e)
        {
            //Si salta el try es porque no se a puesto ningun valor a la variable contador por eso
            //se lo hañado aqui
            contador = 0;
        }

        //Como el numero ya se ha guardado aqui lo vació para el proximo reseteo
        numeroReset.setText("");

        //Muestro el resultado del contador
        textoResultado.setText(" " + contador);
    }




    /*
    //Antes todas las funciones llamaban a esta funcion
    public void mostrarResultado()
    {
        //Creo una instancia de TextView y con el findViewById para encontrar
        // una vista a partir de su id. Entre parentesis
        // ponemos la id del elemento creado en el content_main
        TextView textoResultado = (TextView)findViewById(R.id.textViewNum);

        //usamos el metodo setText para asignar el texto a la variable
        //Si no ponemos la "" + Nos dara un error, porque esta funcion espera
        // un string, y si solo le pasas un int falla
        textoResultado.setText("" + contador);
    }
    */
}
