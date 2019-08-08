package app.com.optracav;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
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
import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class Lector2PDF_Activity extends AppCompatActivity implements ZXingScannerView.ResultHandler {

    private ZXingScannerView mScannerView;
    private Button btnScanner;

    String url = AppSingleton.url + "site/insertar-cav2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lector_pdf_);

        // Metodo para abrir la camara y escanear pdf417
        mScannerView = new ZXingScannerView(Lector2PDF_Activity.this);
        setContentView(mScannerView);
        mScannerView.setResultHandler(Lector2PDF_Activity.this);
        mScannerView.startCamera();

    }

    @Override
    public void handleResult(Result result) {
        PDFParser parser = new PDFParser();
        try{

            String text = result.getText();
            CAVObject cavObject = parser.parse2(text);
            final String fecha = cavObject.getFecha();
            final int multas = cavObject.getMultas();
            final int prenda = cavObject.getPrenda();
            final String patente = getIntent().getExtras().getString("patente");

            if(fecha != null && multas >= 0 && prenda >= 0){
                StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                try {
                                    JSONObject jsonObject = new JSONObject(response);
                                    String responseString = jsonObject.getString("mensaje");
                                    if(responseString.equals("OK")){
                                        Toast.makeText(getApplicationContext(), "PÁGINA 2 LEÍDA CON ÉXITO. PROCESO FINALIZADO.", Toast.LENGTH_SHORT).show();
                                        ExitActivity.exitApplication(getApplicationContext());
                                        System.exit(0);
                                    }
                                    else{
                                        Toast.makeText(getApplicationContext(), "ERROR AL LEER LA PÁGINA 1 DEL CAV, REINTENTE.", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(Lector2PDF_Activity.this, Lector2PDF_Activity.class);
                                        startActivity(intent);
                                    }
                                    finish();
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                    Toast.makeText(getApplicationContext(), "ERROR AL LEER LA PÁGINA 1 DEL CAV, REINTENTE.", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(Lector2PDF_Activity.this, Lector2PDF_Activity.class);
                                    startActivity(intent);
                                } catch (Throwable throwable) {
                                    throwable.printStackTrace();
                                    Toast.makeText(getApplicationContext(), "ERROR AL LEER LA PÁGINA 1 DEL CAV, REINTENTE.", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(Lector2PDF_Activity.this, Lector2PDF_Activity.class);
                                    startActivity(intent);
                                }
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        String errorMessage = error.getMessage();
                        Toast.makeText(getApplicationContext(), "ERROR REINTENTANDO...", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Lector2PDF_Activity.this,Lector2PDF_Activity.class);
                        startActivity(intent);
                        finish();
                    }
                })
                {

                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String,String> params = new HashMap<>();

                        params.put("patente", patente);
                        params.put("fecha", fecha);
                        params.put("multas", multas+"");
                        params.put("prenda", prenda+"");

                        return params;
                    }
                };
                AppSingleton.getInstance(Lector2PDF_Activity.this).addToRequestQue(stringRequest);
            }
            else{
                Toast.makeText(getApplicationContext(), "ERROR AL LEER LA PÁGINA 1 DEL CAV, REINTENTE.", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Lector2PDF_Activity.this,Lector2PDF_Activity.class);
                startActivity(intent);
                finish();
            }

        }
        catch(Exception ex){
            String error = ex.getMessage();

        }
    }

    public void dialogo (final String result){

        AlertDialog.Builder builder = new AlertDialog.Builder(Lector2PDF_Activity.this);
        builder.setMessage(result)
                .setCancelable(false)
                .setPositiveButton("Siguiente", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent lectorPDF = new Intent(Lector2PDF_Activity.this, Lector2PDF_Activity.class);
                        startActivity(lectorPDF);
                        finish();
                    }
                });
        AlertDialog header = builder.create();
        header.setTitle("Rut QR");
        header.show();
    }

};
