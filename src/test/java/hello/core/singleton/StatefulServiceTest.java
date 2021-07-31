package hello.core.singleton;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.assertj.core.api.Assertions.assertThat;

public class StatefulServiceTest {

    @Test
    @DisplayName("상태를 유지하는 싱글톤 방식의 문제점")
    void statefulServiceSingleton() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService statefulService1 = ac.getBean("statefulService", StatefulService.class);
        StatefulService statefulService2 = ac.getBean("statefulService", StatefulService.class);

        //ThreadA : A 사용자가 10000원 주문
        statefulService1.order("userA", 10000);

        //ThreadB : B 사용자가 20000원 주문
        statefulService2.order("userB", 20000);

        //ThreadA : A 사용자가 주문 금액 조회
        int price1 = statefulService1.getPrice();
        System.out.println("price1 = " + price1);

        assertThat(price1).isEqualTo(20000);
    }

    static class TestConfig {

        @Bean
        public StatefulService statefulService() {
            return new StatefulService();
        }

    }

}
