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

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.testng.annotations.Test;
import ru.olegcherednik.gson.spring.app.dto.Book;
import ru.olegcherednik.gson.utils.GsonUtils;
import ru.olegcherednik.gson.utils.GsonUtilsBuilder;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Oleg Cherednik
 * @since 17.01.2021
 */
@Test
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = SpringBootPrettyPrintConfigTest.SpringBootApp.class)
public class SpringBootPrettyPrintConfigTest extends BaseClientTest {

    public void shouldUsePrettyPrintJsonWhenPrettyPrintConfigured() {
        Book book = new Book("title", "author");
        String actual = gson.toJson(book);
        String notPrettyPrintJson = GsonUtils.print().writeValue(book);
        assertThat(actual.length()).isGreaterThan(notPrettyPrintJson.length());
    }

    @SuppressWarnings("EmptyClass")
    @SpringBootApplication
    @Import(SpringBootApp.Config.class)
    public static class SpringBootApp {

        @SuppressWarnings("InnerClassTooDeeplyNested")
        public static class Config {

            @Bean
            public GsonUtilsBuilderCustomizer customGsonUtilsBuilderCustomizer() {
                return new GsonUtilsBuilderCustomizer() {

                    @Override
                    public boolean isPrettyPrint() {
                        return true;
                    }

                    @Override
                    public void accept(GsonUtilsBuilder builder) {
                    }

                    @Override
                    public int getOrder() {
                        return DEFAULT_PRIORITY;
                    }
                };
            }

        }

    }

}
