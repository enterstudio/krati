package test.retention;

import java.io.File;
import java.util.UUID;

import krati.core.StoreConfig;
import krati.io.Serializer;
import krati.io.serializer.StringSerializerUtf8;
import krati.retention.Retention;
import krati.retention.RetentionConfig;
import krati.retention.SimpleRetention;
import krati.store.DataStore;
import krati.store.factory.DynamicObjectStoreFactory;
import krati.store.factory.ObjectStoreFactory;

/**
 * TestRetentionStoreReader
 * 
 * @author jwu
 * @since 110/20, 2011
 */
public class TestRetentionStoreReader extends AbstractTestRetentionStoreReader<String, String> {
    
    @Override
    protected Serializer<String> createEventValueSerializer() {
        return new StringSerializerUtf8();
    }
    
    @Override
    protected Retention<String> createRetention() throws Exception {
        RetentionConfig<String> config = new RetentionConfig<String>(getId(), getHomeDir());
        config.setBatchSize(getEventBatchSize());
        config.setRetentionPolicy(createRetentionPolicy());
        config.setEventValueSerializer(createEventValueSerializer());
        config.setEventClockSerializer(createEventClockSerializer());
        config.setRetentionSegmentFileSizeMB(16);
        
        return new SimpleRetention<String>(config);
    }
    
    @Override
    protected DataStore<String, String> createStore() throws Exception {
        StoreConfig config = new StoreConfig(new File(getHomeDir(), "store"), 10000);
        config.setSegmentFileSizeMB(16);
        config.setNumSyncBatches(10);
        config.setBatchSize(100);
        
        ObjectStoreFactory<String, String> factory = new DynamicObjectStoreFactory<String, String>();
        return factory.create(config, new StringSerializerUtf8(), new StringSerializerUtf8());
    }
    
    @Override
    protected String nextKey() {
        return UUID.randomUUID().toString();
    }
    
    @Override
    protected String nextValue() {
        return "value." + UUID.randomUUID().toString();
    }
    
    @Override
    protected boolean checkKeyEquality(String key1, String key2) {
        return key1.equals(key2);
    }
    
    @Override
    protected boolean checkValueEquality(String value1, String value2) {
        return value1.equals(value2);
    }
}
