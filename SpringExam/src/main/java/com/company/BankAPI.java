package com.company;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@Controller
public class BankAPI {
	public String getAccessToken (String authorize_code) {
		
		
        String access_Token = "";
        String refresh_Token = "";
        String reqURL = "https://testapi.openbanking.or.kr/oauth/2.0/token"; //
        
        try {
            URL url = new URL(reqURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            
            //    POST 요청을 위해 기본값이 false인 setDoOutput을 true로
            conn.setRequestMethod("POST");
            conn.setDoOutput(true); // false : 파라미터 값이 없을 때
            
            //    POST 요청에 필요로 요구하는 파라미터 스트림을 통해 전송
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
            StringBuilder sb = new StringBuilder();
            
            sb.append("code=GpifZLJuI2K1wh5Pb4u6sTvchZ232D");
            sb.append("&client_id=de1e3afe-a852-40ca-a741-5658dafd24b1");
            sb.append("&client_secret=81a52f4c-3c98-4508-afbd-b54642901b3d");
            sb.append("&redirect_uri=http://localhost/temp/callback"); 
            sb.append("&grant_type=authorization_code"); // pdf : 파라미터 다섯개
            
            bw.write(sb.toString());
            bw.flush();
            // 여기까지 서버
            
            //    결과 코드가 200이라면 성공
            int responseCode = conn.getResponseCode();
            System.out.println("responseCode : " + responseCode);
 
            //    요청을 통해 얻은 JSON타입의 Response 메세지 읽어오기
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line = "";
            String result = "";
            
            while ((line = br.readLine()) != null) {
                result += line;
            }
            System.out.println("response body : " + result);
            
            //    Gson 라이브러리에 포함된 클래스로 JSON파싱 객체 생성
            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(result); // map 구조
            
            access_Token = element.getAsJsonObject().get("access_token").getAsString();
            refresh_Token = element.getAsJsonObject().get("refresh_token").getAsString();
            
            System.out.println("access_token : " + access_Token);
            System.out.println("refresh_token : " + refresh_Token);
            
            br.close();
            bw.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } 
        
        return access_Token;
    }

	public HashMap<String, Object> getUserinfo(String access_token, String user_num) {
		// 요청하는 클라이언트마다 가진 정보가 다를 수 있기에 HashMap타입으로 선언
	    HashMap<String, Object> userInfo = new HashMap<>();
	    String reqURL = "https://testapi.openbanking.or.kr/v2.0/user/unlink";
	    StringBuilder qstr = new StringBuilder();
		
	    String user_seq_no = "1100770528";
	    String include_cancel_yn = "Y";
	    String sort_order = "D";
	    
	    qstr.append("user_seq_no=" + user_seq_no)
			.append("&include_cancel_yn=" + include_cancel_yn)
			.append("&sort_order=" + sort_order);
	    try {
	        URL url = new URL(reqURL + "?" + qstr);
	        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	        conn.setRequestMethod("GET");
	        
	        // 요청에 필요한 Header에 포함될 내용
	        conn.setRequestProperty("Authorization", "Bearer " + access_token);
	        // 출력되는 값이 200이면 정상작동
	        int responseCode = conn.getResponseCode();
	        System.out.println("responseCode : " + responseCode);
	        
	        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	        
	        String line = "";
	        String result = "";
	        
	        while ((line = br.readLine()) != null) {
	            result += line;
	        }
	        System.out.println("response body : " + result);
	        
	        
	        //userInfo.put("email", email);
	        
	    } catch (IOException e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
	    }
	    
	    return userInfo;
	}

}