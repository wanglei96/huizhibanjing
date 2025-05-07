package strosoft.main.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import strosoft.app.common.SysConfig;
import strosoft.app.util.Tracer;

import java.io.IOException;

@Configuration
public class ResourcesConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String uploadDirectoryPath = "file:" + SysConfig.getAbsoluteUploadDirectory() + "/";

        System.out.println("uploadDirectoryPath:" + uploadDirectoryPath);
        Tracer.writeLine("upload directory:" + uploadDirectoryPath);
        registry.addResourceHandler("/upload/**").addResourceLocations(uploadDirectoryPath);
    }

}