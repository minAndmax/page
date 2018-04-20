package com.army.util.config;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

@Configuration
public class RedisConfig {

	@Value("${spring.redis.cluster.nodes}")
	private String clusterNodes;
	
	@Bean
	public JedisCluster getJedisCluster() {
		
		String[] cNodes = clusterNodes.split(",");
		
		Set<HostAndPort> nodes = new HashSet<HostAndPort>();
		
		for(String node : cNodes) {
			
			nodes.add(new HostAndPort(node.split(":")[0], Integer.parseInt(node.split(":")[1])));
			
		}
		
		JedisCluster jedisCluster = new JedisCluster(nodes);
		
		return jedisCluster;
	}
	
	
}
