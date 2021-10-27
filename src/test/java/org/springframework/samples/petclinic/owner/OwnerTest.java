package org.springframework.samples.petclinic.owner;

import java.util.*;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.*;

public class OwnerTest {

	public Owner owner;

	@BeforeEach
	public void setup() {
		owner = new Owner();
	}

	@AfterEach
	public void teardown() {
		owner = null;
	}

	//State Verification
	@Test
	public void TestAddNewPetState() {
		Pet pet1 = new Pet();
		pet1.setName("Max");
		Pet pet2 = new Pet();
		pet2.setName("Coco");
		List<Pet> pets = Arrays.asList(pet2,pet1);
		owner.addPet(pet1);
		owner.addPet(pet2);
		assertEquals(pets, owner.getPets());
	}

	@Test
	public void TestAddOldPetState() {
		Pet pet = new Pet();
		pet.setName("Max");
		List<Pet> pets = Arrays.asList(pet);
		owner.addPet(pet);
		owner.addPet(pet);
		assertEquals(pets, owner.getPets());
	}

	//Behavior Verification
	@Test
	public void TestAddPetBehavior() {
		Pet pet = mock(Pet.class);
		owner.addPet(pet);
		verify(pet).isNew();
		verify(pet).setOwner(owner);
	}
}
