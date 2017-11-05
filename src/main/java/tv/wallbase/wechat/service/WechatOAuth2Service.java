package tv.wallbase.wechat.service;

import com.github.scribejava.core.oauth.OAuth20Service;

import java.util.Map;

/**
 * 微信静默授权获取openID
 *
 * @author wangkun23 2017/6/19.
 */
public interface WechatOAuth2Service {

    /**
     * 获取微信Oauth2的服务
     *
     * @return
     */
    public OAuth20Service getService();


    /**
     * 为了调用微信的原生API需要
     *
     * @return
     */
    public Map<String, Object> sign(String url);


    /**
     * 获取微信的access_token信息
     *
     * @return
     */
    public String getAccessToken();

    /**
     * 获取jsAPI的ticket
     *
     * @param accessToken
     * @return
     */
    public String getJsApiTicket(String accessToken);
}
