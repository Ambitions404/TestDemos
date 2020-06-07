package com.wj.jasypt;

import lombok.extern.slf4j.Slf4j;
import org.jasypt.encryption.StringEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;

import javax.annotation.Resource;

@SpringBootApplication
@Slf4j
public class JasyptApplication implements CommandLineRunner { // CommandLineRunner 实现run()方法，启动后可以执行run()里面的操作，用value控制接口的类run方法的执行顺序

    @Resource
    private StringEncryptor stringEncryptor;

    @Autowired
    private ApplicationContext context;

    @Value("${test.decryptpsd}")
    private String decryptPsd;

    public static void main(String[] args) {

        SpringApplication.run(JasyptApplication.class, args);
    }

    @Override
    // 简单加密解密
    public void run(String... args) throws Exception {

        Environment environment = context.getBean(Environment.class);
        String psd = environment.getProperty("test.password");
        log.info("source psd={}", psd);
        String encryptPsw = stringEncryptor.encrypt(psd);
        log.info("encrypt psd1={}", encryptPsw);

        log.info("{}", stringEncryptor.decrypt(decryptPsd));
//        String psdDecrypt = environment.getProperty("test.decryptpsd");
//
//        log.info("encrypt psd2={}", psdDecrypt);
    }
}
