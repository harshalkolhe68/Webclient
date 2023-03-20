package com.dev.webclient.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import com.dev.webclient.dto.Post;
import com.dev.webclient.dto.Users;

import reactor.core.publisher.Flux;

@RestController
@RequestMapping("webclient")
public class APIController {
	
	private final String POST_OF_API = "http://jsonplaceholder.typicode.com/posts/9";
	private final String POST_BY_ID_API = "http://jsonplaceholder.typicode.com/posts/{id}";
	private final String POST_API = "http://jsonplaceholder.typicode.com/posts";
	
	private final String Users_API = "https://jsonplaceholder.typicode.com/users/1" ;
	private final String All_Users_API = "https://jsonplaceholder.typicode.com/users" ;
	
	@Autowired
	private WebClient.Builder webClientBuilder;
	
	@GetMapping("/comsume")
	public Post getData() {
		
		return webClientBuilder.build()
				.get()
				.uri(POST_OF_API)
				.retrieve()
				.bodyToMono(Post.class)
				.block();
	}
	
	
	@GetMapping("/comsume/{id}")
	public Post getPostById(@PathVariable("id") Integer postId) {		
		
	 return webClientBuilder.build()
			 .get()
			 .uri(POST_BY_ID_API,  postId)
			 .retrieve()
			 .bodyToMono(Post.class)
			 .block();	 
	}
	
	@GetMapping("/comsume/all")
	public Post[] getAllPost() {		
		
	 return webClientBuilder.build()
			 .get()
			 .uri(POST_API)
			 .retrieve()
			 .bodyToMono(Post[].class)
			 .block();	 
	}
	
	@GetMapping("/users")
	public Users getUserData() {
		
		return webClientBuilder.build()
				.get()
				.uri(Users_API)
				.retrieve()
				.bodyToMono(Users.class)
				.block();
				
	}
	
	@GetMapping("/allusers")
	public Users[] getAllUserData() {
		
		return webClientBuilder.build()
				.get()
				.uri(All_Users_API)
				.retrieve()
				.bodyToMono(Users[].class)
				.block();
				
	}
	
	// Webclient Flux 
	@GetMapping("/FluxUsers")
	public Object getFlux() {
		WebClient client = WebClient.create("https://jsonplaceholder.typicode.com");
		
		Flux<Map> userMap = client
				.get().uri("/users")
				.retrieve().bodyToFlux(Map.class);
		
		List<Map> output = userMap.collectList().block();
		
		return output;
	}

}
