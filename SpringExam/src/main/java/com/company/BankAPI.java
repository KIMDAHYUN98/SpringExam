package com.company;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;

import com.fasterxml.jackson.databind.json.JsonMapper;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@Controller
public class BankAPI {
	
	
	String access_Token = "";
	String refresh_Token = "";
	String user_org_code = "";
	
	public Map<String, Object> getOrgAccessToken() {
		HashMap<String, Object> map = new HashMap<String, Object>();
        String reqURL = "https://testapi.openbanking.or.kr/oauth/2.0/token";
        
        try {
            URL url = new URL(reqURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            
            //    POST 요청을 위해 기본값이 false인 setDoOutput을 true로
            conn.setRequestMethod("POST");
            conn.setDoOutput(true); // false : 파라미터 값이 없을 때
            
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
            
            //    POST 요청에 필요로 요구하는 파라미터 스트림을 통해 전송
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
            StringBuilder sb = new StringBuilder();
            
            sb.append("client_id=de1e3afe-a852-40ca-a741-5658dafd24b1");
            sb.append("&client_secret=81a52f4c-3c98-4508-afbd-b54642901b3d");
            sb.append("&scope=oob"); 
            sb.append("&grant_type=client_credentials");
            
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
            
            Gson gson = new Gson();
	        map = gson.fromJson(result, HashMap.class);
			 
            System.out.println("access_token : " + access_Token);
            System.out.println("refresh_token : " + refresh_Token);
            
            br.close();
            bw.close();
            
           
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } 
        
        return map;
    }
	
	
	public String getAccessToken (String authorize_code) {
		
		
        String reqURL = "https://testapi.openbanking.or.kr/oauth/2.0/token";
        
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
			/*
			 * Gson gson = new Gson(); map = gson.fromJson(sb.toString(), Map.class);
			 */
            
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
	
	public String getDate() {
		String str = "";
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddhhmmss");
		str = format.format(date);
		return str; // 현재 시간을 가지고 시분초 를 스트링으로 변환
	}
	
	public String getRand32() {
		// 32바이트 난수
		return "";
	}
	
	
	public String getRand() { // 9자리 난수
		long time = System.currentTimeMillis(); // 13자리
		String str = Long.toString(time);
		return str.substring(str.length()-9); // 뒤에서 9자리
	}
	
	public HashMap<String, Object> getBalance(BankVO vo) {
		HashMap<String, Object> map = new HashMap<>();
	    String reqURL = "https://testapi.openbanking.or.kr/v2.0/account/balance/fin_num";
	    StringBuilder qstr = new StringBuilder();
		
	    String bank_tran_id = "M202111681";
	    
	    qstr.append("bank_tran_id=").append(bank_tran_id + "U" + getRand())
			.append("&fintech_use_num=").append(vo.getFintech_use_num())
			.append("&tran_dtime=").append(getDate());
	    try {
	        URL url = new URL(reqURL + "?" + qstr);
	        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	        conn.setRequestMethod("GET");
	        
	        // 요청에 필요한 Header에 포함될 내용
	        conn.setRequestProperty("Authorization", "Bearer " + vo.getAccess_token());
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
	        
	        // String -> map 에 담기
	       
	        Gson gson = new Gson();
	        map = gson.fromJson(result, HashMap.class);
	        
	        //userInfo.put("email", email);
	        
	    } catch (IOException e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
	    }
	    
	    return map;
	}

	public HashMap<String, Object> getAccoutList(String access_token, String user_num) {
		// 요청하는 클라이언트마다 가진 정보가 다를 수 있기에 HashMap타입으로 선언
		HashMap<String, Object> map = new HashMap<>();
	    String reqURL = "https://testapi.openbanking.or.kr/v2.0/account/list";
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
	        
	        // String -> map 에 담기
	       
	        Gson gson = new Gson();
	        map = gson.fromJson(result, HashMap.class);
	        
	        //userInfo.put("email", email);
	        
	    } catch (IOException e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
	    }
	    
	    return map;
	}



}