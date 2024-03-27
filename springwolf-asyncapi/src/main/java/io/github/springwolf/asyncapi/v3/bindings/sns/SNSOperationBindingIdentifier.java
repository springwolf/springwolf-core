// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.asyncapi.v3.bindings.sns;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * We provide an Identifer Object to support providing the identifier of an externally defined endpoint for this SNS
 * publication to target, or an endpoint on another binding against this Operation Object (via the name field).
 *
 * @see <a href="https://github.com/asyncapi/bindings/tree/master/sns#identifier">SNS Operation Identifier</a>
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class SNSOperationBindingIdentifier {
    /**
     * Optional. The endpoint is a URL
     */
    @JsonProperty("url")
    private String url;
    /**
     * Optional. The endpoint is an email address
     */
    @JsonProperty("email")
    private String email;
    /**
     * Optional. The endpoint is a phone number
     */
    @JsonProperty("phone")
    private String phone;
    /**
     * Optional. The target is an ARN. For example, for SQS, the identifier may be an ARN, which will be of the form:
     * "arn:aws:sqs:{region}:{account-id}:{queueName}"
     */
    @JsonProperty("arn")
    private String arn;
    /**
     * Optional. The endpoint is identified by a name, which corresponds to an identifying field called 'name' of a
     * binding for that protocol on this publish Operation Object. For example, if the protocol is 'sqs' then the name
     * refers to the name field sqs binding. We don't use $ref because we are referring, not including.
     */
    @JsonProperty("name")
    private String name;
}
