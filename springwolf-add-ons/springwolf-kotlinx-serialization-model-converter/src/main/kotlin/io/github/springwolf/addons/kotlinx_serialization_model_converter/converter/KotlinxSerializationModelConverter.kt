package io.github.springwolf.addons.kotlinx_serialization_model_converter.converter

import io.swagger.v3.core.converter.AnnotatedType
import io.swagger.v3.core.converter.ModelConverter
import io.swagger.v3.core.converter.ModelConverterContext
import io.swagger.v3.core.util.RefUtils
import io.swagger.v3.oas.models.media.ArraySchema
import io.swagger.v3.oas.models.media.IntegerSchema
import io.swagger.v3.oas.models.media.MapSchema
import io.swagger.v3.oas.models.media.ObjectSchema
import io.swagger.v3.oas.models.media.Schema
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type
import java.util.concurrent.ConcurrentHashMap
import kotlin.reflect.KProperty1
import kotlin.reflect.full.findAnnotation
import kotlin.reflect.full.memberProperties
import kotlin.reflect.jvm.javaType
import kotlin.reflect.jvm.jvmErasure

class KotlinxSerializationModelConverter(private val useFqn: Boolean = false) : ModelConverter {

    private val qualifiedNameMap: MutableMap<String, String> = ConcurrentHashMap()

    override fun resolve(
        annotatedType: AnnotatedType, context: ModelConverterContext, chain: Iterator<ModelConverter>
    ): Schema<*>? {
        if (annotatedType.type is Class<*>) {
            val kClass = (annotatedType.type as Class<*>).kotlin
            val isKotlinSerializable = kClass.findAnnotation<Serializable>() != null

            if (isKotlinSerializable) {
                val schema = ObjectSchema()
                schema.nullable = false
                schema.name = kClass.simpleName

                kClass.memberProperties.forEach { property ->
                    val propertySchema = getPropertySchema(property, context)

                    schema.addProperty(propertySchema.name, propertySchema)
                    if (!propertySchema.nullable) {
                        schema.addRequiredItem(propertySchema.name)
                    }
                }

                return schema
            }
        }

        return if ((chain.hasNext())) chain.next().resolve(annotatedType, context, chain) else null
    }

    private fun getPropertyName(property: KProperty1<*, *>): String {
        val serialNameAnnotation = property.findAnnotation<SerialName>()
        if (serialNameAnnotation != null) {
            return serialNameAnnotation.value
        }
        return property.name
    }

    private fun getPropertySchema(property: KProperty1<*,*>, context: ModelConverterContext) : Schema<*> {
        val propertySchema: Schema<*>

        val name = getPropertyName(property)

        if (property.returnType.jvmErasure.java.isAssignableFrom(List::class.java)) {
            propertySchema = ArraySchema()
            val value = (property.returnType.javaType as ParameterizedType).actualTypeArguments[0]
            propertySchema.items = resolveRefSchema(value, context)
        } else if (property.returnType.jvmErasure.java.isAssignableFrom(Set::class.java)) {
            propertySchema = ArraySchema()
            val value = (property.returnType.javaType as ParameterizedType).actualTypeArguments[0]
            propertySchema.items = resolveRefSchema(value, context)
            propertySchema.uniqueItems = true
        } else if (property.returnType.jvmErasure.java.isAssignableFrom(Map::class.java)) {
            val mapType = (property.returnType.javaType as ParameterizedType)
            val value = mapType.actualTypeArguments[1]
            propertySchema = MapSchema().additionalProperties(context.resolve(AnnotatedType(value)))
        } else if (property.returnType.jvmErasure.java.isAssignableFrom(Byte::class.java)) {
            propertySchema = IntegerSchema()
        } else {
            propertySchema = resolveRefSchema(property.returnType.javaType, context)
        }
        propertySchema.nullable = property.returnType.isMarkedNullable
        propertySchema.name = name

        return propertySchema
    }

    private fun resolveRefSchema(type: Type, context: ModelConverterContext) : Schema<*> {
        val typeSchema = context.resolve(AnnotatedType(type))
        if (typeSchema.type == "object") {
            val name = getName(type as Class<*>)
            context.defineModel(name, typeSchema)
            return Schema<Any>().`$ref`(RefUtils.constructRef(name))
        }
        return typeSchema
    }

    private fun getName(type: Class<*>): String {
        return if (useFqn) type.name else simplifyName(type.name)
    }

    private fun simplifyName(name: String): String {
        var name = name
        val qualifiedName = name

        if (name.isNotEmpty() && !Character.isUpperCase(name[0])) {
            name = removePrefix(name)
        }

        val simplifiedName = name
        var index = 0

        while (true) {
            val existingName = qualifiedNameMap.putIfAbsent(name, qualifiedName)

            if (existingName == null || existingName == qualifiedName || name == qualifiedName) {
                break
            } else {
                name = simplifiedName + (++index)
            }
        }

        return name
    }

    private fun removePrefix(name: String): String {
        var result = name
        var dotIndex = -1
        while (true) {
            dotIndex = result.indexOf('.', dotIndex + 1)
            if (dotIndex < 0 || dotIndex == name.length - 1) break
            if (Character.isUpperCase(name[dotIndex + 1])) {
                result = result.substring(dotIndex + 1)
                break
            }
        }
        return result
    }
}