package MeeW.intern.WheelOfFortune;

import MeeW.intern.WheelOfFortune.controller.WheelOfFortuneController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class WheelOfFortuneApplicationTests {

    @Autowired
    private WheelOfFortuneController wheelOfFortuneControllercontroller;

    @Autowired
    private WheelOfFortuneController fieldController;

    @Autowired
    private WheelOfFortuneController WinnerController;

    @Test
    void WheelOfFortuneControllerIsNotNull() {
        assertThat(wheelOfFortuneControllercontroller).isNotNull();
    }

    @Test
    void FieldControllerIsNotNull() {
        assertThat(fieldController).isNotNull();
    }

    @Test
    void WinnerControllerIsNotNull() {
        assertThat(WinnerController).isNotNull();
    }



}
