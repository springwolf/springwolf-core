package io.github.stavshamir.springwolf.asyncapi.scanners.channels.operationdata;

import io.github.stavshamir.springwolf.asyncapi.types.OperationData;
import io.github.stavshamir.springwolf.configuration.AsyncApiDocketService;
import io.github.stavshamir.springwolf.schemas.SchemasService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static io.github.stavshamir.springwolf.configuration.properties.SpringWolfConfigConstants.*;

@Slf4j
@RequiredArgsConstructor
@Component
@ConditionalOnProperty(name = SPRINGWOLF_SCANNER_PRODUCER_DATA_ENABLED, matchIfMissing = true)
public class ProducerOperationDataScanner extends AbstractOperationDataScanner {

    private final AsyncApiDocketService asyncApiDocketService;
    private final SchemasService schemasService;

    @Override
    protected SchemasService getSchemaService() {
        return this.schemasService;
    }

    @Override
    protected List<OperationData> getOperationData() {
        return new ArrayList<>(asyncApiDocketService.getAsyncApiDocket().getProducers());
    }

    @Override
    protected OperationData.OperationType getOperationType() {
        return OperationData.OperationType.SUBSCRIBE;
    }
}
