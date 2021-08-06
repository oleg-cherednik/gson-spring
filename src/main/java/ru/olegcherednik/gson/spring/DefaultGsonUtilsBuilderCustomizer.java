/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package ru.olegcherednik.gson.spring;

import org.springframework.boot.context.properties.PropertyMapper;
import ru.olegcherednik.gson.utils.GsonUtilsBuilder;

/**
 * @author Oleg Cherednik
 * @since 06.02.2021
 */
public class DefaultGsonUtilsBuilderCustomizer implements GsonUtilsBuilderCustomizer {

    private final GsonUtilsProperties properties;

    public DefaultGsonUtilsBuilderCustomizer(GsonUtilsProperties properties) {
        this.properties = properties;
    }

    @Override
    public boolean isPrettyPrint() {
        return properties.getPrettyPrinting() == Boolean.TRUE;
    }

    @Override
    public void accept(GsonUtilsBuilder builder) {
        PropertyMapper map = PropertyMapper.get().alwaysApplyingWhenNonNull();

        // ---------- GsonBuilder ----------

//        map.from(properties::getVersion).to(builder::setVersion);
        map.from(properties::getExcludeFieldsWithModifiers).to(fieldModifiers -> {
            int[] modifiers = fieldModifiers.stream()
                                            .mapToInt(GsonUtilsProperties.FieldModifier::code)
                                            .toArray();
            if (modifiers.length != 0)
                builder.excludeFieldsWithModifiers(modifiers);
        });
        map.from(properties::getGenerateNonExecutableJson).toCall(builder::generateNonExecutableJson);
        map.from(properties::getExcludeFieldsWithoutExposeAnnotation).toCall(builder::excludeFieldsWithoutExposeAnnotation);
        map.from(properties::getSerializeNulls).toCall(builder::serializeNulls);
        map.from(properties::getDisableInnerClassSerialization).toCall(builder::disableInnerClassSerialization);
//        map.from(properties::getLongSerializationPolicy).to(builder::setLongSerializationPolicy);
//        map.from(properties::getFieldNamingPolicy).to(builder::setFieldNamingPolicy);
        map.from(properties::getLenient).toCall(builder::setLenient);
        map.from(properties::getDisableHtmlEscaping).toCall(builder::disableHtmlEscaping);
        map.from(properties::getSerializeSpecialFloatingPointValues).toCall(builder::serializeSpecialFloatingPointValues);
    }

    @Override
    public int getOrder() {
        return HIGHEST_PRIORITY;
    }

}
