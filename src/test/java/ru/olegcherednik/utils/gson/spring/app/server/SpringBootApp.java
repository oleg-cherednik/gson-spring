package ru.olegcherednik.utils.gson.spring.app.server;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

/**
 * @author Oleg Cherednik
 * @since 30.01.2021
 */
@SpringBootApplication//(exclude = { TransactionAutoConfiguration.class, DataSourceTransactionManagerAutoConfiguration.class })
//(exclude = GsonAutoConfiguration.class)
@Import(SpringBootApp.Config.class)
public class SpringBootApp {

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
