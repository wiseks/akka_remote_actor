package com.benq.agentserver;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import com.benq.common.Constant;
import com.benq.common.Op;
import com.typesafe.config.ConfigFactory;
import scala.collection.immutable.Stream;
import scala.concurrent.duration.Duration;

import java.util.Random;

import static java.util.concurrent.TimeUnit.SECONDS;

public class ApplicationAgentServerMain {
  public static void main(String[] args) {

      startAgentSystem();

  }


  public static void startAgentSystem() {
    final ActorSystem system = ActorSystem.create(Constant.AgentActorSysName,
        ConfigFactory.load(Constant.AgentActorCfgName));
    //system.actorOf(Props.create(AgentActor.class), "agent");

    System.out.println("Started agent System");

    //to find remote

    final String path = "akka.tcp://"+ Constant.LoginActorSysName+"@127.0.0.1:2552/user/"+Constant.LoginActorRemoteName;
    final ActorRef actor = system.actorOf(
        Props.create(LookupActor.class, path), "lookupActor");

    System.out.println("Started LookupSystem");
    final Random r = new Random();
    system.scheduler().schedule(Duration.create(1, SECONDS),
        Duration.create(1, SECONDS), new Runnable() {
          @Override
          public void run() {
            if (r.nextInt(100) % 2 == 0) {
              actor.tell(new Op.Add(r.nextInt(100), r.nextInt(100)), null);
            } else {
              actor.tell(new Op.Subtract(r.nextInt(100), r.nextInt(100)), null);
            }

          }
        }, system.dispatcher());

  }
}
