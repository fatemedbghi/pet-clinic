package org.springframework.samples.petclinic.owner;

import static org.junit.Assert.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.support.MutableSortDefinition;
import org.springframework.beans.support.PropertyComparator;

import java.util.*;

class OwnerTest {

	Owner owner;

	@BeforeEach
	public void setup() {
		owner = new Owner();
	}

	@AfterEach
	public void teardown() {
		owner = null;
	}

	@Test
	public void TestAddress() {
		owner.setAddress("214 Henry Ford Avenue");
		assertEquals("214 Henry Ford Avenue",owner.getAddress());
	}

	@Test
	public void TestGetAddressNull() {
		assertEquals(null,owner.getAddress());
	}

	@Test
	public void TestCity() {
		owner.setCity("Manhattan");
		assertEquals("Manhattan",owner.getCity());
	}

	@Test
	public void TestGetCityNull() {
		assertEquals(null,owner.getCity());
	}

	@Test
	public void TestTelephone() {
		owner.setTelephone("7188139637");
		assertEquals("7188139637", owner.getTelephone());
	}

	@Test
	public void TestGetTelephoneNull() {
		assertEquals(null, owner.getTelephone());
	}

	@Test
	public void TestPetsInternal() {
		Pet pet = new Pet();
		pet.setName("Max");
		Set<Pet> pets = new HashSet<Pet>();
		pets.add(pet);
		owner.setPetsInternal(pets);
		assertEquals(pets, owner.getPetsInternal());
	}

	@Test
	public void TestGetPetsInternalNull() {
		HashSet<String> set = new HashSet<String>();
		assertEquals(set, owner.getPetsInternal());
	}

	@Test
	public void TestAddPet() {
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
	public void TestGetPetsNull() {
		List<Pet> pets = new ArrayList<>();
		assertEquals(pets, owner.getPets());
	}
	@Test
	public void removePet() {
		Pet pet1 = new Pet();
		pet1.setName("Max");
		Pet pet2 = new Pet();
		pet2.setName("Coco");
		Set<Pet> pets = new HashSet<Pet>();
		pets.add(pet1);
		pets.add(pet2);
		owner.setPetsInternal(pets);
		owner.removePet(pet2);
		List<Pet> final_pets = Arrays.asList(pet1);
		assertEquals(final_pets, owner.getPets());
	}

	@Test
	public void TestGetPet() {
		Pet pet1 = new Pet();
		pet1.setName("Max");
		Pet pet2 = new Pet();
		pet2.setName("Coco");
		Set<Pet> pets = new HashSet<Pet>();
		pets.add(pet1);
		pets.add(pet2);
		owner.setPetsInternal(pets);
		assertEquals(pet2, owner.getPet("Coco"));
	}

}
