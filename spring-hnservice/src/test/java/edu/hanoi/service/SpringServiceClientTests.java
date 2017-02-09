package edu.hanoi.service;

import edu.hanoi.service.model.User;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by trungdovan on 12/4/16.
 */
@RunWith(JUnit4.class)
public class SpringServiceClientTests {
    private RestTemplate restTemplate;

    @Before
    public void setUp() {
        restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory(HttpClientBuilder.create().build()));
    }

    @Test
    @Ignore
    public void contextLoads(){
        String url = "http://localhost:2222/list/users";
        ResponseEntity entity = restTemplate.getForEntity(url, List.class);
        List<LinkedHashMap> users = (List) entity.getBody();
        users.forEach( user -> {
            System.out.println("user name: " + user.keySet());
        });
//        assertEquals(10, users.size());
    }

    @Test
    @Ignore
    public void insertUser() {
        String url = "http://localhost:2222/update/user";

        User user = new User();
        user.setUsername("trungdovan");
        user.setPassword("123456");
        user.setAge(25);
        user.setGroupId(2);
        user.setEmail("test1@gmail.com");

        ResponseEntity<String> insertEntity = restTemplate.postForEntity(url, user, String.class);
        System.out.println(insertEntity.getBody());
    }

    @Test
    @Ignore
    public void udpateUser() {
        String url = "http://localhost:2222/update/user";

        User user = new User();
        user.setUsername("trungdovan");
        user.setPassword("123456");
        user.setAge(25);
        user.setGroupId(2);
        user.setEmail("test1@gmail.com");

        ResponseEntity<String> insertEntity = restTemplate.postForEntity(url, user, String.class);
        System.out.println(insertEntity.getBody());
    }

	@Test
	public void testSecurity() throws IOException {
		URL url = new URL ("http://localhost:2222/list/users");
		String encoding = Base64.getEncoder().encodeToString("test1:123".getBytes());

		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod("POST");
		//connection.setDoOutput(true);
		connection.setRequestProperty  ("Authorization", "Basic " + encoding);
		InputStream content = connection.getInputStream();
		BufferedReader in   =
				new BufferedReader (new InputStreamReader (content));
		String line;
		while ((line = in.readLine()) != null) {
			System.out.println(line);
		}
	}

}