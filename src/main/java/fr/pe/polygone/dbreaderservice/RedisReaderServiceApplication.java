package fr.pe.polygone.dbreaderservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@SpringBootApplication
@EnableRedisRepositories
public class RedisReaderServiceApplication {



	public static void main(String[] args) {
		SpringApplication.run(RedisReaderServiceApplication.class, args);
	}



		/*
	 * Define connection factory for Jedis Client based on a RedisStandaloneConfiguration
	 * Configure connection details
	 * return a JedisConnectionFactory
	 */
	@Bean
	JedisConnectionFactory jedisConnectionFactory() {
		RedisStandaloneConfiguration redisStandaloneConfiguration= new RedisStandaloneConfiguration("polygone.dgasi.pole-emploi.intra", 6379);
		return new JedisConnectionFactory(redisStandaloneConfiguration);
	}

	/*
	 * Define redis template used for querying data with a custom repo
	 * Use the JedisConnectionFactory define above
	 * return a RedisTemplate
	 */
	@Bean
	public RedisTemplate<String,Message> redisTemplate()
	{
		RedisTemplate<String,Message> template = new RedisTemplate<>();
		RedisSerializer<String> stringSerializer = new StringRedisSerializer();
		RedisSerializer<Message> messageSerializer = new Jackson2JsonRedisSerializer<>(Message.class);
		template.setKeySerializer(stringSerializer);
		template.setHashKeySerializer(stringSerializer);
		template.setValueSerializer(messageSerializer);
		template.setConnectionFactory(jedisConnectionFactory());
		return template;
	}
}
