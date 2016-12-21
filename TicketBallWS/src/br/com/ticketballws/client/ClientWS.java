package br.com.ticketballws.client;

import java.net.HttpURLConnection;
import java.net.URI;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

import br.com.ticketballws.model.Users;
import br.com.ticketballws.utils.Crypt;

public class ClientWS {

	public static void main(String[] args) {

		try {
			Users user = new Users();
			user.setName("Teste 3");
			user.setCpf("36985215687");
			user.setEmail("test3@test.com");
			user.setLogin("test3");
			user.setPass(Crypt.encryptStrAES("123789"));

			ClientConfig config = new DefaultClientConfig();
			Client client = Client.create(config);
			WebResource service = client.resource(getBaseURI());
			
			//service.path("services").path("sendUser").post(user);
			
			ClientResponse response = service.path("services").path("sendUser").accept(MediaType.APPLICATION_XML_TYPE).type(MediaType.APPLICATION_XML_TYPE).post(ClientResponse.class, user);
			System.out.println(response);
			
			if (response.getStatus() != HttpURLConnection.HTTP_CREATED) {
				throw new RuntimeException("Failed : HTTP error code : "
				     + response.getStatus());
			}

			System.out.println("Output from Server .... \n");
			String output = response.getEntity(String.class);
			System.out.println(output);
			
			
			//GET
			Users obj = service.path("services").path("getUser").queryParam("idUser", "24").accept(MediaType.APPLICATION_XML_TYPE).type(MediaType.APPLICATION_XML_TYPE).get(Users.class);
			System.out.println(obj.toString());
			System.out.println(Crypt.decryptAES(obj.getPass().split(";")));
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static URI getBaseURI() {
		return UriBuilder.fromUri("http://localhost:8080/TicketBallWS/").build();
	}

}
