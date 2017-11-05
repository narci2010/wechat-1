package tv.wallbase.wechat.controller;

import tv.wallbase.wechat.model.MessageTemplateData;
import tv.wallbase.wechat.model.TemplateMessage;
import tv.wallbase.wechat.service.UserService;
import tv.wallbase.wechat.service.WechatTemplateMessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by wangkun23 on 2017/6/18.
 */
@Controller
public class DashboardController {

    final Logger logger = LoggerFactory.getLogger(DashboardController.class);

    @Value("${app.host}")
    private String appHost;

    @Resource
    UserService userService;

    @Resource
    WechatTemplateMessageService wechatTemplateMessageService;


    @RequestMapping("/dashboard")
    public String index(Model model) {
        model.addAttribute("appHost", appHost);
        return "index";
    }


    @RequestMapping("/sendTemplateMessage")
    public String sendTemplateMessage(Model model) {

        String url = appHost + "/dashboard";
        Map<String, MessageTemplateData> data = new LinkedHashMap<>();
        data.put("first", new MessageTemplateData("#000000", "有新的下户订单需要处理,快抢单!!!"));
        data.put("keyword1", new MessageTemplateData("#ff0000", "FL21170626871014746268"));
        data.put("keyword2", new MessageTemplateData("#ff0000", "2017-06-26 23:55:55"));
        data.put("remark", new MessageTemplateData("#000000", "我是描述信息"));
        TemplateMessage templateMessage = new TemplateMessage("oyuY8w9CS8KHB2ibnlQpsve1uZQs", "sRMk7VDuIJujQLsJVSB4v3nU4bxxeVE6O8jMJulLmS0", url, data);
        wechatTemplateMessageService.sendTemplateMessage(templateMessage);

        return "uploadDemo";
    }
}
