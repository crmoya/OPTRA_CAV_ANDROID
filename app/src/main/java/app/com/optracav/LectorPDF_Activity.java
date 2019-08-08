package app.com.optracav;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.zxing.Result;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import app.com.optracav.clases.AppSingleton;
import app.com.optracav.clases.CAVObject;
import app.com.optracav.clases.PDFParser;
import app.com.optracav.clases.Propietario;
import app.com.optracav.clases.Vehiculo;
import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class LectorPDF_Activity extends AppCompatActivity implements ZXingScannerView.ResultHandler {

    private ZXingScannerView mScannerView;
    private Button btnScanner;

    String url = AppSingleton.url + "site/insertar-cav";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lector_pdf_);

        // Metodo para abrir la camara y escanear pdf417
        mScannerView = new ZXingScannerView(LectorPDF_Activity.this);
        setContentView(mScannerView);
        mScannerView.setResultHandler(LectorPDF_Activity.this);
        mScannerView.startCamera();

    }

    @Override
    public void handleResult(Result result) {
        PDFParser parser = new PDFParser();
        try{

            String text = result.getText();
            CAVObject cavObject = parser.parse(text);
            final Propietario propietario = cavObject.getPropietario();
            final Vehiculo vehiculo = cavObject.getVehiculo();

            if(propietario.isOK() && vehiculo.isOK()){
                StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                try {
                                    JSONObject jsonObject = new JSONObject(response);
                                    String responseString = jsonObject.getString("mensaje");
                                    if(responseString.equals("OK")){
                                        Toast.makeText(getApplicationContext(), "PÁGINA 1 LEÍDA CON ÉXITO.", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(LectorPDF_Activity.this,Lector2PDF_Activity.class);
                                        intent.putExtra("patente", vehiculo.getPatente());
                                        startActivity(intent);
                                    }
                                    else{
                                        Toast.makeText(getApplicationContext(), "ERROR AL LEER LA PÁGINA 1 DEL CAV, REINTENTE.", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(LectorPDF_Activity.this,LectorPDF_Activity.class);
                                        startActivity(intent);
                                    }
                                    finish();
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                } catch (Throwable throwable) {
                                    throwable.printStackTrace();
                                }
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        String errorMessage = error.getMessage();
                        Toast.makeText(getApplicationContext(), "ERROR REINTENTANDO...", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(LectorPDF_Activity.this,LectorPDF_Activity.class);
                        startActivity(intent);
                        finish();
                    }
                })
                {

                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String,String> params = new HashMap<>();

                        //propietario
                        params.put("rut", propietario.getRut());
                        params.put("nombres", propietario.getNombres());
                        params.put("apellido_paterno", propietario.getApellidoPaterno());
                        params.put("apellido_materno", propietario.getApellidoMaterno());

                        //vehiculo
                        params.put("patente", vehiculo.getPatente());
                        params.put("chasis", vehiculo.getNroChasis());
                        params.put("motor", vehiculo.getNroMotor());
                        params.put("agno", vehiculo.getAgno());
                        params.put("modelo", vehiculo.getModelo());
                        params.put("marca", vehiculo.getMarca());
                        params.put("tipo", vehiculo.getTipo());
                        params.put("combustible", vehiculo.getCombustible());
                        params.put("color", vehiculo.getColor());
                        params.put("pbv", vehiculo.getPBV());

                        //persona natural o jurídica
                        params.put("natural",propietario.getPersonaNatural());

                        return params;
                    }
                };
                AppSingleton.getInstance(LectorPDF_Activity.this).addToRequestQue(stringRequest);
            }
            else{
                Toast.makeText(getApplicationContext(), "ERROR AL LEER LA PÁGINA 1 DEL CAV, REINTENTE.", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(LectorPDF_Activity.this,LectorPDF_Activity.class);
                startActivity(intent);
                finish();
            }


        }
        catch(Exception ex){
            String error = ex.getMessage();

        }
    }

    public void dialogo (final String result){

        AlertDialog.Builder builder = new AlertDialog.Builder(LectorPDF_Activity.this);
        builder.setMessage(result)
                .setCancelable(false)
                .setPositiveButton("Siguiente", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent lectorPDF = new Intent(LectorPDF_Activity.this,LectorPDF_Activity.class);
                        startActivity(lectorPDF);
                        finish();
                    }
                });
        AlertDialog header = builder.create();
        header.setTitle("Rut QR");
        header.show();
    }

};
