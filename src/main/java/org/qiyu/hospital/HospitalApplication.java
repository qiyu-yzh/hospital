package org.qiyu.hospital;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootConfiguration
@EnableAutoConfiguration
@ComponentScans({
        @ComponentScan("org.qiyu.hospital"),
        @ComponentScan(basePackageClasses = {
                com.xlf.utility.UtilProperties.class,
                com.xlf.utility.exception.PublicExceptionHandlerAbstract.class,
        })
})
@EnableAsync
public class HospitalApplication {

    public static void main(String[] args) {
        SpringApplication.run(HospitalApplication.class, args);
    }

}
