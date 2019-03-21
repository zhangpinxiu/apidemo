import com.rongrong.wework.Wework;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @author rongrong
 * @Title: TestGetToken
 * @ProjectName apidemo
 * @Description: TODO
 * @date 2019/3/17 10:16
 * @email 1163565402@qq.com
 */
public class TestGetToken {
/*    @Test
    void testToken(){
        RestAssured.given().log().all()
                .queryParam("corpid",com.rongrong.WeWorkConfig.getInstance().corpid)
                .queryParam("corpsecret",com.rongrong.WeWorkConfig.getInstance().secret)
                .when().get("https://qyapi.weixin.qq.com/cgi-bin/gettoken")
                .then().statusCode(200).body("errcode",equalTo(0));
    }*/

    @Test
    void testToken(){
        Wework wework = new Wework();
        String token = wework.getToken();
        assertThat(token,not(equalTo(null)));
    }
}
