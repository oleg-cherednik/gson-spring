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
