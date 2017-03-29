package xabi.es.zinovax.morsecompleto;

import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    EditText textotrad;
    private CameraManager mCameraManager;
    private String mCameraId;
    private Boolean isTorchOn;
    TextView textoResultado;
    Button BotOn;
    Button BotOff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textoResultado = (TextView)findViewById(R.id.resmorse);
        textotrad = (EditText) findViewById(R.id.textotrad);
        BotOn = (Button)findViewById(R.id.encender);
        BotOff = (Button)findViewById(R.id.apagar);
        isTorchOn = false;

        Boolean isFlashAvailable = getApplicationContext().getPackageManager()
                .hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH);

        if (!isFlashAvailable) {

            AlertDialog alert = new AlertDialog.Builder(MainActivity.this)
                    .create();
            alert.setTitle("Error !!");
            alert.setMessage("Your device doesn't support flash light!");
            alert.setButton(DialogInterface.BUTTON_POSITIVE, "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // closing the application
                            finish();
                            System.exit(0);
                        }
                    });
            alert.show();
            return;
        }

        mCameraManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
        try {
            mCameraId = mCameraManager.getCameraIdList()[0];
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }

        BotOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                        encenderLuz();
                        isTorchOn = true;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        BotOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    apagarLuz();
                    isTorchOn = false;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }


    public void encenderLuz() {

        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                mCameraManager.setTorchMode(mCameraId, true);
                isTorchOn = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void apagarLuz() {

        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                mCameraManager.setTorchMode(mCameraId, false);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    protected void onStop() {
        super.onStop();
        if(isTorchOn){
            apagarLuz();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(isTorchOn){
           apagarLuz();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (isTorchOn) {
            encenderLuz();
        }
    }
    //Esto es un metodo
    public void fconvert (View vista){
        Editable texto;
        texto = textotrad.getText();

        textoResultado.setText("Valor: " + stringConvertido(textotrad.getText().toString()));
    }

    public static String stringConvertido(String loreString)
    {
        String cadena;
        String getMorse;
        String convertirString = "";

        for (int i = 0; i < loreString.length(); i++)
        {
            //Obtiene un caracter en la posiciÃ³n i
            cadena = loreString.charAt(i) + "";

            //convertir caracteres a cÃ³digo Morse
            getMorse = convierte(cadena);

            //palabras separadas con el sÃ­mbolo |
            if (getMorse.equals(" "))
            {
                convertirString= convertirString + "  |  ";
            }

            else
            {
                //concatena la letra convertida
                convertirString = convertirString + getMorse;

                //AÃ±adir un espacio entre cada letra
                if (!getMorse.equals(" "))
                {
                    convertirString = convertirString + " ";
                }
            }
        }

        return convertirString;

    }
    public static String convierte (String letra)
    {
        String morse = letra;

        if (letra.equalsIgnoreCase("a"))
            morse = ".-";
        if (letra.equalsIgnoreCase("b"))
            morse = "-...";
        if (letra.equalsIgnoreCase("c"))
            morse = "-.-.";
        if (letra.equalsIgnoreCase("d"))
            morse = "-..";
        if (letra.equalsIgnoreCase("e"))
            morse = ".";
        if (letra.equalsIgnoreCase("f"))
            morse = "..-.";
        if (letra.equalsIgnoreCase("g"))
            morse = "--.";
        if (letra.equalsIgnoreCase("h"))
            morse = "....";
        if (letra.equalsIgnoreCase("i"))
            morse = "..";
        if (letra.equalsIgnoreCase("j"))
            morse = ".---";
        if (letra.equalsIgnoreCase("k"))
            morse = "-.-";
        if (letra.equalsIgnoreCase("l"))
            morse = ".-..";
        if (letra.equalsIgnoreCase("m"))
            morse = "--";
        if (letra.equalsIgnoreCase("n"))
            morse = "-.";
        if (letra.equalsIgnoreCase("o"))
            morse = "---";
        if (letra.equalsIgnoreCase("p"))
            morse = ".--.";
        if (letra.equalsIgnoreCase("q"))
            morse = "--.-";
        if (letra.equalsIgnoreCase("r"))
            morse = ".-.";
        if (letra.equalsIgnoreCase("s"))
            morse = "...";
        if (letra.equalsIgnoreCase("t"))
            morse = "-";
        if (letra.equalsIgnoreCase("u"))
            morse = "..-";
        if (letra.equalsIgnoreCase("v"))
            morse = "...-";
        if (letra.equalsIgnoreCase("w"))
            morse = ".--";
        if (letra.equalsIgnoreCase("x"))
            morse = "-..-";
        if (letra.equalsIgnoreCase("y"))
            morse = "-.--";
        if (letra.equalsIgnoreCase("z"))
            morse = "--..";
        if (letra.equalsIgnoreCase("0"))
            morse = "-----";
        if (letra.equalsIgnoreCase("1"))
            morse = ".----";
        if (letra.equalsIgnoreCase("2"))
            morse = "..---";
        if (letra.equalsIgnoreCase("3"))
            morse = "...--";
        if (letra.equalsIgnoreCase("4"))
            morse = "....-";
        if (letra.equalsIgnoreCase("5"))
            morse = ".....";
        if (letra.equalsIgnoreCase("6"))
            morse = "-....";
        if (letra.equalsIgnoreCase("7"))
            morse = "--...";
        if (letra.equalsIgnoreCase("8"))
            morse = "---..";
        if (letra.equalsIgnoreCase("9"))
            morse = "----.";
        if (letra.equalsIgnoreCase("."))
            morse = ".-.-";
        if (letra.equalsIgnoreCase(","))
            morse = "--..--";
        if (letra.equalsIgnoreCase("?"))
            morse = "..--..";

        return morse;
    }

}