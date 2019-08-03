package com.javarush.task.task37.task3708.retrievers;

import com.javarush.task.task37.task3708.cache.LRUCache;
import com.javarush.task.task37.task3708.storage.Storage;

public class CachingProxyRetriever implements Retriever {
    private OriginalRetriever retriever;
    private LRUCache cache = new LRUCache(16);

    public CachingProxyRetriever(Storage storage) {
        this.retriever = new OriginalRetriever(storage);
    }

    @Override
    public Object retrieve(long id) {
        Object result = cache.find(id);
        if (result == null) {
            result = retriever.retrieve(id);
            cache.set(id, result);
        }
        return result;
    }
}
