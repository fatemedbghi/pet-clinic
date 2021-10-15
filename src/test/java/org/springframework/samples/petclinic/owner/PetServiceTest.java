package org.springframework.samples.petclinic.owner;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.springframework.samples.petclinic.utility.PetTimedCache;
import org.springframework.samples.petclinic.utility.SimpleDI;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.*;
import org.junit.runners.Parameterized.Parameters;

import java.util.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith (Parameterized.class)
class PetServiceTest {
	private static PetService PetSer;
	private static Pet dog, cat, horse, hen;
	private final int id;
	private final Pet pet;

	public PetServiceTest(int id, Pet pet) {
		this.id = id;
		this.pet = pet;
	}

	@Before
	public void setup() {
		SetPet();
		PetSer = mock((PetService.class));
		when(PetSer.findPet(1)).thenReturn(dog);
		when(PetSer.findPet(2)).thenReturn(horse);
		when(PetSer.findPet(3)).thenReturn(cat);
		when(PetSer.findPet(4)).thenReturn(hen);
	}

	public void SetPet() {
		dog = new Pet();
		dog.setId(1);

		horse = new Pet();
		horse.setId(2);

		cat = new Pet();
		cat.setId(3);

		hen = new Pet();
		hen.setId(4);
	}

	@After
	public void teardown() {
		PetSer = null;
	}

	@Parameterized.Parameters
	public static Collection<Object[]> parameters() {
		return Arrays.asList (new Object [][]{
			{1, dog}, {2, horse}, {3, cat}, {4, hen}
		});
	}
	@Test
	public void FindPetTest(){
		Pet found_pet = PetSer.findPet(id);
		assertEquals(pet, found_pet);
		assertNotNull(found_pet);
	}


}
