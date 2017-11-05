package tv.wallbase.wechat.service;

import tv.wallbase.wechat.model.TemplateMessage;

/**
 * 发送微信模板消息
 * Created by wangkun23 on 2017/6/26.
 */
public interface WechatTemplateMessageService {

    /**
     * 发送模板消息
     *
     * @param message
     */
    public void sendTemplateMessage(TemplateMessage message);
}
