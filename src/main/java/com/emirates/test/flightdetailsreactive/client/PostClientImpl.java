package com.emirates.test.flightdetailsreactive.client;

import com.emirates.test.flightdetailsreactive.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component("PostClient")
public class PostClientImpl implements PostClient {

    private WebClient client;

    public PostClientImpl(){
        this.client = WebClient.create("https://jsonplaceholder.typicode.com/posts");
    }

    @Override
    public Mono<Post> create(Post post) {
        return this.client.post().body(Mono.just(post), Post.class)
            .retrieve().bodyToMono(Post.class);
    }

    @Override
    public Mono<Post> findById(String id) {
        return this.client.get().uri("/{id}", id).retrieve().bodyToMono(Post.class);
    }

    @Override
    public Flux<Post> findAll() {
        return this.client.get().retrieve().bodyToFlux(Post.class);
    }

    @Override
    public Mono<Void> remove(String id) {
        return this.client.delete().uri("/{id}", id).exchange().then();
    }
}