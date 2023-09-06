package com.backend.guhbackend.utils;

import java.util.*;

public class CollectionFunctions {


    public static <K, V> Optional<Map.Entry<K, V>> getLastEntry(LinkedHashMap<K, V> map) {
        if (map.isEmpty()) {
            return Optional.empty();
        } else {
            int lastIndex = (map.size() - 1);
            return map.entrySet().stream().skip(lastIndex).findFirst();
        }
    }

    public static <K, V> Object getLastEntryValue(LinkedHashMap<K, V> map){
        Optional<Map.Entry<K, V>> lastEntryOptional = getLastEntry(map);
        if(lastEntryOptional.isPresent()){
            return getLastEntry(map).get().getValue();
        } else {
            return null;
        }
    }

    public static <K, V> Object getLastEntryKey(LinkedHashMap<K, V> map){
        Optional<Map.Entry<K, V>> lastEntryOptional = getLastEntry(map);
        if(lastEntryOptional.isPresent()){
            return getLastEntry(map).get().getKey();
        } else {
            return null;
        }
    }
}
