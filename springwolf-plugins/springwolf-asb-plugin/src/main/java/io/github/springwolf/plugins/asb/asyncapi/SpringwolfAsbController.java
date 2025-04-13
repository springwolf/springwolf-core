package io.github.springwolf.plugins.asb.asyncapi;

import io.github.springwolf.core.controller.PublishingBaseController;
import io.github.springwolf.core.controller.PublishingPayloadCreator;
import io.github.springwolf.core.controller.dtos.MessageDto;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * SpringwolfAsbController.
 */

@RestController
@RequestMapping("${springwolf.path.base:/springwolf}/asb")
public class SpringwolfAsbController extends PublishingBaseController {

    private final SpringwolfAsbProducer springwolfAsbProducer;

    public SpringwolfAsbController(
            final PublishingPayloadCreator publishingPayloadCreator,
            final SpringwolfAsbProducer springwolfAsbProducer
    ) {
        super(publishingPayloadCreator);
        this.springwolfAsbProducer = springwolfAsbProducer;
    }

    @Override
    protected boolean isEnabled() {
        return springwolfAsbProducer.isEnabled();
    }

    @Override
    protected void publishMessage(final String topic, final MessageDto message, final Object payload) {
        springwolfAsbProducer.send(topic, message);
    }
}
