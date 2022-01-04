package bdd.PetServiceTest;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.owner.*;

import static org.junit.Assert.assertEquals;

public class FindOwnerTest {
	@Autowired
	private PetService petService;
	@Autowired
	private OwnerRepository ownerRepository;

	private Owner owner;
	private Owner testResultOwner;

	@Given("An owner with Id")
	public void IsAnOwner() {
		owner = new Owner();
		owner.setFirstName("Merritt");
		owner.setLastName("Tremblay");
		owner.setAddress("33073 Lupe Fall");
		owner.setCity("North Electachester");
		owner.setTelephone("5618520709");
		ownerRepository.save(owner);
	}

	@When("findOwner with existing Id is called")
	public void findOwnerWithExistingId() {
		testResultOwner = petService.findOwner(owner.getId());
	}

	@Then("Owner is found with Id")
	public void ownerIsFound() {
		assertEquals(owner.getId(), testResultOwner.getId());
	}

}
