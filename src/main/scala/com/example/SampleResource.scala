package com.example

import javax.ws.rs.{GET, Path, Produces}

import co.omise.Client
import co.omise.models.{Card, Charge, Token}

@Path("test")
class SampleResource {

  @GET
  @Produces(Array("text/plain"))
  def method(): String = {
    val client = new Client("pkey_test_58ebzc1zj2ghylfwb5k", "skey_test_58ebzc1zricvpb66zxw")
    val token = client.tokens().create(
      new Token.Create().card(
        new Card.Create().name("Somchai Prasert")
          .number("4242424242424242")
          .expirationMonth(10)
          .expirationYear(2018)
          .city("Bangkok")
          .postalCode("10320")
          .securityCode("123")))

    val charge = client.charges.create(
      new Charge.Create()
        .amount(100000)
        .currency("jpy")
        .capture(false)
        .description("test by app engine on scala user id is test0123")
        .card(token.getId))

    charge.getId
  }

}