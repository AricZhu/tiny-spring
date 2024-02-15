package com.spring.tiny.core.io;

import cn.hutool.core.lang.Assert;
import com.spring.tiny.util.ClassUtils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

public class ClassPathResource implements Resource {
    private final String path;
    private final ClassLoader classLoader;

    public ClassPathResource(String path) {
        this(path, null);
    }

    public ClassPathResource(String path, ClassLoader classLoader) {
        Assert.notNull(path, "Path must not be null");

        this.path = path;
        this.classLoader = Objects.isNull(classLoader) ? ClassUtils.getDefaultClassLoader() : classLoader;
    }

    @Override
    public InputStream getInputStream() throws IOException {
        InputStream resourceAsStream = this.classLoader.getResourceAsStream(this.path);
        if (resourceAsStream == null) {
            throw new FileNotFoundException(this.path + " cannot be opened because it does not exist");
        }
        return resourceAsStream;
    }
}
