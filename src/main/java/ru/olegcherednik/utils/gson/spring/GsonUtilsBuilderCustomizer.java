package ru.olegcherednik.utils.gson.spring;

import org.springframework.core.Ordered;
import ru.olegcherednik.utils.gson.GsonUtilsBuilder;

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
