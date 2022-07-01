package com.bridgelabz.spotify;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class SpotifyApi {

	public String token;
	 String userId;
	 String playListId = "0Mc8aBONKnjtK2UGPgTtOJ";
     String trackId = "0DqXA397QkcVjWq5Wa7DMt";

	@BeforeTest
	public void getToken() {
		token = "Bearer BQCBxsY-sPAtjNez2M9y5fNJxaAZOmNf-5-feVun-3L8eRPpg9RGRNWujr2lTXrUG7VS75Nb4JrfmM4FGOQoWe2nrMJQ1988Av2Hx4DJ5E8NjPaLcmPlizIutdAtzTKYulxtj8cfsiHwwS9wgNUeqzCxRMlKUPmGmr8FL8DIR_PgzZ1d8DLukCLdBgzfts6Ihk7zQPw-GlMHcaxKGA_4IUk9KDKTu0-PVwKm5C0Cy94wBcLX8sDNRzSQO-N4AMi5WrC69Iq9r19aem71DPH7VHZSEWbwJ96ol7dQMxYhqxHIQDGv_jZTdxqnaS_YtOJOtrLVXQU9El9L";

	}

	@Test(priority = 0)
	public void test_GetCurrentUsersProfile() {
		Response response = RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON)
				.header("Authorization", token).when().get("https://api.spotify.com/v1/me");
		response.prettyPrint();
		userId = response.path("id");
		System.out.println("User Id is : " + userId);
//		int statusCode = response.getStatusCode();
//		System.out.println("Status code: " + statusCode);
//		Assert.assertEquals(statusCode, 200);
		Assert.assertEquals(userId, "31sqekzq7bsp3wvqxkugx5uahdwe");
	}

	// Get User's Profile
	@Test(priority = 1)
	public void test_GetUsersProfile() {
		Response response = RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON)
				.header("Authorization", token).when().get("https://api.spotify.com/v1/users/" + userId + "");
		response.prettyPrint();
//		int statusCode = response.getStatusCode();
//		System.out.println("Status code: " + statusCode);
//		Assert.assertEquals(statusCode, 200);
		response.then().assertThat().statusCode(200);
	}

	
	  // Create Playlist
	  
	  @Test(priority = 2) public void test_CreatePlaylist_ShouldStatusCode() {
	  Response response =
	  RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON)
	  .header("Authorization", token) .body("{\r\n" +
	  "  \"name\": \"Automation Playlist1\",\r\n" +
	  "  \"description\": \"New playlist description\",\r\n" +
	  "  \"public\": false\r\n" + "}")
	  .when().post("https://api.spotify.com/v1/users/" + userId + "/playlists");
	  response.prettyPrint(); playListId = response.path("id");
	  System.out.println("Playlist Id is: " + playListId);
	  response.then().assertThat().statusCode(201);
	  
	  }
	 

	// Get Playlist
	@Test(priority = 3)
	public void test_GetPlaylist() {
		Response response = RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON)
				.header("Authorization", token).when()
				.get("	https://api.spotify.com/v1/playlists/" + playListId + "");
		response.prettyPrint();
		response.then().assertThat().statusCode(200);

	}

	// Get Current User's Playlists
	@Test(priority = 4)
	public void test_GetCurrentUserPlaylists() {
		Response response = RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON)
				.header("Authorization", token).when().get("https://api.spotify.com/v1/me/playlists");
		response.prettyPrint();
		response.then().assertThat().statusCode(200);

	}

	// Get Playlist Cover Image
	@Test(priority = 5)
	public void test_GetPlaylistCoverImage() {
		Response response = RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON)
				.header("Authorization", token).when()
				.get("	https://api.spotify.com/v1/playlists/" + playListId + "/images");
		response.prettyPrint();
		response.then().assertThat().statusCode(200);

	}

	// Get Playlist Items
	@Test(priority = 6)
	public void test_GetPlaylistItems() {
		Response response = RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON)
				.header("Authorization", token).when()
				.get("https://api.spotify.com/v1/playlists/" + playListId + "/tracks");
		response.prettyPrint();
		response.then().assertThat().statusCode(200);

	}

	// Get User's Playlists
	@Test(priority = 7)
	public void test_GetUsersPlaylists() {
		Response response = RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON)
				.header("Authorization", token).when().get("https://api.spotify.com/v1/users/" + userId + "/playlists");
		response.prettyPrint();
		response.then().assertThat().statusCode(200);

	}

	// Search for Item
	@Test(priority = 8)
	public void test_SearchForItem() {
		Response response = RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON)
				.header("Authorization", token).queryParam("q", "jubin nautiyal").queryParam("type", "track").when()
				.get("https://api.spotify.com/v1/search");
		response.prettyPrint();
		response.then().assertThat().statusCode(200);

	}

	// Add Items to Playlist
	@Test(priority = 9)
	public void test_AddItemstoPlaylist() {
		Response response = RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON)
				.header("Authorization", token)
				.queryParams("uris", "spotify:track:2rU33n6Fhd6G1MzYhUj6C5,spotify:track:28odLhBa4Yh2yPoDWJMOPb,spotify:track:6TGX01ExmdHyOG72fqUU3Q,spotify:track:58SIEtTjWErr0yEXVBKxvd,spotify:track:55LK166LWHOpXOroZ5g82J").when()
				.post("https://api.spotify.com/v1/playlists/" + playListId + "/tracks");
		response.prettyPrint();
		response.then().assertThat().statusCode(201);

	}

	// Update Playlist Items
	@Test(priority = 10)
	public void test_UpdatePlaylistItems() {
		Response response = RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON)
				.header("Authorization", token).body("{\r\n" + "\"range_start\": 1,\r\n" + "\"insert_before\": 4,\r\n"
						+ "\"range_length\": 1\r\n" + "}")
				.when().put("https://api.spotify.com/v1/playlists/" + playListId + "/tracks");
		response.prettyPrint();
		response.then().assertThat().statusCode(200);

	}



	// Remove Playlist Items
	@Test(priority = 12)
	public void test_RemovePlaylistItems() {
		Response response = RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON)
				.header("Authorization", token)
				.body("{ \"tracks\": [{ \"uri\": \"spotify:track:2rU33n6Fhd6G1MzYhUj6C5\" },{ \"uri\": \"spotify:track:28odLhBa4Yh2yPoDWJMOPb\" },{ \"uri\": \"spotify:track:6TGX01ExmdHyOG72fqUU3Q\" },{ \"uri\": \"spotify:track:58SIEtTjWErr0yEXVBKxvd\" },{ \"uri\": \"spotify:track:55LK166LWHOpXOroZ5g82J\" }] }")
				.when()
				.delete("https://api.spotify.com/v1/playlists/" + playListId + "/tracks");
		response.prettyPrint();
		response.then().assertThat().statusCode(200);
	}
	

	// Track

	// Get Track's Audio Analysis
	@Test(priority = 13)
	public void test_GetTracksAudioAnalysis() {
		Response response = RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON)
				.header("Authorization", token).when().get("https://api.spotify.com/v1/audio-analysis/" + trackId + "");
		response.prettyPrint();
		response.then().assertThat().statusCode(200);

	}

	// Get Tracks' Audio Features
	@Test(priority = 14)
	public void test_GetTracksAudioFeatures() {
		Response response = RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON)
				.header("Authorization", token).when().get("https://api.spotify.com/v1/audio-features");
		response.prettyPrint();
		response.then().assertThat().statusCode(200);

	}

	// Get Track's Audio Features
	@Test(priority = 15)
	public void test_GetTracksAudioFeatures_ShouldtatusCode() {
		Response response = RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON)
				.header("Authorization", token).when().get("https://api.spotify.com/v1/audio-features/" + trackId + "");
		response.prettyPrint();
		response.then().assertThat().statusCode(200);

	}

	// Get Several Tracks
	@Test(priority = 16)
	public void test_GetSeveralTracks() {
		Response response = RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON)
				.header("Authorization", token).queryParam("ids", "0DqXA397QkcVjWq5Wa7DMt").when()
				.get("https://api.spotify.com/v1/tracks");
		response.prettyPrint();
		response.then().assertThat().statusCode(200);

	}

	// Get Track
	@Test(priority = 17)
	public void test_GetTrack() {
		Response response = RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON)
				.header("Authorization", token).when().get("https://api.spotify.com/v1/tracks/" + trackId + "");
		response.prettyPrint();
		response.then().assertThat().statusCode(200);

	}

}
