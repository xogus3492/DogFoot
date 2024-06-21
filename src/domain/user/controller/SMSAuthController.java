package domain.user.controller;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import global.util.CoolSMSInfo;
import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;

import java.util.HashMap;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

@RestController
public class SMSAuthController {
	
    @PostMapping("/send-one")
    public String sendOne(HttpServletRequest request) {
        Message message = new Message(CoolSMSInfo.KEY, CoolSMSInfo.SECRET);
        String certificationNumber = generateRandomNumber();
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("to", request.getParameter("phone"));	// 수신전화번호
        params.put("from", CoolSMSInfo.FROM_NUMBER);	// 발신전화번호. 테스트시에는 발신,수신 둘다 본인 번호로 하면 됨
        params.put("type", "SMS");
        params.put("text", "[DogFoot] 본인확인 인증번호(" + certificationNumber + ")를 잘 입력해 주세요");
        params.put("app_version", "dogFoot 1.0"); // application name and version
        String result = "FAIL";
        try {
          JSONObject obj = (JSONObject) message.send(params);
          System.out.println("send 후 : " + obj.toString());
          System.out.println("확인 : " + String.valueOf(obj.get("success_count")));
          if(String.valueOf(obj.get("success_count")).equals("1")) {
        	  result = certificationNumber;
          }
        } catch (CoolsmsException e) {
          System.out.println(e.getMessage());
          System.out.println(e.getCode());
        }
        
        return result;
    }

    public String generateRandomNumber() {
        Random random = new Random();		//랜덤 함수 선언
        String resultNum = "";  		//결과 난수
        for (int i = 0; i < 6; i++) {
            resultNum += random.nextInt(9);
        }
        return resultNum;
    }
}
