package il.george_nika.phrase2.service;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class RandomService {

    private static Random random  = new Random();

    public static int getRandom(int bound) {
        if (bound ==0){
            return 0;
        }
        return random.nextInt(bound);
    }

    public static int getRandomWithPriorityOnLast(int bound, int percentPriority){
        if (getRandom(2) == 0){
            return getRandom(bound);
        } else {
            return bound - 1 - getRandom(bound * percentPriority / 100);
        }
    }
}
