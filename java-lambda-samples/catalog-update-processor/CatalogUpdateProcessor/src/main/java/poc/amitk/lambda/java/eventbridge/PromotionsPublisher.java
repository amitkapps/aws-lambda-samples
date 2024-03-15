package poc.amitk.lambda.java.eventbridge;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.ScheduledEvent;

/**
 * @author amitkapps
 */
public class PromotionsPublisher implements RequestHandler<ScheduledEvent, Void> {
    @Override
    public Void handleRequest(ScheduledEvent input, Context context) {
        context.getLogger().log("message received " + input.getDetail());
        return null;
    }
}
