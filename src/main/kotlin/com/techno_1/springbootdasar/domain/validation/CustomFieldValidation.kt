package com.techno_1.springbootdasar.domain.validation

import com.techno_1.springbootdasar.domain.validation.validator.FieldValidator
import java.lang.annotation.Documented
import java.lang.annotation.ElementType
import java.lang.annotation.RetentionPolicy
import javax.validation.Constraint
import javax.validation.Payload
import kotlin.reflect.KClass


@Documented
@Constraint(validatedBy = [FieldValidator::class])
@Target(
//    ElementType.METHOD,
//    ElementType.FIELD,
//    ElementType.ANNOTATION_TYPE,
//    ElementType.CONSTRUCTOR,
//    ElementType.PARAMETER,
//    ElementType.TYPE_USE

    AnnotationTarget.FUNCTION,
    AnnotationTarget.PROPERTY_GETTER,
    AnnotationTarget.PROPERTY_SETTER,
    AnnotationTarget.FIELD,
    AnnotationTarget.CONSTRUCTOR,
    AnnotationTarget.VALUE_PARAMETER,
    AnnotationTarget.TYPE_PARAMETER
)
//@Retention(RetentionPolicy.RUNTIME)
@Retention(AnnotationRetention.RUNTIME)
annotation class CustomFieldValidation(
    val message: String = "Default Message",

    val groups: Array<KClass<Any>> = [],

    val payload: Array<KClass<Payload>> = []

)
