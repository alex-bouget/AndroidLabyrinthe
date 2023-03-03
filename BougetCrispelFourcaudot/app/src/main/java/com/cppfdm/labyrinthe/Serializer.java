package com.cppfdm.labyrinthe;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Random;

/**
 * Used for passed an object under two activity
 * "Transform" Object into String
 */
public class Serializer {
    public static HashMap<String, Object> map = new HashMap<>();

    /**
     * Serialized an object
     *
     * @param o object to serialized
     * @return the name of serialized object
     */
    public static String addToSerializer(Object o) {
        byte[] serialized = new byte[10];
        new Random().nextBytes(serialized);
        String generated = new String(serialized, StandardCharsets.UTF_8);
        map.put(generated, o);
        return generated;
    }

    /**
     * Return an object serialized
     *
     * @param s the name of serialized object
     * @return the object
     */
    public static Object get(String s) {
        if (map.containsKey(s)) {
            Object o = map.get(s);
            map.remove(s);
            return o;
        }
        return null;

    }
}
