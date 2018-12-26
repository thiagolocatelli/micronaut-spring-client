package com.github.thiagolocatelli;

import io.micronaut.context.ApplicationContext;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.client.HttpClient;
import io.micronaut.runtime.server.EmbeddedServer;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static junit.framework.TestCase.assertNotNull;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class IntegrationTest {

    private static EmbeddedServer server;
    private static HttpClient client;
    private static EmbeddedServer springCloudServer;

    @BeforeClass
    public static void setupServer() {
        Map<String, Object> map = new HashMap<>();
        map.put(MockSpringCloundConfigServer.ENABLED, true);
        map.put("micronaut.server.port", 8888);
        map.put("spring.cloud.config.enabled", false);
        map.put("micronaut.environments", "dev,test");
        springCloudServer = ApplicationContext.run(EmbeddedServer.class, map);

        server = ApplicationContext.run(EmbeddedServer.class);

        client = server
                .getApplicationContext()
                .createBean(HttpClient.class, server.getURL());
    }

    @AfterClass
    public static void stopServer() {
        if (springCloudServer != null) {
            springCloudServer.stop();
        }
        if (server != null) {
            server.stop();
        }
        if (client != null) {
            client.stop();
        }
    }

    @Test
    public void testHello() throws Exception {
        HttpRequest request = HttpRequest.GET("/issues/1");
        String body = client.toBlocking().retrieve(request);
        assertNotNull(body);
        assertThat(body, equalTo("test: issue # 1!"));
    }
}
