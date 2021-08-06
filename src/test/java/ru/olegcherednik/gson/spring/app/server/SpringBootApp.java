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
package ru.olegcherednik.gson.spring.app.server;

import org.springframework.boot.SpringApplication;

/**
 * @author Oleg Cherednik
 * @since 30.01.2021
 */
//@SpringBootApplication
//        (exclude = {
//        TransactionAutoConfiguration.class,
//        DataSourceTransactionManagerAutoConfiguration.class,
//        GsonAutoConfiguration.class })
//(exclude = GsonAutoConfiguration.class)
//@Import(SpringBootApp.Config.class)
public class SpringBootApp {

    public static void main(String... args) {
        SpringApplication.run(SpringBootApp.class, args);
    }

    public static class Config {

//        @Bean
//        public Gson gson() {
//            return GsonHelper.createGson();
//        }
//
//        @Bean
//        public GsonBuilder gsonBuilder() {
//            return new GsonBuilder();
//        }
//
//        @Bean
//        public GsonDecorator gson(Gson gson) {
//            return new GsonDecorator(gson);
//        }

    }

}
