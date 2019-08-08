package app.com.optracav;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private static int SPLASH_TIME_OUT = 1000;
    public static final int MULTIPLES_PERMISOS = 10;

    String[] permisos= new String[]{
            Manifest.permission.INTERNET,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.CAMERA};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(checkPermissions()){
            show();
        }


    }

    private  boolean checkPermissions() {
        int result;
        List<String> listPermissionsNeeded = new ArrayList<>();
        for (String p:permisos) {
            result = ContextCompat.checkSelfPermission(this,p);
            if (result != PackageManager.PERMISSION_GRANTED) {
                listPermissionsNeeded.add(p);
            }
        }
        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(this, listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]),MULTIPLES_PERMISOS );
            return false;
        }
        return true;
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String permissionsList[], int[] grantResults) {
        switch (requestCode) {
            case MULTIPLES_PERMISOS:{
                // PERMISOS ACEPTADOS
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    if (checkPermissions()){
                        show();
                    }
                }
                // FALTAN PERMISOS
                else
                {
                    checkPermissions();
                }
            }
        }
    }



    private String getUsuario(){
        SharedPreferences prefe=getSharedPreferences("datos", Context.MODE_PRIVATE);
        return prefe.getString("usuario","");
    }

    public void show(){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                String usuario = getUsuario();
                Intent intent;
                if(usuario.equals("")){
                    intent = new Intent(MainActivity.this,Login_Activity.class);
                }
                else{
                    intent = new Intent(MainActivity.this,LectorPDF_Activity.class);
                    Toast.makeText(getApplicationContext(), "SE LEERÁ LA PÁGINA 1 DEL CAV.", Toast.LENGTH_SHORT).show();
                }
                startActivity(intent);
                finish();
            }
        },SPLASH_TIME_OUT);
    }

}
