package bdd.PetServiceTest;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.owner.*;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;

public class FindPetTest {
	@Autowired
	private PetService petService;
	@Autowired
	private PetRepository petRepository;
	@Autowired
	private PetTypeRepository petTypeRepository;
	@Autowired
	private OwnerRepository ownerRepository;

	private Pet pet;
	private Pet testResultPet;
	private Owner owner;
	private PetType petType;

	@Given("A pet with Id")
	public void IsAPet() {
		//set owner
		owner = new Owner();
		owner.setFirstName("Merritt");
		owner.setLastName("Tremblay");
		owner.setAddress("33073 Lupe Fall");
		owner.setCity("North Electachester");
		owner.setTelephone("5618520709");
		ownerRepository.save(owner);
		//set pet type
		petType = new PetType();
		petType.setName("dog");
		petTypeRepository.save(petType);

		pet = new Pet();
		pet.setName("Jay");
		pet.setBirthDate(LocalDate.of(2010,4,15));
		pet.setType(petType);
		owner.addPet(pet);
		petRepository.save(pet);
	}

	@When("findPet with existing Id is called")
	public void findOwnerWithExistingId() {
		testResultPet = petService.findPet(pet.getId());
	}

	@Then("Pet is found with Id")
	public void ownerIsFound() {
		assertEquals(pet.getId(), testResultPet.getId());
	}

}
