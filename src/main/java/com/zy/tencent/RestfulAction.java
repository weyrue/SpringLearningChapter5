package com.zy.tencent;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Random;

public class RestfulAction {
    @Autowired
    private RestTemplate restTemplate;

    private static TIMRestURL timRestURL = new TIMRestURL();

    private String url = "https://console.tim.qq.com/v4/openim/querystate?sdkappid=1400320131&identifier=administrator&usersig=eJw1jssKwjAURP8lW6Xcm-RFwY3YlRV8IrgrJC23mhqTaEXx39VWl3OGM8yTbYtNoO6GrGJZDGEKMO7ZTVmWMR4AG7KTx9IYkizDEEBwQIFDQ1K1nirqhVJqasl5W-qz-atUf6dOsYtWNod6v9YJFuIqFq6J5irfLQ*XXMfTbgTygc2sm-xET-rzCqOU84RjIl5vuE0zww__&random=99999999&contenttype=json";
    private String url_short = "https://console.tim.qq.com/v4/openim/querystate";

    @RequestMapping("restTem")
    public @ResponseBody
    String restTem() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("To_Account", Arrays.asList("user0", "user1"));
        System.out.println(jsonObject);

//        Map<String, String> map = new HashMap<>();
//        map.put("sdkappid", "1400320131");
//        map.put("identifier", "administrator");
//        map.put("usersig", "eJw1jssKwjAURP8lW6Xcm-RFwY3YlRV8IrgrJC23mhqTaEXx39VWl3OGM8yTbYtNoO6GrGJZDGEKMO7ZTVmWMR4AG7KTx9IYkizDEEBwQIFDQ1K1nirqhVJqasl5W-qz-atUf6dOsYtWNod6v9YJFuIqFq6J5irfLQ*XXMfTbgTygc2sm-xET-rzCqOU84RjIl5vuE0zww__");
//        map.put("random", "999999");
//        map.put("contenttype", "json");

        String url = timRestURL.getURL();
        System.out.println(url);

        String response = restTemplate.postForObject(url, jsonObject.toString(), String.class);
        System.out.println(response);

        return response;
    }

    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    private static class TIMRestURL {
        private static Random random = new Random();
        private String host = "https://console.tim.qq.com";
        private String sdkappid = "1400320131";
        private String identifier = "administrator";
        private String usersig = "eJw1jssKwjAURP8lW6Xcm-RFwY3YlRV8IrgrJC23mhqTaEXx39VWl3OGM8yTbYtNoO6GrGJZDGEKMO7ZTVmWMR4AG7KTx9IYkizDEEBwQIFDQ1K1nirqhVJqasl5W-qz-atUf6dOsYtWNod6v9YJFuIqFq6J5irfLQ*XXMfTbgTygc2sm-xET-rzCqOU84RjIl5vuE0zww__";
        private String contenttype = "json";
        private String methodName = "/v4/openim/querystate";

        String getURL(String methodName, String usersig) {
            this.methodName = methodName;
            this.usersig = usersig;
            return getURL();
        }

        String getURL(String methodName) {
            this.methodName = methodName;
            return getURL();
        }

        String getURL() {
            random.setSeed(System.currentTimeMillis());
            String randomStr = String.valueOf(random.nextInt(Integer.MAX_VALUE));
            StringBuilder sb = new StringBuilder(
                    52 +
                            host.length() +
                            methodName.length() +
                            sdkappid.length() +
                            identifier.length() +
                            usersig.length() +
                            randomStr.length() +
                            contenttype.length());

            sb.append(host);
            sb.append(methodName);
            sb.append('?');
            sb.append("sdkappid=");
            sb.append(sdkappid);
            sb.append('&');
            sb.append("identifier=");
            sb.append(identifier);
            sb.append('&');
            sb.append("usersig=");
            sb.append(usersig);
            sb.append('&');
            sb.append("random=");
            sb.append(randomStr);
            sb.append('&');
            sb.append("contenttype=");
            sb.append(contenttype);

            return sb.toString();
        }

    }
}


