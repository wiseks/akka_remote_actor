package com.benq.matchserver;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import com.benq.common.Constant;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

public class ApplicationMatchServerMain {

    public static void main(String[] args) {
        startMatchSystem();
    }

    public static void startMatchSystem() {
        //user cfg
        String serverHostName= UserConfig.getConfig().getString("akka.remote.netty.tcp.hostname");
        int serverPort= UserConfig.getConfig().getInt("akka.remote.netty.tcp.port");

        //app resource cfg
        Config appCfg = ConfigFactory.load(Constant.MatchActorCfgName);
        final Config finalConfig = ConfigFactory.parseString(
                "akka.remote.netty.tcp.hostname=" + serverHostName)
                .withFallback(ConfigFactory.parseString("akka.remote.netty.tcp.port=" + serverPort))
                .withFallback(appCfg);

        ActorSystem actorSystem = ActorSystem.create(Constant.MatchActorSysName, finalConfig);
        System.out.println("Started Match System");

        final ActorRef matchActor = actorSystem.actorOf(Props.create(MatchActor.class),
                Constant.MatchActorRemoteName);

        System.out.println("---- Match System-----");
    }

}
