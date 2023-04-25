package uz.pdp.springadvanced;

import java.util.concurrent.TimeUnit;

public class JunitAnnotationSample {

    public int m1(int a) {
        if (a < 0) {
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return 0;
        }
        return a * 2;
    }

}
