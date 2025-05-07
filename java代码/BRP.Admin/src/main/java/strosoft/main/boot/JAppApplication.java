package strosoft.main.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

import java.io.IOException;

@SpringBootConfiguration
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@ServletComponentScan(basePackages = {"strosoft.wx.pay", "strosoft.app.common"})
@ComponentScan({"strosoft.main.controller", "strosoft.main.config", "strosoft.wx.controller", "BRP.controller"})
public class JAppApplication extends SpringBootServletInitializer {
    public static void main(String[] args) throws IOException {
//        SpringApplication application = new SpringApplication(JAppApplication.class);
//        application.run(args);
        SpringApplication.run(JAppApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(JAppApplication.class);
    }
}
