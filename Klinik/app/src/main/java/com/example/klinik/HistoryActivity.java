package com.example.klinik;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class HistoryActivity extends AppCompatActivity {

    ListView listView;
    ArrayAdapter<String> adapter;
    private static final String JSON_URL = "http://192.168.43.93/andro/crud_ki/history.php";

    private List<PlayerItem> playerItemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        listView =  findViewById(R.id.listView);
        playerItemList = new ArrayList<>();




        loadPlayer();
    }

    private void loadPlayer() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, JSON_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject obj = new JSONObject(response);
                            int success = obj.getInt("success");
                            if (success == 1){
                            JSONArray playerArray = obj.getJSONArray("result");

                            for (int i = 0; i < playerArray.length(); i++) {

                                JSONObject playerObject = playerArray.getJSONObject(i);


                                PlayerItem playerItem = new PlayerItem(playerObject.getString("id_komentar"),
                                        playerObject.getString("no_rm"),
                                        playerObject.getString("Kritik"),
                                        playerObject.getString("saran"));

                                playerItemList.add(playerItem);
                            }

                        } else {
                                Toast.makeText(getApplicationContext(), "There is no histories yet", Toast.LENGTH_SHORT).show();
                            }

                            ListViewAdapter adapter = new ListViewAdapter(playerItemList, getApplicationContext());

                            listView.setAdapter(adapter);

                        } catch (JSONException e) {
                            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }


    /*@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        listView = (ListView)findViewById(R.id.listView);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
        listView.setAdapter(adapter);
        new Connection().execute();
    }

    class Connection extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... strings) {
            String result = "";
            String host = "http://192.168.43.93/andro/crud_ki/history.php";
            try{
                HttpClient client = new DefaultHttpClient();
                HttpGet request = new HttpGet();
                request.setURI(new URI(host));
                HttpResponse response = client.execute(request);
                BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

                StringBuffer stringBuffer = new StringBuffer("");

                String line ="";
                while ((line = reader.readLine()) != null){
                    stringBuffer.append(line);
                    break;
                }
                reader.close();
                result = stringBuffer.toString();
            }
            catch (Exception e){
                return new String ("There Exception: " + e.getMessage());
            }
            return result;
        }

        @Override
        protected void onPostExecute(String result){
            //Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
            //parsing json data here
            try{
                JSONObject jsonResult = new JSONObject(result);
                int success = jsonResult.getInt("success");
                if (success == 1){
                    JSONArray history = jsonResult.getJSONArray("history");
                    for (int i=0; i < history.length(); i++){
                        JSONObject riwayat = history.getJSONObject(i);
                        int no_rm = riwayat.getInt("no_rm");
                        String kritik = riwayat.getString("kritik");
                        String saran = riwayat.getString("saran");
                        String line = no_rm + "-" + kritik + "-" + saran;
                        adapter.add(line);
                    }
                }
                else {
                    Toast.makeText(getApplicationContext(), "There is no histories yet", Toast.LENGTH_SHORT).show();
                }
            }
            catch (JSONException e){
                Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }*/
}
