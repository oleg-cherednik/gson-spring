package ru.olegcherednik.utils.gson.spring;

import com.google.gson.Gson;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * @author Oleg Cherednik
 * @since 08.02.2021
 */
public class GsonUtilsWebMvcConfigurer implements WebMvcConfigurer {

    private final Gson gson;

    public GsonUtilsWebMvcConfigurer(Gson gson) {
        this.gson = gson;
    }

    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(0, new GsonHttpMessageConverter(gson));
    }
}
