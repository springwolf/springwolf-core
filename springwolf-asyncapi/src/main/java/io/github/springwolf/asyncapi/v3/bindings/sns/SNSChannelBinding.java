// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.asyncapi.v3.bindings.sns;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.springwolf.asyncapi.v3.bindings.ChannelBinding;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * This object contains information about the channel representation in SNS.
 * </p>
 * We represent an AsyncAPI Channel with a Topic in SNS. The bindings here allow definition of a topic within SNS. We
 * provide properties on the binding that allow creation of a topic in infrastructure-as-code scenarios. Be aware that
 * although the binding offers that flexibility, it may be more maintainable to specify properties such as SNS
 * Access Control Policy outside of AsyncAPI.
 * </p>
 * SNS supports many optional properties. To mark a channel as SNS, but use default values for the channel properties,
 * just use an empty object {}.
 *
 * @see <a href="https://github.com/asyncapi/bindings/blob/master/sns/README.md#channel">SNS Channel</a>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class SNSChannelBinding extends ChannelBinding {
    /**
     * Required. The name of the topic. Can be different from the channel name to allow flexibility around AWS resource
     * naming limitations.
     */
    @NotNull
    @JsonProperty("name")
    private String name;

    /**
     * Optional. By default, we assume an unordered SNS topic. This field allows configuration of a FIFO SNS Topic.
     */
    @JsonProperty("ordering")
    private SNSChannelBindingOrdering ordering;

    /**
     * Optional. The security policy for the SNS Topic
     */
    @JsonProperty("policy")
    private SNSChannelBindingPolicy policy;

    /**
     * Optional. Key-value pairs that represent AWS tags on the topic.
     */
    @JsonProperty("tags")
    private Map<String, String> tags;

    /**
     * Optional, defaults to latest. The version of this binding.
     */
    @Builder.Default
    @JsonProperty("bindingVersion")
    private String bindingVersion = "0.1.0";
}
