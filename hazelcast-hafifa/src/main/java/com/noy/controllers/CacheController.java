package com.noy.controllers;

import java.util.Map;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import com.noy.processors.ModifiedEntryProcessor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cache")
public class CacheController {

    private final HazelcastInstance hazelcastInstance;

    @Autowired
    public CacheController(HazelcastInstance hazelcastInstance) {
        this.hazelcastInstance = hazelcastInstance;
    }

    @GetMapping("/all")
    public Map<Long, String> getAll() {
        return hazelcastInstance.getMap("test-cache");
    }

    @GetMapping("/fill")
    public String fillCache() {
        IMap<Long, String> cache = hazelcastInstance.getMap("test-cache");
        for(long i=0; i<1000; i++) {
            cache.put(i, Long.toString(i));
            cache.submitToKey(i, new ModifiedEntryProcessor());
        }
        return "cache filled!";
    }

}