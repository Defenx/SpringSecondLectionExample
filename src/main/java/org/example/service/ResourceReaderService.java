package org.example.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.example.dto.Orders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class ResourceReaderService implements ResourceLoaderAware {
    ResourceLoader resourceLoader;
    final ObjectMapper objectMapper;

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    public void readOrderList(String fileName) throws IOException {
        Resource resource = resourceLoader.getResource(fileName);
        var file = resource.getFile();
        var orders = objectMapper.readValue(file, Orders.class);
        System.out.println(orders);
    }
}
