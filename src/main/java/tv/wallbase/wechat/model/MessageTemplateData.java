package tv.wallbase.wechat.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 微信中发送模板消息 内容
 *
 * @author wangkun23
 */
public class MessageTemplateData implements Serializable {

    @Setter
    @Getter
    private String color;

    @Setter
    @Getter
    private String value;


    public MessageTemplateData(String color, String value) {
        this.color = color;
        this.value = value;
    }
}
