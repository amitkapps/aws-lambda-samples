package poc.amitk.lambda.java.eventbridge;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.github.javafaker.Faker;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.eventbridge.EventBridgeClient;
import software.amazon.awssdk.services.eventbridge.model.PutEventsRequest;
import software.amazon.awssdk.services.eventbridge.model.PutEventsRequestEntry;
import software.amazon.awssdk.services.eventbridge.model.PutEventsResponse;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

/**
 * @author amitkapps
 */
public class CatalogUpdatePublisher {

    private Logger logger = LoggerFactory.getLogger(CatalogUpdatePublisher.class);

    /**
     * Ref: https://docs.aws.amazon.com/eventbridge/latest/userguide/example_eventbridge_PutEvents_section.html
     */
    @Test
    public void postEventBridgeMessage() throws JsonProcessingException {

        String catalogUpdateEventJson = toJson(getCatalogUpdateEvent());

        Region region = Region.US_WEST_2;
        EventBridgeClient eventBrClient = EventBridgeClient.builder()
                .credentialsProvider(DefaultCredentialsProvider.builder().build())
                .region(region)
                .build();

        PutEventsRequestEntry entry = PutEventsRequestEntry.builder()
                .source("com.amitk.catalogservice")
                .detail(catalogUpdateEventJson)
                .detailType("ProductCatalogUpdate")
                .eventBusName("ProductCatalogEventBus")
                .build();

        PutEventsRequest eventsRequest = PutEventsRequest.builder()
                .entries(entry)
                .build();
        logger.info("putting events {}", eventsRequest);
        PutEventsResponse putEventsResponse = eventBrClient.putEvents(eventsRequest);
        logger.info("putEventsResponse: {}", putEventsResponse);
        eventBrClient.close();

    }

    private CatalogUpdateEvent getCatalogUpdateEvent(){
        CatalogUpdateEvent catalogUpdateEvent = new CatalogUpdateEvent();
        Faker faker = new Faker();
        catalogUpdateEvent.setManufacturer(faker.company().name());
        catalogUpdateEvent.setSku(String.valueOf(faker.number().numberBetween(1000,10000)));
        catalogUpdateEvent.setProductName(faker.commerce().productName());
        catalogUpdateEvent.setLaunchDate(ZonedDateTime.ofInstant(faker.date().future(365, TimeUnit.DAYS).toInstant(), TimeZone.getDefault().toZoneId()));
        catalogUpdateEvent.setPriceCurrency("USD");
        catalogUpdateEvent.setPriceDenomination(faker.number().randomDouble(0, 10, 10000));

        return catalogUpdateEvent;
    }

    private String toJson(Object object) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        // to be able to handle ZonedDateTime
        mapper.registerModule(new JavaTimeModule());

        return mapper.writeValueAsString(object);
    }
}
