package lesson3.task1;

import java.util.*;

public class Task1 {
    static void toLower(String[] array) {
        for (int i = 0; i < array.length; i++) {
            array[i] = array[i].toLowerCase();
        }
    }

    static Set<String> getSet(String[] array) {
        Set<String> set = new HashSet<String>();
        set.addAll(Arrays.asList(array));
        return set;
    }

    static Map<String, Integer> getMap(String[] array) {
        Map<String, Integer> map = new HashMap<String, Integer>();
        Set<String> set = getSet(array);
        for(String s : set)
            map.put(s, 0);
        for(String s : array)
            map.put(s, map.get(s) + 1);
        return map;
    }
}
