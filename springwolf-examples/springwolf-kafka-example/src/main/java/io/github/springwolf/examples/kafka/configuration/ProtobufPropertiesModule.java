// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.examples.kafka.configuration;

import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import tools.jackson.core.Version;
import tools.jackson.core.util.VersionUtil;
import tools.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.JavaType;
import tools.jackson.databind.PropertyNamingStrategies;
import tools.jackson.databind.SerializationConfig;
import tools.jackson.databind.cfg.MapperConfig;
import tools.jackson.databind.introspect.AnnotatedClass;
import tools.jackson.databind.introspect.AnnotatedClassResolver;
import tools.jackson.databind.introspect.BasicBeanDescription;
import tools.jackson.databind.introspect.BasicClassIntrospector;
import tools.jackson.databind.introspect.BeanPropertyDefinition;
import tools.jackson.databind.introspect.NopAnnotationIntrospector;
import tools.jackson.databind.introspect.VisibilityChecker;
import com.google.protobuf.Descriptors.Descriptor;
import com.google.protobuf.Descriptors.FieldDescriptor;
import com.google.protobuf.Message;
import com.google.protobuf.Timestamp;
import tools.jackson.databind.JacksonModule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * We use this class because there is no build version at maven central
 * Also available at https://github.com/innogames/springfox-protobuf/blob/master/src/main/java/com/innogames/springfox_protobuf/ProtobufPropertiesModule.java
 * ---
 *
 * Jackson module that works together with com.hubspot.jackson.datatype.protobuf.ProtobufModule.
 * When added to the springdoc/springfox ObjectMapper, it allows protobuf messages to show up in swagger ui.
 * <p>
 * Copyright (c) 2018 InnoGames GmbH  MIT license
 */
public class ProtobufPropertiesModule extends JacksonModule {
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(ProtobufPropertiesModule.class);

    private Map<Class<?>, Map<String, FieldDescriptor>> cache = new ConcurrentHashMap<>();

    @Override
    public String getModuleName() {
        return "ProtobufPropertyModule";
    }

    @Override
    public Version version() {
        return VersionUtil.versionFor(getClass());
    }

    @Override
    public void setupModule(SetupContext context) {

        context.setClassIntrospector(new ProtobufClassIntrospector());

        context.insertAnnotationIntrospector(annotationIntrospector);
    }

    NopAnnotationIntrospector annotationIntrospector = new NopAnnotationIntrospector() {

        @Override
        public VisibilityChecker<?> findAutoDetectVisibility(AnnotatedClass ac, VisibilityChecker<?> checker) {
            if (Message.class.isAssignableFrom(ac.getRawType())) {
                return checker.withGetterVisibility(Visibility.PUBLIC_ONLY).withFieldVisibility(Visibility.ANY);
            }
            return super.findAutoDetectVisibility(ac, checker);
        }

        @Override
        public Object findNamingStrategy(AnnotatedClass ac) {
            if (!Message.class.isAssignableFrom(ac.getRawType())) {
                return super.findNamingStrategy(ac);
            }

            return new PropertyNamingStrategies.NamingBase() {
                @Override
                public String translate(String propertyName) {
                    if (propertyName.endsWith("_")) {
                        return propertyName.substring(0, propertyName.length() - 1);
                    }
                    return propertyName;
                }
            };
        }
    };

    class ProtobufClassIntrospector extends BasicClassIntrospector {

        @Override
        public BasicBeanDescription forDeserialization(DeserializationConfig cfg, JavaType type, MixInResolver r) {
            BasicBeanDescription desc = super.forDeserialization(cfg, type, r);

            if (Message.class.isAssignableFrom(type.getRawClass())) {
                return protobufBeanDescription(cfg, type, r, desc);
            }

            return desc;
        }

        @Override
        public BasicBeanDescription forSerialization(SerializationConfig cfg, JavaType type, MixInResolver r) {
            BasicBeanDescription desc = super.forSerialization(cfg, type, r);

            if (Timestamp.class == type.getRawClass()) {
                return STRING_DESC;
            }
            if (Message.class.isAssignableFrom(type.getRawClass())) {
                return protobufBeanDescription(cfg, type, r, desc);
            }

            return desc;
        }

        private BasicBeanDescription protobufBeanDescription(
                MapperConfig<?> cfg, JavaType type, MixInResolver r, BasicBeanDescription baseDesc) {
            Map<String, FieldDescriptor> types = cache.computeIfAbsent(type.getRawClass(), this::getDescriptorForType);

            AnnotatedClass ac = AnnotatedClassResolver.resolve(cfg, type, r);

            List<BeanPropertyDefinition> props = new ArrayList<>();

            for (BeanPropertyDefinition p : baseDesc.findProperties()) {
                String name = p.getName();
                if (!types.containsKey(name)) {
                    continue;
                }

                if (p.hasField()
                        && p.getField().getType().isJavaLangObject()
                        && types.get(name).getType().equals(FieldDescriptor.Type.STRING)) {
                    addStringFormatAnnotation(p);
                }

                props.add(p.withSimpleName(name));
            }

            return new BasicBeanDescription(cfg, type, ac, new ArrayList<>(props)) {};
        }

        @JsonFormat(shape = Shape.STRING)
        class AnnotationHelper {}

        private void addStringFormatAnnotation(BeanPropertyDefinition p) {
            JsonFormat annotation = AnnotationHelper.class.getAnnotation(JsonFormat.class);
            p.getField().getAllAnnotations().addIfNotPresent(annotation);
        }

        private Map<String, FieldDescriptor> getDescriptorForType(Class<?> type) {
            try {
                Descriptor invoke = (Descriptor) type.getMethod("getDescriptor").invoke(null);
                Map<String, FieldDescriptor> descriptorsForType = new HashMap<>();
                invoke.getFields().stream().forEach(fieldDescriptor -> {
                    descriptorsForType.put(fieldDescriptor.getName(), fieldDescriptor);
                    descriptorsForType.put(fieldDescriptor.getJsonName(), fieldDescriptor);
                });
                return descriptorsForType;
            } catch (Exception e) {
                log.error("Error getting protobuf descriptor for swagger.", e);
                return new HashMap<>();
            }
        }
    }
}
