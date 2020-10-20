package stepDefinitions;

import org.junit.Assert;

import apiBase.BaseTest;
import apiPojo.Category;
import apiPojo.Pets;
import apiPojo.Tags;
import context.TestContext;
import enums.RestEnums;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utils.RestRequests;

public class PetDefinition extends BaseTest {

	private static String petId;

	public PetDefinition(TestContext testContext) {
		super(testContext);
	}

	@Given("User sent a request with pet status as {string}")
	public void user_sent_a_request_with_pet_status_as(String petStatus) {
		RequestSpecification request = RestRequests.getRestRequest();
		request.queryParam("status", petStatus);
		Response response = request.get("/v2/pet/findByStatus/");
		getScenarioContext().setContext(RestEnums.StatusCode, response.getStatusCode());
	}

	@Then("A list of available pets is returned")
	public void a_list_of_available_pets_is_returned() {
		String statusCode = getScenarioContext().getContext(RestEnums.StatusCode).toString();
		Assert.assertEquals("Status code not correct as expected", "200", statusCode);
	}

	@Given("User want to add a pet with details {string},{string},{string},{string},{string},{string},{string}")
	public void user_want_to_add_a_pet_with_details(String catId, String catName, String petName, String photoUrls,
			String tagId, String tagName, String Status) {
		RequestSpecification request = RestRequests.getRestRequestWithHeaders();
		Pets pets = new Pets();
		Category category = new Category() {
			@Override
			public void setId(String catId) {
				super.setId(catId);
			}

			@Override
			public void setName(String catName) {
				super.setName(catName);
			}
		};

		Tags tags = new Tags() {
			@Override
			public void setId(String tagId) {
				super.setId(tagId);
			}

			@Override
			public void setName(String tagName) {
				super.setName(tagName);
			}
		};
		Tags[] tagList = { tags };
		String[] photoUrlList = { photoUrls };

		pets.setCategory(category);
		pets.setTags(tagList);
		pets.setPhotoUrls(photoUrlList);
		pets.setStatus(Status);
		pets.setName(petName);

		Response response = request.body(pets).post("/v2/pet");

		Pets petResponse = response.getBody().as(Pets.class);
		getScenarioContext().setContext(RestEnums.ID, petResponse.getId());
		getScenarioContext().setContext(RestEnums.StatusCode, response.getStatusCode());
	}

	@Then("verify that pet is added to the store")
	public void verify_that_pet_is_added_to_the_store() {
		petId = getScenarioContext().getContext(RestEnums.ID).toString();
		Assert.assertTrue(petId.length() > 0);
		String statusCode = getScenarioContext().getContext(RestEnums.StatusCode).toString();
		Assert.assertEquals("Status code not correct as expected", "200", statusCode);
	}

	@Given("User want to update a pet record details as {string}")
	public void user_want_to_update_a_pet_record_details_as(String Status) {
		RequestSpecification request = RestRequests.getRestRequestWithHeaders();
		Pets pets = new Pets();
		pets.setId(petId);
		pets.setStatus(Status);
		Response response = request.body(pets).put("/v2/pet");
		Pets petResponse = response.getBody().as(Pets.class);
		Assert.assertEquals("Status code not correct as expected", Status, petResponse.getStatus());
		getScenarioContext().setContext(RestEnums.StatusCode, response.getStatusCode());
	}

	@Then("I verify that pet details are updated")
	public void i_verify_that_pet_details_are_updated() {
		String statusCode = getScenarioContext().getContext(RestEnums.StatusCode).toString();
		Assert.assertEquals("Status code not correct as expected", "200", statusCode);
	}

	@Given("User sent a delete pet request")
	public void user_sent_a_delete_pet_request() {
		RequestSpecification request = RestRequests.getRestRequest();
		Response response = request.get("/v2/pet/"+petId);
		getScenarioContext().setContext(RestEnums.StatusCode, response.getStatusCode());
	}

	@Then("a pet is deleted successfully from the store")
	public void a_pet_is_deleted_successfully_from_the_store() {
		String statusCode = getScenarioContext().getContext(RestEnums.StatusCode).toString();
		Assert.assertEquals("Status code not correct as expected", "200", statusCode);
	}

}
