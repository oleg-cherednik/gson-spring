package ru.olegcherednik.gson.spring;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.testng.annotations.BeforeClass;

public abstract class BaseClientTest extends AbstractTransactionalTestNGSpringContextTests {

    @LocalServerPort
    protected int randomServerPort;
    @Autowired
    protected Gson gson;
    @Autowired
    private WebApplicationContext webApplicationContext;
    protected MockMvc mockMvc;

    @BeforeClass
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

}
