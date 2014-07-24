package pl.akomar;

import com.hazelcast.config.Config;
import com.hazelcast.config.JoinConfig;
import com.hazelcast.config.NetworkConfig;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;

public class Member {
    public static void main(String[] args) {
        Config cfg = new Config();
        NetworkConfig network = cfg.getNetworkConfig();
//        network.setPort(PORT_NUMBER);

        JoinConfig join = network.getJoin();

        join.getMulticastConfig().setEnabled(false);
        join.getTcpIpConfig().addMember("192.168.2.5").addMember("192.168.2.5").
                addMember("192.168.2.5").setRequiredMember(null).setEnabled(true);

//this sets the allowed connections to the cluster? necessary for multicast, too?
//        network.getInterfaces().setEnabled(true).addInterface("192.168.0.*");

        HazelcastInstance instance = Hazelcast.newHazelcastInstance(cfg);
        System.out.println("debug: joined via "+join+" with "+instance.getCluster()
                .getMembers().size()+" members.");

//        Hazelcast.newHazelcastInstance();
    }
}
