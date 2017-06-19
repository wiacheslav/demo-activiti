package com.example.activiti;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.Test;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class DemoActivitiApplicationTests {

    @Test
    public void contextLoads() {
        String jwt = Jwts.builder()
                .setSubject("wombat")
                .signWith(SignatureAlgorithm.HS256, "secret")
                .compact();
        System.out.println(jwt);

        System.out.println(Jwts.parser().setSigningKey("secret"). parse(jwt));
    }

}
