package com.emirates.test.flightdetailsreactive.client;

import com.emirates.test.flightdetailsreactive.model.Post;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PostClient {

    Mono<Post> create (Post post);

    Mono<Post> findById (String id);

    Flux<Post> findAll ();

    Mono<Void> remove (String id);
}