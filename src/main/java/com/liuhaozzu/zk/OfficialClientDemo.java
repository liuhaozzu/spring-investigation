package com.liuhaozzu.zk;

import com.google.common.collect.Lists;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.ACL;
import org.junit.Before;
import org.junit.Test;

import java.nio.charset.StandardCharsets;

/**
 * @author liuhao01
 * @date 3/19/21 7:25 PM
 */
public class OfficialClientDemo {

    ZooKeeper zooKeeper;

    @Before
    public void init() throws Exception{
        String connectString = "localhost:12181";

        zooKeeper = new ZooKeeper(connectString, 3000, null);
    }

    @Test
    public void getTest() throws Exception{
        //zooKeeper.create("/root", "root".getBytes(StandardCharsets.UTF_8), Lists.newArrayList(ZooDefs.Ids.OPEN_ACL_UNSAFE), CreateMode.PERSISTENT);
        byte[] data = zooKeeper.getData("/root", null, null);
        System.out.println("data:"+new String(data));
    }

}
