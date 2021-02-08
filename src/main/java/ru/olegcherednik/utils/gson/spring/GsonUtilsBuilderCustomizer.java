package ru.olegcherednik.utils.gson.spring;

import org.springframework.core.Ordered;
import ru.olegcherednik.utils.gson.GsonUtilsBuilder;

import java.util.function.Consumer;

/**
 * @author Oleg Cherednik
 * @since 07.02.2021
 */
public interface GsonUtilsBuilderCustomizer extends Consumer<GsonUtilsBuilder>, Ordered {

    boolean isPrettyPrint();

    @Override
    default int getOrder() {
        return 0;
    }

}
