package personal.nsl.termlist.container.domain;

import java.util.Random;

public class CodeGenerator {
    
    public static long generate(long lowerBound, long upperBound) {
        Random random = new Random(System.currentTimeMillis());
        long upperBoundInclusive = upperBound + 1;
        return random.nextLong(lowerBound, upperBoundInclusive);
    }
}
