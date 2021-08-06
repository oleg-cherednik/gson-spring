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

import com.google.gson.Gson;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import ru.olegcherednik.gson.utils.GsonUtilsBuilder;

import java.util.List;
import java.util.function.Supplier;

/**
 * @author Oleg Cherednik
 * @since 05.02.2021
 */
@Configuration(proxyBeanMethods = false)
@ConditionalOnClass(Gson.class)
@EnableConfigurationProperties(GsonUtilsProperties.class)
@Import(GsonUtilsWebMvcConfigurer.class)
public class GsonUtilsAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public Supplier<Gson> gsonUtilsBuilder(List<GsonUtilsBuilderCustomizer> customizers) {
        GsonUtilsBuilder builder = new GsonUtilsBuilder();
        boolean prettyPrint = GsonUtilsProperties.DEF_PRETTY_PRINT;

        for (GsonUtilsBuilderCustomizer customizer : customizers) {
            prettyPrint = customizer.isPrettyPrint();
            customizer.accept(builder);
        }

        return prettyPrint ? builder::prettyPrintGson : builder::gson;
    }

    @Bean
    @ConditionalOnMissingBean
    public Gson gson(Supplier<Gson> supplier) {
        return supplier.get();
    }

    @Bean
    public DefaultGsonUtilsBuilderCustomizer defaultGsonUtilsBuilderCustomizer(GsonUtilsProperties gsonProperties) {
        return new DefaultGsonUtilsBuilderCustomizer(gsonProperties);
    }

}
