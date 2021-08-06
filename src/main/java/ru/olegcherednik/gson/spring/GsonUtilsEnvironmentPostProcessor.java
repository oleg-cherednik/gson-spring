/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package ru.olegcherednik.gson.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.gson.GsonAutoConfiguration;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.MutablePropertySources;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @author Oleg Cherednik
 * @since 06.02.2021
 */
public class GsonUtilsEnvironmentPostProcessor implements EnvironmentPostProcessor {

    public static final String SPRING_EXCLUDE_PROPERTY = "spring.autoconfigure.exclude";
    public static final String PROPERTY_SOURCE_NAME = GsonUtilsEnvironmentPostProcessor.class.getCanonicalName();
    public static final Pattern COMMA = Pattern.compile("\\s*,\\s*");

    // ---------- EnvironmentPostProcessor ----------

    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        Deque<String> parts = getExistingExcludes(environment);

        if (!parts.contains(GsonAutoConfiguration.class.getCanonicalName()))
            parts.addFirst(GsonAutoConfiguration.class.getCanonicalName());

        overrideValues(environment, Collections.singletonMap(SPRING_EXCLUDE_PROPERTY, String.join(",", parts)));
    }

    protected Deque<String> getExistingExcludes(ConfigurableEnvironment environment) {
        return Arrays.stream(COMMA.split(environment.getProperty(SPRING_EXCLUDE_PROPERTY, "")))
                     .filter(Objects::nonNull)
                     .filter(str -> !str.trim().isEmpty())
                     .collect(Collectors.toCollection(ArrayDeque::new));
    }

    protected static void overrideValues(ConfigurableEnvironment environment, Map<String, Object> properties) {
        MutablePropertySources propertySources = environment.getPropertySources();
        MapPropertySource propertySource = Optional.ofNullable((MapPropertySource)propertySources.get(PROPERTY_SOURCE_NAME))
                                                   .orElseGet(() -> new MapPropertySource(PROPERTY_SOURCE_NAME, new HashMap<>()));
        propertySource.getSource().putAll(properties);
        propertySources.remove(PROPERTY_SOURCE_NAME);
        propertySources.addFirst(propertySource);
    }

}

