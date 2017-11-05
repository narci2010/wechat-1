package tv.wallbase.wechat.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.model.Verb;
import com.github.scribejava.core.oauth.OAuth20Service;
import org.apache.commons.codec.digest.DigestUtils;
import tv.wallbase.wechat.oauth2.WechatApi20;
import tv.wallbase.wechat.service.WechatOAuth2Service;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * 微信授权认证
 * Created by wangkun23 on 2017/6/19.
 */
@Service
public class WechatOAuth2ServiceImpl implements WechatOAuth2Service {

    private ConcurrentHashMap<String, String> caches = new ConcurrentHashMap();

    private static final Logger logger = LoggerFactory.getLogger(WechatOAuth2ServiceImpl.class);

    private static final String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=%s&secret=%s";
    private static final String JS_TICKET_URL = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=%s&type=jsapi";

    @Value("${app.oauth2.appId}")
    private String appId;

    @Value("${app.oauth2.secret}")
    private String secret;

    @Value("${app.oauth2.callback}")
    private String callback;

    private OAuth20Service service;

    /**
     * @return
     * @ 设置微信的配置信息
     * @ 从配置文件读取配置信息
     * @ 系统启动时就就在调用该方法 非常重要 wangkun23
     */
    @Override
    @PostConstruct
    public OAuth20Service getService() {
        logger.info("OAuth20Service PostConstruct...");
        if (service == null) {
            service = new ServiceBuilder()
                    .apiKey(appId)
                    .apiSecret(secret)
                    .callback(callback)
                    .scope("snsapi_base")
                    .build(WechatApi20.instance());
        }
        return service;
    }

    @Override
    public Map<String, Object> sign(String url) {
        Map<String, Object> result = new HashMap<String, Object>();

        String nonceStr = RandomStringUtils.randomAlphanumeric(32);
        String timestamp = Long.toString(System.currentTimeMillis());
        String jsApiTicket = getJsApiTicket(getAccessToken());
        //注意这里参数名必须全部小写，且必须有序
        String string1 = "jsapi_ticket=" + jsApiTicket + "&noncestr=" + nonceStr + "&timestamp=" + timestamp + "&url=" + url;
        String signature = DigestUtils.sha1Hex(string1);

        result.put("appId", appId);
        result.put("url", url);
        result.put("jsapi_ticket", jsApiTicket);
        result.put("noncestr", nonceStr);
        result.put("timestamp", timestamp);
        result.put("signature", signature);

        return result;
    }

    //禁止调用该方法 需要在
    public String getAccessToken() {
        String accessToken = caches.get("access_token");
        /**
         * 如果需要启用redis 使用如下测试
         * */
//        String accessToken = redisTemplate.opsForValue().get("access_token");
//        if (StringUtils.isEmpty(accessToken)) {
//            try {
//                logger.info("call wechat server..");
//                final OAuthRequest oAuthRequest = new OAuthRequest(Verb.GET, String.format(ACCESS_TOKEN_URL, appId, secret));
//                Response response = service.execute(oAuthRequest);
//                JSONObject jsonObject = (JSONObject) JSON.parse(response.getBody());
//                accessToken = (String) jsonObject.get("access_token");
//                //并把access_token放到缓存中
//                redisTemplate.opsForValue().set("access_token", accessToken, 7000, TimeUnit.SECONDS);
//                //caches.putIfAbsent("access_token", accessToken);
//            } catch (InterruptedException | ExecutionException | IOException ex) {
//                logger.error("when get wechat access_token error {}", ex);
//            }
//        }

        return accessToken;
    }

    public String getJsApiTicket(String accessToken) {
        String ticket = caches.get("js_ticket");

        /**
         * 如果需要启用redis 使用如下测试
         * */
//        String ticket = redisTemplate.opsForValue().get("js_ticket");
//        if (StringUtils.isEmpty(ticket)) {
//            try {
//                logger.info("call wechat server..");
//                final OAuthRequest oAuthRequest = new OAuthRequest(Verb.GET, String.format(JS_TICKET_URL, accessToken));
//                Response response = service.execute(oAuthRequest);
//                JSONObject jsonObject = (JSONObject) JSON.parse(response.getBody());
//                ticket = (String) jsonObject.get("ticket");
//                //并把ticket放到缓存中
//                redisTemplate.opsForValue().set("js_ticket", ticket, 7000, TimeUnit.SECONDS);
//                //caches.putIfAbsent("js_ticket", ticket);
//            } catch (InterruptedException | ExecutionException | IOException ex) {
//                logger.error("when get wechat js_api_ticket error {}", ex);
//            }
//        }
        return ticket;
    }


}
