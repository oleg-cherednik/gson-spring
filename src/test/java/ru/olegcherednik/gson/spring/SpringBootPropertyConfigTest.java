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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.testng.annotations.Test;
import ru.olegcherednik.gson.spring.app.server.BookController;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Oleg Cherednik
 * @since 17.01.2021
 */
@Test
@Import(BookController.class)
@PropertySource("classpath:spring.gson.properties")
public class SpringBootPropertyConfigTest extends SpringClientTest {

    @Autowired
    private GsonUtilsProperties properties;

    public void shouldReadPropertiesWhenPropertyExistsInConfigFile() {
        assertThat(properties.getVersion()).isEqualTo(666.14);
        assertThat(properties.getExcludeFieldsWithModifiers()).isEqualTo(Arrays.asList(
                GsonUtilsProperties.FieldModifier.PUBLIC,
                GsonUtilsProperties.FieldModifier.PRIVATE));
        assertThat(properties.getGenerateNonExecutableJson()).isTrue();
        assertThat(properties.getExcludeFieldsWithoutExposeAnnotation()).isTrue();
        assertThat(properties.getSerializeNulls()).isTrue();
        assertThat(properties.getDisableInnerClassSerialization()).isTrue();
//        assertThat(properties.getLongSerializationPolicy()).isSameAs(LongSerializationPolicy.DEFAULT);
//        assertThat(properties.getFieldNamingPolicy()).isSameAs(FieldNamingPolicy.IDENTITY);
        assertThat(properties.getLenient()).isTrue();
        assertThat(properties.getDisableHtmlEscaping()).isTrue();
        assertThat(properties.getSerializeSpecialFloatingPointValues()).isFalse();
        assertThat(properties.getPrettyPrinting()).isFalse();
    }

    @SuppressWarnings("EmptyClass")
    @SpringBootApplication
    public static class SpringBootApp {

    }

}
