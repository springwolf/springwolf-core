// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.examples.kafka.configuration;

import com.google.protobuf.Descriptors.Descriptor;
import com.google.protobuf.Descriptors.FieldDescriptor;
import com.google.protobuf.Message;
import io.swagger.v3.core.converter.AnnotatedType;
import io.swagger.v3.core.converter.ModelConverter;
import io.swagger.v3.core.converter.ModelConverterContext;
import io.swagger.v3.oas.models.media.ArraySchema;
import io.swagger.v3.oas.models.media.BooleanSchema;
import io.swagger.v3.oas.models.media.IntegerSchema;
import io.swagger.v3.oas.models.media.MapSchema;
import io.swagger.v3.oas.models.media.NumberSchema;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.media.StringSchema;
import tools.jackson.databind.JavaType;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;

class ProtobufModelConverter implements ModelConverter {

    private final boolean useFqn;

    ProtobufModelConverter(boolean useFqn) {
        this.useFqn = useFqn;
    }

    @Override
    public Schema resolve(AnnotatedType annotatedType, ModelConverterContext context, Iterator<ModelConverter> chain) {
        if (annotatedType == null) {
            return null;
        }
        Class<?> rawClass = annotatedType.getType() instanceof JavaType javaType
                ? javaType.getRawClass()
                : annotatedType.getType() instanceof Class<?> type ? type : null;
        if (rawClass == null || !Message.class.isAssignableFrom(rawClass)) {
            return chain.hasNext() ? chain.next().resolve(annotatedType, context, chain) : null;
        }
        Descriptor descriptor = defaultInstance(rawClass).getDescriptorForType();
        Schema wellKnownTypeSchema = wellKnownTypeSchema(descriptor);
        if (wellKnownTypeSchema != null) {
            return wellKnownTypeSchema;
        }

        String name = modelName(rawClass);
        Schema model = new Schema<>().name(name).title(rawClass.getSimpleName()).type("object");
        model.setProperties(new LinkedHashMap<>());

        for (FieldDescriptor field : descriptor.getFields()) {
            Method getter = getter(rawClass, field);
            Class<?> javaType = valueClass(getter, field);
            Schema property = schema(field, javaType, context);
            if (field.isRepeated() && !field.isMapField()) {
                property = new ArraySchema().items(property);
            } else if (field.isMapField()) {
                FieldDescriptor valueField = field.getMessageType().findFieldByNumber(2);
                Class<?> valueType = valueClass(getter, field, 1);
                property = new MapSchema().additionalProperties(schema(valueField, valueType, context));
            }
            model.getProperties().put(field.getJsonName(), property);
        }

        context.defineModel(name, model, annotatedType, null);
        if (annotatedType.isResolveAsRef()) {
            return new Schema<>().$ref("#/components/schemas/" + name);
        }
        return model;
    }

    private Schema schema(FieldDescriptor field, Class<?> javaType, ModelConverterContext context) {
        return switch (field.getType()) {
            case BOOL -> new BooleanSchema();
            case BYTES -> new StringSchema().format("byte");
            case DOUBLE -> new NumberSchema().format("double");
            case FLOAT -> new NumberSchema().format("float");
            case INT32, SINT32, SFIXED32, UINT32, FIXED32 -> new IntegerSchema().format("int32");
            case INT64, SINT64, SFIXED64, UINT64, FIXED64 -> new IntegerSchema().format("int64");
            case STRING -> new StringSchema();
            case ENUM -> enumSchema(field, javaType);
            case MESSAGE, GROUP -> messageSchema(field, javaType, context);
        };
    }

    private Schema enumSchema(FieldDescriptor field, Class<?> enumClass) {
        StringSchema schema = new StringSchema();
        schema.setTitle(field.getEnumType().getName());
        if (enumClass.isEnum()) {
            schema.setEnum(Arrays.stream(enumClass.getEnumConstants())
                    .map(value -> ((Enum<?>) value).name())
                    .toList());
        } else {
            schema.setEnum(field.getEnumType().getValues().stream()
                    .map(value -> value.getName())
                    .toList());
        }
        return schema;
    }

    private Schema messageSchema(FieldDescriptor field, Class<?> messageClass, ModelConverterContext context) {
        String fullName = field.getMessageType().getFullName();
        if ("google.protobuf.Timestamp".equals(fullName)) {
            return new StringSchema().format("date-time");
        }
        if ("google.protobuf.Duration".equals(fullName) || "google.protobuf.FieldMask".equals(fullName)) {
            return new StringSchema();
        }
        return context.resolve(new AnnotatedType().type(messageClass).resolveAsRef(true));
    }

    private Method getter(Class<?> messageClass, FieldDescriptor field) {
        String suffix = Character.toUpperCase(field.getJsonName().charAt(0))
                + field.getJsonName().substring(1);
        String methodName = "get" + suffix + (field.isMapField() ? "Map" : field.isRepeated() ? "List" : "");
        try {
            return messageClass.getMethod(methodName);
        } catch (NoSuchMethodException e) {
            throw new IllegalStateException("Unable to find protobuf accessor " + methodName, e);
        }
    }

    private Class<?> valueClass(Method getter, FieldDescriptor field) {
        return valueClass(getter, field, field.isMapField() ? 1 : 0);
    }

    private Class<?> valueClass(Method getter, FieldDescriptor field, int typeArgumentIndex) {
        if (!field.isRepeated()) {
            return getter.getReturnType();
        }
        Type genericReturnType = getter.getGenericReturnType();
        if (!(genericReturnType instanceof ParameterizedType parameterizedType)
                || !(parameterizedType.getActualTypeArguments()[typeArgumentIndex] instanceof Class<?> valueType)) {
            throw new IllegalStateException("Unable to determine protobuf element type for " + getter);
        }
        return valueType;
    }

    private Message defaultInstance(Class<?> type) {
        try {
            return (Message) type.getMethod("getDefaultInstance").invoke(null);
        } catch (ReflectiveOperationException e) {
            throw new IllegalStateException("Unable to get protobuf default instance for " + type.getName(), e);
        }
    }

    private Schema wellKnownTypeSchema(Descriptor descriptor) {
        return switch (descriptor.getFullName()) {
            case "google.protobuf.Timestamp" -> new StringSchema().format("date-time");
            case "google.protobuf.Duration", "google.protobuf.FieldMask" -> new StringSchema();
            default -> null;
        };
    }

    private String modelName(Class<?> type) {
        io.swagger.v3.oas.annotations.media.Schema schemaAnnotation =
                type.getAnnotation(io.swagger.v3.oas.annotations.media.Schema.class);
        if (schemaAnnotation != null && !schemaAnnotation.name().isBlank()) {
            return schemaAnnotation.name();
        }
        return useFqn ? type.getName().replace("$", ".") : type.getSimpleName();
    }
}
