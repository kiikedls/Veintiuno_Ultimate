package com.example.veintiuno_ultimate;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button btn1, btn2,btn3;
    TextView txt1, txt2, txt3;
    ImageView img1;

    Integer cont = 0, tot = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn1 = findViewById(R.id.pedir);
        btn1.setOnClickListener(this);
        btn2 = findViewById(R.id.enviar);
        btn2.setOnClickListener(this);
        btn3=findViewById(R.id.reset);
        btn3.setOnClickListener(this);

        txt1 = findViewById(R.id.marca);
        txt2 = findViewById(R.id.suma);
        txt3 = findViewById(R.id.messege);
        img1 = findViewById(R.id.primera);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.pedir:
                String URL = "http://nuevo.rnrsiilge-org.mx/baraja/numero";
                JsonObjectRequest js = new JsonObjectRequest(
                        Request.Method.GET,
                        URL,
                        null/*aqui es donde voy a enviar el nombre*/,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {

                                try {
                                    cont = response.getInt("numero");
                                    tot += cont;
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                                txt1.setText(cont.toString());
                                txt2.setText(tot.toString());
//                                switch para implementar la carta
                                switch (cont) {
                                    case 1:
                                        img1.setImageResource(R.drawable.as);
                                        break;
                                    case 2:
                                        img1.setImageResource(R.drawable.dos);
                                        break;
                                    case 3:
                                        img1.setImageResource(R.drawable.tres);
                                        break;
                                    case 4:
                                        img1.setImageResource(R.drawable.cuatro);
                                        break;
                                    case 5:
                                        img1.setImageResource(R.drawable.cinco);
                                        break;
                                    case 6:
                                        img1.setImageResource(R.drawable.seis);
                                        break;
                                    case 7:
                                        img1.setImageResource(R.drawable.siete);
                                        break;
                                    case 8:
                                        img1.setImageResource(R.drawable.ocho);
                                        break;
                                    case 9:
                                        img1.setImageResource(R.drawable.nueve);
                                        break;
                                    case 10:
                                        img1.setImageResource(R.drawable.diez);
                                        break;
                                    case 11:
                                        img1.setImageResource(R.drawable.j);
                                        break;
                                    case 12:
                                        img1.setImageResource(R.drawable.q);
                                        break;
                                    case 13:
                                        img1.setImageResource(R.drawable.k);
                                        break;

                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                error.printStackTrace();
                            }
                        }
                );
                VolleyS.getInstance(this).getmRequestQueue().add(js);
                break;

            case R.id.enviar:
                if (tot!=0){
                btn3.setVisibility(View.VISIBLE);

                JSONObject n = new JSONObject();
                try {
                    n.put("nombre", "Kiike");
                    n.put("numero", tot.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                URL = "http://nuevo.rnrsiilge-org.mx/baraja/enviar";
                JsonObjectRequest xd = new JsonObjectRequest(
                        Request.Method.POST,
                        URL,
                        n/*aqui es donde voy a enviar el nombre*/,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
//                                switch
                                try {
                                    txt3.setText(response.getString("mensaje"));
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                error.printStackTrace();
                                Toast.makeText(MainActivity.this, "Nachos!!", Toast.LENGTH_SHORT).show();
                            }
                        }
                );
                VolleyS.getInstance(this).getmRequestQueue().add(xd);}
                else {Toast.makeText(MainActivity.this,"no puedes mandar solo 0",Toast.LENGTH_SHORT).show();}
                break;
            case R.id.reset:
                cont=0;
                tot=0;
                txt3.setText("");
                txt1.setText("0");
                txt2.setText("0");
                btn3.setVisibility(View.INVISIBLE);
                img1.setImageDrawable(null);
        }
    }
}
