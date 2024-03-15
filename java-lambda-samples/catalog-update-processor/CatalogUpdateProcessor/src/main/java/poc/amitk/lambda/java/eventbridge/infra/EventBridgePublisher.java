package poc.amitk.lambda.java.eventbridge.infra;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import poc.amitk.lambda.java.eventbridge.model.ProductPromotion;
import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
import software.amazon.awssdk.services.eventbridge.EventBridgeClient;
import software.amazon.awssdk.services.eventbridge.model.PutEventsRequest;
import software.amazon.awssdk.services.eventbridge.model.PutEventsRequestEntry;
import software.amazon.awssdk.services.eventbridge.model.PutEventsResponse;

/**
 * Ref: https://docs.aws.amazon.com/sdk-for-java/latest/developer-guide/java_eventbridge_code_examples.html
 * @author amitkapps
 */
public class EventBridgePublisher {

    private Logger logger = LoggerFactory.getLogger(EventBridgePublisher.class);
    private ObjectMapper objectMapper;
    public EventBridgePublisher(){
        this.objectMapper = new ObjectMapper();
        // to be able to handle ZonedDateTime
        objectMapper.registerModule(new JavaTimeModule());
    }

    public void publishEvent(ProductPromotion productPromotion){
        EventBridgeClient eventBrClient = EventBridgeClient.builder()
                .credentialsProvider(DefaultCredentialsProvider.builder().build())
                .build();

        PutEventsRequestEntry entry = null;
        try {
            entry = PutEventsRequestEntry.builder()
                    .source("com.amitk.promotionservice")
                    .detail(this.objectMapper.writeValueAsString(productPromotion))
                    .detailType("ProductPromotion")
                    .eventBusName("ProductCatalogEventBus")
                    .build();
        } catch (JsonProcessingException e) {
            logger.error("could not serialize the product promotion to json", e);
        }

        PutEventsRequest eventsRequest = PutEventsRequest.builder()
                .entries(entry)
                .build();
        logger.debug("putting events on the bus {}", eventsRequest);
        PutEventsResponse putEventsResponse = eventBrClient.putEvents(eventsRequest);
        logger.debug("putEventsResponse entries: {}", putEventsResponse.entries());
        logger.info("putEventsResponse failure/total count: {}/{}", putEventsResponse.failedEntryCount(), putEventsResponse.entries().size());
        eventBrClient.close();

    }
}
