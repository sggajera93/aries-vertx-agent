package com.example.starter;

import io.vertx.core.*;
import io.vertx.core.http.HttpClient;
import io.vertx.core.http.HttpClientRequest;
import io.vertx.core.http.HttpServerOptions;
import io.vertx.core.http.RequestOptions;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;

public class MainVerticle extends AbstractVerticle {

  @Override
  public void start(Promise<Void> startPromise) throws Exception {

    {
      // Create a Router
      Router router = Router.router(vertx);

      // Mount the handler for all incoming requests at every path and HTTP method
      router.route("/issuer").handler(context -> {

          context.request().body().onComplete(rq -> {
            System.out.println(rq.result().toString());
            context.json(
              new JsonObject()
                .put("uri", context.request().uri())
                .put("body",rq.result().toString())
            );
          });

        });


      // Mount the handler for all incoming requests at every path and HTTP method
      router.route("/holder").handler(context -> {

        context.request().body().onComplete(rq -> {
          System.out.println(rq.result().toString());
          context.json(
            new JsonObject()
              .put("uri", context.request().uri())
              .put("body",rq.result().toString())
          );
        });

      });

      router.route("/tail-server").handler(context -> {

        context.request().body().onComplete(rq -> {
          System.out.println(rq.result().toString());
          context.json(
            new JsonObject()
              .put("uri", context.request().uri())
              .put("body",rq.result().toString())
          );
        });

      });


      // Create the HTTP server
      vertx.createHttpServer()
        // Handle every request using the router
        .requestHandler(router)
        // Start listening
        .listen(8888)
        // Print the port
        .onSuccess(server ->
          System.out.println(
            "HTTP server started on port " + server.actualPort()
          )
        );
    }
  }
}
