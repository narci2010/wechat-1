package tv.wallbase.wechat.service.impl;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import tv.wallbase.wechat.model.TemplateMessage;
import tv.wallbase.wechat.service.WechatOAuth2Service;
import tv.wallbase.wechat.service.WechatTemplateMessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 微信模板消息发送
 * Created by wangkun23 on 2017/6/26.
 */
@Service
public class WechatTemplateMessageServiceImpl implements WechatTemplateMessageService {

    private static final Logger logger = LoggerFactory.getLogger(WechatTemplateMessageServiceImpl.class);
    private static final String MESSAGE_TEMPLATE_SEND_URL = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=%s";

    @Resource
    private WechatOAuth2Service wechatOAuth2Service;

    @Autowired
    RestTemplate restTemplate;

    /**
     * 发送模板消息
     *
     * @param message
     */
    public void sendTemplateMessage(TemplateMessage message) {
        String access_token = wechatOAuth2Service.getAccessToken();
        String sendUrl = String.format(MESSAGE_TEMPLATE_SEND_URL, access_token);
        try {
            String content = JSON.toJSONString(message);
            logger.debug("sendJSON {}", content);
            HttpEntity<String> request = new HttpEntity<>(content);
            ResponseEntity<String> response = restTemplate.postForEntity(sendUrl, request, String.class);
            String rawResponse = response.getBody();
            //= HttpClientUtils.getInstance().doPost(sendUrl, content);
            logger.info("sendTemplateMessage {} ,rawResponse {}", message, rawResponse);
        } catch (RestClientException e) {
            logger.error("when sendTemplateMessage error {}", e);
        }
    }

}
