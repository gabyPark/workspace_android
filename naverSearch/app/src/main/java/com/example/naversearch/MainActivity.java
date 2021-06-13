package com.example.naversearch;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    EditText edtSearch;
    TextView tvTitle, tvLink, tvAddress;
    ListView lv;

    String[] result;
    String[] linkSite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtSearch = findViewById(R.id.edt_search);

        Button btnSearch = findViewById(R.id.btn_search);

        tvTitle = findViewById(R.id.tv_title);
        tvLink = findViewById(R.id.tv_link);
        tvAddress = findViewById(R.id.tv_address);

        lv = findViewById(R.id.lv);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // ⭐ 스레드 작업
                Thread t1 = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        naver_search();
                    }
                });

                t1.start();
            }
        });

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(linkSite[position].contains("http://") || linkSite[position].contains("http://")){
                    Uri uri = Uri.parse(linkSite[position]);
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(),"링크없음",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    // ⭐ 네이버 검색 소스코드 가져왔음
    public void naver_search(){
        String clientId = "XnWP1XNgWV9Yi4xDfUOt"; //애플리케이션 클라이언트 아이디값"
        String clientSecret = "z2PUNOPZHb"; //애플리케이션 클라이언트 시크릿값"


        String text = null;
        try {
            // "그린팩토리" 가 검색어가 되는 것. (마지막에 edtSearch 로 교환)
            text = URLEncoder.encode(edtSearch.getText().toString(), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("검색어 인코딩 실패",e);
        }


        // search / 뒤에 blog, local 등등 어디에서 검색을 할지 결정할 수 있다
        String apiURL = "https://openapi.naver.com/v1/search/local?query=" + text+"&display=5";    // json 결과
        //String apiURL = "https://openapi.naver.com/v1/search/blog.xml?query="+ text; // xml 결과


        Map<String, String> requestHeaders = new HashMap<>();
        requestHeaders.put("X-Naver-Client-Id", clientId);
        requestHeaders.put("X-Naver-Client-Secret", clientSecret);
        String responseBody = get(apiURL,requestHeaders);

        // sysout 대신 log 태그 사용
        Log.i("mytag",responseBody);

        jsonParsing(responseBody);

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(),
                        android.R.layout.simple_list_item_1,result);
                lv.setAdapter(adapter);
            }
        });
    }


    private static String get(String apiUrl, Map<String, String> requestHeaders){
        HttpURLConnection con = connect(apiUrl);
        try {
            con.setRequestMethod("GET");
            for(Map.Entry<String, String> header :requestHeaders.entrySet()) {
                con.setRequestProperty(header.getKey(), header.getValue());
            }


            int responseCode = con.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) { // 정상 호출
                return readBody(con.getInputStream());
            } else { // 에러 발생
                return readBody(con.getErrorStream());
            }
        } catch (IOException e) {
            throw new RuntimeException("API 요청과 응답 실패", e);
        } finally {
            con.disconnect();
        }
    }


    private static HttpURLConnection connect(String apiUrl){
        try {
            URL url = new URL(apiUrl);
            return (HttpURLConnection)url.openConnection();
        } catch (MalformedURLException e) {
            throw new RuntimeException("API URL이 잘못되었습니다. : " + apiUrl, e);
        } catch (IOException e) {
            throw new RuntimeException("연결이 실패했습니다. : " + apiUrl, e);
        }
    }


    private static String readBody(InputStream body){
        InputStreamReader streamReader = new InputStreamReader(body);


        try (BufferedReader lineReader = new BufferedReader(streamReader)) {
            StringBuilder responseBody = new StringBuilder();


            String line;
            while ((line = lineReader.readLine()) != null) {
                responseBody.append(line);
            }

            return responseBody.toString();
        } catch (IOException e) {
            throw new RuntimeException("API 응답을 읽는데 실패했습니다.", e);
        }
    }

    public void jsonParsing(String data){
        try {
            JSONObject obj1 = new JSONObject(data);
            JSONArray array1 = obj1.getJSONArray("items");

            result = new String[array1.length()];
            linkSite = new String[array1.length()];

            for(int i=0; i<array1.length(); i++){
                JSONObject obj2 = array1.getJSONObject(i);
                String title = obj2.getString("title");
                String link = obj2.getString("link");
                String addr = obj2.getString("address")+ " "+
                              obj2.getString("roadAddress");

                Log.i("mytag",title+" "+link+" "+addr);
//                tvTitle.setText(title);
//                tvLink.setText(link);
//                tvAddress.setText(addr);

                result[i] = title+"\n"+addr;
                linkSite[i] = link;

            }

        } catch (Exception e){
            Log.i("mytag", "제이슨 파싱 오류");
        }
    }
}