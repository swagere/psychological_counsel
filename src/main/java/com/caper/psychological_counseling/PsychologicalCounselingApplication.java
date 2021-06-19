package com.caper.psychological_counseling;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan({"com.caper.psychological_counseling.mapper"})
public class PsychologicalCounselingApplication {

    public static void main(String[] args) {
        SpringApplication.run(PsychologicalCounselingApplication.class, args);
    }

}
