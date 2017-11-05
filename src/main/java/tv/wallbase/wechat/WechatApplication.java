package tv.wallbase.wechat;

import org.springframework.boot.ApplicationPid;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;

/**
 * 房联微信助手启动类
 * Created by wangkun23 on 2016/12/27.
 */
@SpringBootApplication
public class WechatApplication {

    @PostConstruct
    private void handlePid() throws IOException {
        File file = new File("/tmp/application.pid");
        new ApplicationPid().write(file);
        file.deleteOnExit();
    }

    public static void main(String[] args) {
        SpringApplication.run(WechatApplication.class, args);
    }
}
