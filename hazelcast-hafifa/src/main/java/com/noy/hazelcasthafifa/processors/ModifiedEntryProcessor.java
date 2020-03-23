package com.noy.hazelcasthafifa.processors;

import java.util.Map;

import com.hazelcast.core.Offloadable;
import com.hazelcast.map.AbstractEntryProcessor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ModifiedEntryProcessor extends AbstractEntryProcessor<Long, String> implements Offloadable {
    private static final long serialVersionUID = 1L;
    private Logger logger = LoggerFactory.getLogger(ModifiedEntryProcessor.class);

    @Override
    public Object process(Map.Entry<Long, String> entry) {
        String modifiedValue = entry.getValue() + " Modified!";
        logger.info("Before Sleep for key -----> " + entry.getKey() + ", original value: " + entry.getValue() + ", modified value: " + modifiedValue);
        try {
            Thread.sleep(10000);
            entry.setValue(modifiedValue);
            logger.info("After Sleep for key -----> " + entry.getKey());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String getExecutorName() {
        return "test-exec";
    }
}