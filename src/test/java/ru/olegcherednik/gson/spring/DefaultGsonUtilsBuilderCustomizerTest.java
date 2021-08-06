package ru.olegcherednik.gson.spring;

import org.mockito.ArgumentCaptor;
import org.testng.annotations.Test;
import ru.olegcherednik.gson.utils.GsonUtilsBuilder;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author Oleg Cherednik
 * @since 25.02.2021
 */
@Test
@SuppressWarnings("NewClassNamingConvention")
public class DefaultGsonUtilsBuilderCustomizerTest {

    public void shouldRetrievePrettyPrintWhenPropertyIsTrue() {
        GsonUtilsProperties properties = mock(GsonUtilsProperties.class);
        DefaultGsonUtilsBuilderCustomizer customizer = new DefaultGsonUtilsBuilderCustomizer(properties);

        when(properties.getPrettyPrinting()).thenReturn(true);

        assertThat(customizer.isPrettyPrint()).isTrue();
    }

    public void shouldRetrieveNotPrettyPrintWhenPropertyIsNull() {
        GsonUtilsProperties properties = mock(GsonUtilsProperties.class);
        DefaultGsonUtilsBuilderCustomizer customizer = new DefaultGsonUtilsBuilderCustomizer(properties);

        when(properties.getPrettyPrinting()).thenReturn(null);
        assertThat(customizer.isPrettyPrint()).isFalse();
    }

    public void shouldRetrieveNotPrettyPrintWhenPropertyIsFalse() {
        GsonUtilsProperties properties = mock(GsonUtilsProperties.class);
        DefaultGsonUtilsBuilderCustomizer customizer = new DefaultGsonUtilsBuilderCustomizer(properties);

        when(properties.getPrettyPrinting()).thenReturn(false);
        assertThat(customizer.isPrettyPrint()).isFalse();
    }

    public void shouldRetrieveHighestPriorityWhenGetOrder() {
        DefaultGsonUtilsBuilderCustomizer customizer = new DefaultGsonUtilsBuilderCustomizer(mock(GsonUtilsProperties.class));
        assertThat(customizer.getOrder()).isEqualTo(Integer.MIN_VALUE);
    }

    public void shouldCallExcludeFieldsWithModifiersWhenPropertyHasNotEmptyExcludeFields() {
        GsonUtilsProperties properties = mock(GsonUtilsProperties.class);
        GsonUtilsBuilder builder = mock(GsonUtilsBuilder.class);

        when(properties.getExcludeFieldsWithModifiers()).thenReturn(Arrays.asList(
                GsonUtilsProperties.FieldModifier.PUBLIC,
                GsonUtilsProperties.FieldModifier.STRICT));

        ArgumentCaptor<int[]> captor = ArgumentCaptor.forClass(int[].class);

        DefaultGsonUtilsBuilderCustomizer customizer = new DefaultGsonUtilsBuilderCustomizer(properties);
        customizer.accept(builder);

        verify(builder, times(1)).excludeFieldsWithModifiers(captor.capture());
        List<int[]> actual = captor.getAllValues();
        assertThat(actual).isEqualTo(Arrays.asList(0x1, 0x800));
    }

    public void shouldNotCallExcludeFieldsWithModifiersWhenPropertyHasEmptyExcludeFields() {
        GsonUtilsProperties properties = mock(GsonUtilsProperties.class);
        GsonUtilsBuilder builder = mock(GsonUtilsBuilder.class);

        when(properties.getExcludeFieldsWithModifiers()).thenReturn(Collections.emptyList());

        DefaultGsonUtilsBuilderCustomizer customizer = new DefaultGsonUtilsBuilderCustomizer(properties);
        customizer.accept(builder);

        verify(builder, never()).excludeFieldsWithModifiers(any(int[].class));
    }

}
