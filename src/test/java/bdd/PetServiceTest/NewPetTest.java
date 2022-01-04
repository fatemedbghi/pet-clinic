package bdd.PetServiceTest;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.owner.*;

import static org.junit.Assert.assertEquals;

public class NewPetTest {
	@Autowired
	private PetService petService;
	@Autowired
	private OwnerRepository ownerRepository;

	private Pet testResultPet;
	private Owner owner;

	@Given("A new owner with Id")
	public void IsAPet() {
		owner = new Owner();
		owner.setFirstName("Merritt");
		owner.setLastName("Tremblay");
		owner.setAddress("33073 Lupe Fall");
		owner.setCity("North Electachester");
		owner.setTelephone("5618520709");
		ownerRepository.save(owner);
	}

	@When("Owner adds new pet to his pet list")
	public void addPetToOwner() {
		testResultPet = petService.newPet(owner);
	}

	@Then("The new pet has its owner set")
	public void petIsAddedToOwner() {
		assertEquals(testResultPet.getOwner().getId(),owner.getId());
	}

}
