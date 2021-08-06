package ru.olegcherednik.gson.spring;

import org.springframework.core.Ordered;
import ru.olegcherednik.gson.utils.GsonUtilsBuilder;

/**
 * @author Oleg Cherednik
 * @since 07.02.2021
 */
public interface GsonUtilsBuilderCustomizer extends Ordered {

    int HIGHEST_PRIORITY = Integer.MIN_VALUE;
    int DEFAULT_PRIORITY = 0;

    boolean isPrettyPrint();

    void accept(GsonUtilsBuilder builder);

}
