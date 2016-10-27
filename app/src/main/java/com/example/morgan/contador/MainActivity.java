package com.example.morgan.contador;

import android.app.Activity;
import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
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

        //La inicializo aqui en el contructor junto con el contador
        textoResultado = (TextView)findViewById(R.id.textViewNum);

        contador = 0;


        ///////EventoTeclado es de una classe que esta creada abajo y creamos un objeto de esa clase
        EventoTeclado teclado=new EventoTeclado();

        //Creamos un objeto de tipo EditText que se identifique con el numero del reset
        EditText n_reseteo=(EditText)findViewById(R.id.editTextNumeroReseteo);

        //Este objeto lo ponemos a la escucha del teclado
        n_reseteo.setOnEditorActionListener(teclado);
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
        textoResultado.setText("" + contador);


        //Instanciamos este metodo que servira para que al desplegar el teclado no mueva la app
        InputMethodManager miTeclado = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);

        //Con esto conseguimos que al pulsar el reset el teclado se oculte automáticamente
        miTeclado.hideSoftInputFromWindow(numeroReset.getWindowToken(),0);
    }




    //////Esta clase es para que funcione el ok del teclado
    class EventoTeclado implements TextView.OnEditorActionListener
    {

        //Esta funcion se ejecuta cuando pulsan el ok del teclado
        @Override
        public boolean onEditorAction(TextView v, int actionId, KeyEvent event)
        {

            //Esta condicion comprueba si se a ocultado el teclado con el ok
            if(actionId== EditorInfo.IME_ACTION_DONE)
            {
                //Entonces llamamos a la funcion de reset. Nos obliga a ponerle un parametro pero poniendo un null se solventa este problema
                reset(null);
            }
            return false;
        }
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
