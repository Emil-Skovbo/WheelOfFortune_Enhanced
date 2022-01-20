package MeeW.intern.WheelOfFortune;

import MeeW.intern.WheelOfFortune.controller.WheelOfFortuneController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class WheelOfFortuneApplicationTests {

    @Autowired
    private WheelOfFortuneController controller;


    @Test
    void contextLoads() {
        assertThat(controller).isNotNull();
        assertThat(controller).isNotNull();

    }

}
