package com.ai.zhome.pms.content.utils.security;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BASE64Test {

    @Test
    public void encode(){
        //该参数是一个对象的JSON串
        String param = "{\"cpId\":\"xxxxxx\",\"userId\":\"xxxxx\",\"userToken\":\"\",\"userGroup\":\"\",\"productId\":\"xxxxx\",\"productName\":\"xxxxxx\",\"productQuantity\":1,\"productAmount\":10,\"contentId\":\"xxxxx\",\"contentName\":\"xxxxxx\",\"jfNumber\":0,\"jfAmount\":0,\"couponSerial\":\"xxxxxxx\",\"couponAmount\":1,\"isConcessional\":1}";
        String result = BASE64.encode(param);
        System.out.println(result);
        //如果编码后的值自动换行了
        result = result.replaceAll("[\\s*\t\n\r]","");
        //实际编码后的参数
        String really = "eyJjcElkIjoieHh4eHh4IiwidXNlcklkIjoieHh4eHgiLCJ1c2VyVG9rZW4iOiIiLCJ1c2VyR3JvdXAiOiIiLCJwcm9kdWN0SWQiOiJ4eHh4eCIsInByb2R1Y3ROYW1lIjoieHh4eHh4IiwicHJvZHVjdFF1YW50aXR5IjoxLCJwcm9kdWN0QW1vdW50IjoxMCwiY29udGVudElkIjoieHh4eHgiLCJjb250ZW50TmFtZSI6Inh4eHh4eCIsImpmTnVtYmVyIjowLCJqZkFtb3VudCI6MCwiY291cG9uU2VyaWFsIjoieHh4eHh4eCIsImNvdXBvbkFtb3VudCI6MSwiaXNDb25jZXNzaW9uYWwiOjF9";
        Assert.assertEquals(really,result);

    }

}
