package ru.olegcherednik.utils.gson.spring;

import com.google.gson.Gson;
import feign.Feign;
import feign.Logger;
import feign.okhttp.OkHttpClient;
import feign.spring.SpringContract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import ru.olegcherednik.utils.gson.GsonDecorator;
import ru.olegcherednik.utils.gson.feign.GsonDecoder;
import ru.olegcherednik.utils.gson.feign.GsonEncoder;

public abstract class BaseClientTest extends AbstractTransactionalTestNGSpringContextTests {

    @LocalServerPort
    protected int randomServerPort;
    @Autowired
    protected Gson gson;

    protected final <T> T buildClient(Class<T> clientClass) {
        GsonDecorator gson = new GsonDecorator(this.gson);

        return Feign.builder()
                    .contract(new SpringContract())
                    .client(new OkHttpClient())
                    .encoder(new GsonEncoder(gson))
                    .decoder(new GsonDecoder(gson))
                    .decode404()
                    .logLevel(Logger.Level.FULL)
                    .target(clientClass, String.format("http://localhost:%d", randomServerPort));
    }

}
