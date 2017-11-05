package tv.wallbase.wechat.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Map;

/**
 * 微信发送模板消息内容
 *
 * @author wangkun23
 */
@ToString
public class TemplateMessage implements Serializable {

    @Setter
    @Getter
    private String touser;

    @Setter
    @Getter
    private String template_id;

    @Setter
    @Getter
    private String url;

    @Setter
    @Getter
    private Map<String, MessageTemplateData> data;

    public TemplateMessage(String touser, String template_id, String url, Map<String, MessageTemplateData> data) {
        this.touser = touser;
        this.template_id = template_id;
        this.url = url;
        this.data = data;
    }
}
