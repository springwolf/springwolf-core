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
import kotlin.reflect.KProperty1
import kotlin.reflect.full.findAnnotation
import kotlin.reflect.full.isSubclassOf
import kotlin.reflect.full.memberProperties
import kotlin.reflect.jvm.javaType
import kotlin.reflect.jvm.jvmErasure

class KotlinxSerializationModelConverter(private val useFqn: Boolean = false) : ModelConverter {

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

    private fun getPropertySchema(property: KProperty1<*, *>, context: ModelConverterContext): Schema<*> {
        val propertySchema: Schema<*>

       val name = getPropertyName(property)

        val propertyType = property.returnType.jvmErasure

        when {
            propertyType.isSubclassOf(List::class) -> {
                propertySchema = ArraySchema()
                val value = (property.returnType.javaType as ParameterizedType).actualTypeArguments[0]
                propertySchema.items = resolveRefSchema(value, context)
            }

            propertyType.isSubclassOf(Set::class) -> {
                propertySchema = ArraySchema()
                val value = (property.returnType.javaType as ParameterizedType).actualTypeArguments[0]
                propertySchema.items = resolveRefSchema(value, context)
                propertySchema.uniqueItems = true
            }

            propertyType.isSubclassOf(Map::class) -> {
                val mapType = (property.returnType.javaType as ParameterizedType)
                val value = mapType.actualTypeArguments[1]
                propertySchema = MapSchema().additionalProperties(context.resolve(AnnotatedType(value)))
            }

            propertyType.isSubclassOf(Byte::class) -> {
                propertySchema = IntegerSchema()
            }

            else -> {
                propertySchema = resolveRefSchema(property.returnType.javaType, context)
            }
        }
        propertySchema.nullable = property.returnType.isMarkedNullable
        propertySchema.name = name

        return propertySchema
    }

    private fun resolveRefSchema(type: Type, context: ModelConverterContext): Schema<*> {
        val typeSchema = context.resolve(AnnotatedType(type))
        if (typeSchema.type == "object") {
            val name = getClassName(type as Class<*>)
            context.defineModel(name, typeSchema)
            return Schema<Any>().`$ref`(RefUtils.constructRef(name))
        }
        return typeSchema
    }

    private fun getClassName(type: Class<*>): String {
        val qualifiedName = type.name
        return if (useFqn) {
            qualifiedName
        } else {
            qualifiedName.substringAfterLast(".")
        }
    }
}
