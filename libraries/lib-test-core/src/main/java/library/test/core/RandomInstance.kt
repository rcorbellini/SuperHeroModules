package library.test.core

import kotlin.random.Random
import kotlin.reflect.KClass
import kotlin.reflect.KType
import kotlin.reflect.KTypeParameter
import kotlin.reflect.typeOf

//By Marcin Moskala
// https://blog.kotlin-academy.com/creating-a-random-instance-of-any-class-in-kotlin-b6168655b64a
@ExperimentalStdlibApi
inline fun <reified T : Any> makeRandomInstance(
    random: Random = Random,
    config: MakeRandomInstanceConfig = MakeRandomInstanceConfig()
): T {
    val producer = RandomInstanceProducer(random, config)
    return producer.makeRandomInstance(T::class, typeOf<T>()) as T
}

class NoUsableConstructor : Error()

class MakeRandomInstanceConfig(
    var possibleCollectionSizes: IntRange = 1..5,
    var possibleStringSizes: IntRange = 1..10,
    var any: Any = "Anything"
)

class RandomInstanceProducer(
    private val random: Random,
    private val config: MakeRandomInstanceConfig
) {

    @ExperimentalStdlibApi
    private fun makeRandomInstanceForParam(
        paramType: KType,
        classRef: KClass<*>,
        type: KType
    ): Any? {
        val classifier = paramType.classifier
        return when (classifier) {
            is KClass<*> -> makeRandomInstance(classifier, paramType)
            is KTypeParameter -> {
                val typeParameterName = classifier.name
                val typeParameterId =
                    classRef.typeParameters.indexOfFirst { it.name == typeParameterName }
                val parameterType = type.arguments[typeParameterId].type ?: typeOf<Any>()
                makeRandomInstance(parameterType.classifier as KClass<*>, parameterType)
            }
            else -> throw Error("Type of the classifier $classifier is not supported")
        }
    }

    @ExperimentalStdlibApi
    fun makeRandomInstance(classRef: KClass<*>, type: KType): Any? {
        val primitive = makeStandardInstanceOrNull(classRef, type)
        if (primitive != null) {
            return primitive
        }

        val constructors = classRef.constructors.shuffled(random)

        for (constructor in constructors) {
            try {
                val arguments = constructor.parameters
                    .map { makeRandomInstanceForParam(it.type, classRef, type) }
                    .toTypedArray()

                return constructor.call(*arguments)
            } catch (e: Throwable) {
                e.printStackTrace()
                // no-op. We catch any possible error here that might occur during class creation
            }
        }

        throw NoUsableConstructor()
    }

    @ExperimentalStdlibApi
    @Suppress("IMPLICIT_CAST_TO_ANY")
    private fun makeStandardInstanceOrNull(classRef: KClass<*>, type: KType) = when (classRef) {
        Any::class -> config.any
        Int::class -> random.nextInt()
        Long::class -> random.nextLong()
        Double::class -> random.nextDouble()
        Float::class -> random.nextFloat()
        Char::class -> makeRandomChar(random)
        String::class -> makeRandomString(random)
        List::class, Collection::class -> makeRandomList(classRef, type)
        Map::class -> makeRandomMap(classRef, type)
        else -> null
    }

    @ExperimentalStdlibApi
    private fun makeRandomList(classRef: KClass<*>, type: KType): List<Any?> {
        val numOfElements = random.nextInt(
            config.possibleCollectionSizes.first,
            config.possibleCollectionSizes.last + 1
        )
        val elemType = type.arguments[0].type!!
        return (1..numOfElements)
            .map { makeRandomInstanceForParam(elemType, classRef, type) }
    }

    @ExperimentalStdlibApi
    private fun makeRandomMap(classRef: KClass<*>, type: KType): Map<Any?, Any?> {
        val numOfElements = random.nextInt(
            config.possibleCollectionSizes.first,
            config.possibleCollectionSizes.last + 1
        )
        val keyType = type.arguments[0].type!!
        val valType = type.arguments[1].type!!
        val keys = (1..numOfElements)
            .map { makeRandomInstanceForParam(keyType, classRef, type) }
        val values = (1..numOfElements)
            .map { makeRandomInstanceForParam(valType, classRef, type) }
        return keys.zip(values).toMap()
    }

    private fun makeRandomChar(random: Random) = ('A'..'z').random(random)
    private fun makeRandomString(random: Random) =
        (1..random.nextInt(config.possibleStringSizes.first, config.possibleStringSizes.last + 1))
            .map { makeRandomChar(random) }
            .joinToString(separator = "") { "$it" }
}