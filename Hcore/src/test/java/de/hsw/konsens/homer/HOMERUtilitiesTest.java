package de.hsw.konsens.homer;

import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

/**
 * Created by mielke on 11.06.2014.
 */
@Test
public class HOMERUtilitiesTest {
    public void indexDirTest() {
        try {
            HOMERUtilities.index(new File("C:\\Users\\mielke\\Desktop\\literature.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
