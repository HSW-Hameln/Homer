package de.hsw.konsens.homer;

import org.testng.annotations.Test;

/**
 * Created by mielke on 14.07.2014.
 */

public class HOMERTest {
    @Test
    public void searchTest() {
        new HOMER().search("hallo");
    }
}
