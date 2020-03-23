package com.noy.hazelcasthafifa.listeners;

import com.hazelcast.core.EntryEvent;
import com.hazelcast.core.EntryListener;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import com.hazelcast.core.MapEvent;
import com.hazelcast.map.listener.EntryLoadedListener;
import com.noy.hazelcasthafifa.processors.ModifiedEntryProcessor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ModifiedMapListener implements EntryListener<Long, String>, EntryLoadedListener<Long, String> {
                
    private Logger logger = LoggerFactory.getLogger(ModifiedMapListener.class);
    private final HazelcastInstance hazelcastInstance = Hazelcast.getHazelcastInstanceByName("hazelcast-instance");

    @Override
    public void entryAdded( EntryEvent<Long, String> event ) {
        logger.info( "Entry Added:" + event );
        IMap<Long, String> cache = hazelcastInstance.getMap("test-cache");
        cache.submitToKey(event.getKey(), new ModifiedEntryProcessor());
    }

    @Override
    public void entryRemoved( EntryEvent<Long, String> event ) {
        logger.info( "Entry Removed:" + event );
    }

    @Override
    public void entryUpdated( EntryEvent<Long, String> event ) {
        logger.info( "Entry Updated:" + event );
    }

    @Override
    public void entryEvicted( EntryEvent<Long, String> event ) {
        logger.info( "Entry Evicted:" + event );
    }

    @Override
    public void entryLoaded( EntryEvent<Long, String> event ) {
        logger.info( "Entry Loaded:" + event );
    }

    @Override
    public void mapEvicted( MapEvent event ) {
        logger.info( "Map Evicted:" + event );
    }

    @Override
    public void mapCleared( MapEvent event ) {
        logger.info( "Map Cleared:" + event );
    }
}