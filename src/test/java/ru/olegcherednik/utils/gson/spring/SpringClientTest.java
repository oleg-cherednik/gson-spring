package ru.olegcherednik.utils.gson.spring;

import org.springframework.boot.test.context.SpringBootTest;
import ru.olegcherednik.utils.gson.spring.app.server.SpringBootApp;

@SuppressWarnings("EmptyClass")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = SpringBootApp.class)
public abstract class SpringClientTest extends BaseClientTest {

}
