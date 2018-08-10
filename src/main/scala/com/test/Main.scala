package com.test

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.server.Directives._
import akka.stream.ActorMaterializer
import de.heikoseeberger.akkahttpplayjson.PlayJsonSupport
import play.api.libs.json.{JsPath, Json, Reads, Writes}

case class Hello(name: String)

case class Goodbye(name: String)

object Main extends App with PlayJsonSupport {

  implicit val goodbyeWrites = new Writes[Goodbye] {
    def writes(goodbye: Goodbye)
    = Json.obj("name" → goodbye.name)
  }

  implicit val helloReads: Reads[Hello] =
    (JsPath \ "name").read[String].map(name ⇒ Hello(name))

  implicit val system = ActorSystem()

  import system.dispatcher

  implicit val materializer = ActorMaterializer()

  val route = post {
    path("hello") {
      entity(as[Hello]) { hello: Hello ⇒
        complete(Goodbye(hello.name))
      }
    }
  }

  val bindingFuture = Http().bindAndHandle(route, "localhost", 8080)

  println(s"Server online at http://localhost:8080/")

}
