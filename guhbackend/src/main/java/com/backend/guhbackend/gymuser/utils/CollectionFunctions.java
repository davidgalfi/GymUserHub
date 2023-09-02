package com.backend.guhbackend.gymuser.utils;

import java.util.*;

public class CollectionFunctions {


    public static <K, V> Optional<Map.Entry<K, V>> getLastEntry(LinkedHashMap<K, V> map) {
        if (map.isEmpty()) {
            return Optional.empty();
        } else {
            int lastIndex = (map.size() - 1);
            Map.Entry<K, V> lastEntry = new ArrayList<>(map.entrySet()).get(lastIndex);
            return Optional.of(lastEntry);
        }
    }

    public static <K, V> Object getLastEntryValue(LinkedHashMap<K, V> map){
        Optional<Map.Entry<K, V>> lastEntryOptional = getLastEntry(map);
        if(lastEntryOptional.isPresent()){
            Map.Entry<K, V> lastEntry = getLastEntry(map).get();
            return lastEntry.getValue();
        } else {
            return null;
        }
    }

    public static <K, V> Object getLastEntryKey(LinkedHashMap<K, V> map){
        Optional<Map.Entry<K, V>> lastEntryOptional = getLastEntry(map);
        if(lastEntryOptional.isPresent()){
            Map.Entry<K, V> lastEntry = getLastEntry(map).get();
            return lastEntry.getKey();
        } else {
            return null;
        }
    }
}
