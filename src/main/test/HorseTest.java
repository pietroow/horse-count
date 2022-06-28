package main.test;

import main.java.HorseCountService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class HorseTest {

    private final HorseCountService service = new HorseCountService();

    @Test
    public void testHorses_scenario1() {
        Integer horses = service.countHorses("nei");
        Assertions.assertEquals(0, horses);
    }

    @Test
    public void testHorses_scenario2() {
        Integer horses = service.countHorses("neingeighh");
        Assertions.assertEquals(2, horses);
    }

    @Test
    public void testHorses_scenario3() {
        Integer horses = service.countHorses("enighgheneigh");
        Assertions.assertEquals(1, horses);
    }

    @Test
    public void testHorses_scenario4() {
        Integer horses = service.countHorses("exighghxnexxx");
        Assertions.assertEquals(0, horses);
    }

}