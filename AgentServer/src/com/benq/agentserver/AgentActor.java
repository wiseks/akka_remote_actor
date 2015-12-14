package com.benq.agentserver;

/**
 * Created by Henry.Wu on 2015/8/18.
 */

import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;

public class AgentActor extends UntypedActor {
    LoggingAdapter log = Logging.getLogger(getContext().system(), this);
    String name,ip;

    public AgentActor(String name,String ip){
        this.name=name;
        this.ip=ip;
    }

    public void onReceive(Object message) throws Exception {
        if (message instanceof String) {
            log.info("Received String message: {}", message);
            getSender().tell(message, getSelf());
        } else
            unhandled(message);
    }
}
