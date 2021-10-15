package org.springframework.samples.petclinic.owner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
//import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.springframework.samples.petclinic.utility.PetTimedCache;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.springframework.samples.petclinic.utility.PetTimedCache;


import java.util.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith (Parameterized.class)
public class PetServiceTest {
	private PetService PetSer;
	private static Pet dog, cat, horse, hen;
	private final int id;
	private final Pet pet;
	private PetTimedCache PetCache;

	public PetServiceTest(int id, Pet pet) {
		this.id = id;
		this.pet = pet;
		PetCache = mock(PetTimedCache.class);
		OwnerRepository owners = mock(OwnerRepository.class);
		Logger logger = mock(Logger.class);
		PetSer = new PetService(PetCache, owners, logger);
	}

	@Before
	public void setup() {
//		SetPet();
		when(PetCache.get(1)).thenReturn(dog);
		when(PetCache.get(2)).thenReturn(horse);
		when(PetCache.get(3)).thenReturn(cat);
		when(PetCache.get(4)).thenReturn(hen);

	}

	public static void SetPet() {
		dog = new Pet();
		dog.setId(1);
		dog.setName("dog");

		horse = new Pet();
		horse.setId(2);
		horse.setName("horse");

		cat = new Pet();
		cat.setId(3);
		cat.setName("cat");

		hen = new Pet();
		hen.setId(4);
		hen.setName("hen");
	}

	@After
	public void teardown() {
		PetSer = null;
	}

	@Parameterized.Parameters
	public static Collection<Object[]> parameters() {
		SetPet();
		return Arrays.asList (new Object [][]{
			{1, dog}, {2, horse}, {3, cat}, {4, hen}, {5, null}
		});
	}
	@Test
	public void FindPetTest(){
		Pet found_pet = PetSer.findPet(id);
		assertEquals(pet, found_pet);
	}


}
