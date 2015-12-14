package com.benq.common;

/**
 * Created by Henry.Wu on 2015/12/14.
 */
public class Constant {

    public static String AgentActorSysName="agent_system";
    public static String AgentActorCfgName="agent_server";

    public static String LoginActorSysName="login_system";
    public static String LoginActorCfgName="login_server";
    public static String LoginActorRemoteName="login_actor";

    public static String MatchActorSysName="match_system";
    public static String MatchActorCfgName="match_server";
    public static String MatchActorRemoteName="match_actor";

    public static final String LoginRemotePath = "akka.tcp://"+ Constant.LoginActorSysName+"@127.0.0.1:2552/user/"+Constant.LoginActorRemoteName;
    public static final String MatchRemotePath = "akka.tcp://"+ Constant.MatchActorSysName+"@127.0.0.1:2553/user/"+Constant.MatchActorRemoteName;

//    public static final String LoginRemotePath = "akka.tcp://"+ Constant.LoginActorSysName+"@120.24.218.184:2952/user/"+Constant.LoginActorRemoteName;
//    public static final String MatchRemotePath = "akka.tcp://"+ Constant.MatchActorSysName+"@120.25.248.252:2952/user/"+Constant.MatchActorRemoteName;

}
