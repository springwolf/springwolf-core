package io.github.stavshamir.springwolf.asyncapi.scanners.channels;

import com.asyncapi.v2.binding.OperationBinding;
import com.asyncapi.v2.binding.stomp.STOMPOperationBinding;
import com.google.common.collect.ImmutableMap;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import org.springframework.util.StringValueResolver;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Map;


/**
 * Notes about STOMP-Specific problems/Implementation
 * (Spring Docs - https://docs.spring.io/spring-framework/docs/current/reference/html/web.html#websocket-stomp-message-mapping)
 *
 * @MessageMapping is only one of potential "Input" options, @ConnectMapping and @SubscriptionMapping are also valid.
 * Likely will use another Channel-Scanner for those two, since their AsyncAPI conversion is likely different.
 *
 * A confirmation that that the ProducerData handles STOMP is still required, but I think it will be fine.
 *
 * From my understanding @SendTo and @SendToUser are only valid for @COMMANDMapping functions/Classes
 * A @Service Helper Class should work just fine for helping support all the Mapping Annotations for these then.
 */
@Service
@RequiredArgsConstructor
public class MessageMappingChannelScanner extends AbstractChannelScanner<MessageMapping>
        implements ChannelsScanner, EmbeddedValueResolverAware {


    private StringValueResolver resolver;

    @Override
    public void setEmbeddedValueResolver(StringValueResolver resolver) {
        this.resolver = resolver;
    }

    @Override
    protected Class<MessageMapping> getListenerAnnotationClass() {
        return MessageMapping.class;
    }

    @Override
    protected String getChannelName(MessageMapping annotation) {
        // I need to check if Channels can Chain @MessageMapping in a @Controller. For Example
        //
        // @Controller
        // @MessageMapping("/shared")
        // public ExampleMessageController {
        //
        //   @MessageMapping("/foo") // Is this "/shared/foo"?
        //   public String getBar() { return "bar"; }
        //
        // }

        if (annotation.value().length > 0) {
            return annotation.value()[0];
        }
        return "";
    }

    @Override
    protected Map<String, ? extends OperationBinding> buildOperationBinding(MessageMapping annotation) {
        return ImmutableMap.of("stomp", new STOMPOperationBinding()); //STOMPOperationBinding hasn't be implemented, I'll look into doing it myself.
    }

    @Override
    protected Class<?> getPayloadType(Method method) {
        String methodName = String.format("%s::%s", method.getDeclaringClass().getSimpleName(), method.getName());

        Class<?>[] parameterTypes = method.getParameterTypes();
        switch (parameterTypes.length) {
            case 0:
                // Message Mapping can have 0 Arguments and be fine.
                throw new IllegalArgumentException("Message methods must not have 0 parameters: " + methodName);
            case 1:
                // Additional Parameters like @Header, @Headers, and @DestinationVariable are legal for STOMP.
                return parameterTypes[0];
            default:
                // Likely will remove the private and use this for specifically finding the @Payload, return null if none?
                return getPayloadType(parameterTypes, method.getParameterAnnotations(), methodName);
        }
    }

    // Merge into the protected? But remove the IllegalArgumentException.
    private Class<?> getPayloadType(Class<?>[] parameterTypes, Annotation[][] parameterAnnotations, String methodName) {
        int payloadAnnotatedParameterIndex = getPayloadAnnotatedParameterIndex(parameterAnnotations);

        if (payloadAnnotatedParameterIndex == -1) {
            String msg = "Expected Payload Annotation for MessageMapping, "
                    + "but none was found: "
                    + methodName;

            throw new IllegalArgumentException(msg);
        }

        return parameterTypes[payloadAnnotatedParameterIndex];
    }

    // We can keep this, maybe swap it to include an Annotation Type?
    // Then we can do anyMatch(annotation -> annotation instanceof targetAnnotation)
    private int getPayloadAnnotatedParameterIndex(Annotation[][] parameterAnnotations) {
        for (int i = 0, length = parameterAnnotations.length; i < length; i++) {
            Annotation[] annotations = parameterAnnotations[i];
            boolean hasPayloadAnnotation = Arrays.stream(annotations)
                    .anyMatch(annotation -> annotation instanceof Payload);

            if (hasPayloadAnnotation) {
                return i;
            }
        }
        return -1;
    }
}
