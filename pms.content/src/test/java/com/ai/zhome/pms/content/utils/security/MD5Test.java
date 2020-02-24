package com.ai.zhome.pms.content.utils.security;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MD5Test {

    //需被加密参数
    private final String param = "eyJjcElkIjoieHh4eHh4IiwidXNlcklkIjoieHh4eHgiLCJ1c2VyVG9rZW4iOiIiLCJ1c2VyR3JvdXAiOiIiLCJwcm9kdWN0SWQiOiJ4eHh4eCIsInByb2R1Y3ROYW1lIjoieHh4eHh4IiwicHJvZHVjdFF1YW50aXR5IjoxLCJwcm9kdWN0QW1vdW50IjoxMCwiY29udGVudElkIjoieHh4eHgiLCJjb250ZW50TmFtZSI6Inh4eHh4eCIsImpmTnVtYmVyIjowLCJqZkFtb3VudCI6MCwiY291cG9uU2VyaWFsIjoieHh4eHh4eCIsImNvdXBvbkFtb3VudCI6MSwiaXNDb25jZXNzaW9uYWwiOjF9surfvideo";

    //加密后的参数,固定32位
    private final String securityParam32 = "937456c6a7625cfef38048b25128585f";

    //加密后的参数,固定16位
    private final String securityParam16 = "a7625cfef38048b2";
    /**
     * MD5加密为不可逆加密算法
     */
    @Test
    public void encrypt(){
        MD5 md5 = new MD5();
        String result = md5.encrypt(param);
        System.out.println(result);
        Assert.assertEquals(securityParam32,result);
    }

    @Test
    public void encode(){
        MD5 md5 = new MD5();
        String result = md5.encode(param);
        System.out.println(result);
        Assert.assertEquals(securityParam16,result);
    }


}
