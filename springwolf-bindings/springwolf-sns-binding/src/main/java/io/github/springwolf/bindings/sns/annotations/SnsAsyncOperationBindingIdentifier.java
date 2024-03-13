// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.bindings.sns.annotations;

import io.github.springwolf.asyncapi.v3.bindings.sns.SNSOperationBindingIdentifier;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @see SNSOperationBindingIdentifier
 */
@Retention(RetentionPolicy.RUNTIME)
// ANNOTATION_TYPE only needed to be usable from SNS Plugin Annotation
@Target({ElementType.PARAMETER, ElementType.ANNOTATION_TYPE})
@Inherited
public @interface SnsAsyncOperationBindingIdentifier {
    /**
     * Optional. The endpoint is a URL
     */
    String url() default "";
    /**
     * Optional. The endpoint is an email address
     */
    String email() default "";
    /**
     * Optional. The endpoint is a phone number
     */
    String phone() default "";
    /**
     * Optional. The target is an ARN. For example, for SQS, the identifier may be an ARN, which will be of the form:
     * "arn:aws:sqs:{region}:{account-id}:{queueName}"
     */
    String arn() default "";
    /**
     * Optional. The endpoint is identified by a name, which corresponds to an identifying field called 'name' of a
     * binding for that protocol on this publish Operation Object. For example, if the protocol is 'sqs' then the name
     * refers to the name field sqs binding. We don't use $ref because we are referring, not including.
     */
    String name() default "";
}
