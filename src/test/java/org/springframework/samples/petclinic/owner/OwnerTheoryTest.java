package org.springframework.samples.petclinic.owner;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import static org.junit.Assume.assumeTrue;

@RunWith(Theories.class)
public class OwnerTheoryTest{

	Owner owner;

	@Before
	public void setup() {
		owner = new Owner();
	}

	@After
	public void teardown() {
		owner = null;
	}

	@DataPoints
	public static String[] pet_names = {"Max", "Coco", "Charlie", "Milo", null, "   "};

	@DataPoints
	public static boolean[] ignore_new = {true, false};

	@Theory
	public void TestGetPet(String pet_name) {
		assumeTrue(pet_name != null);
		assumeTrue(pet_name.trim().length() > 0);

		Pet pet = null;
		for (int i=0; i<pet_names.length-2; i++) {
			Pet pet_temp = new Pet();
			pet_temp.setName(pet_names[i]);
			owner.addPet(pet_temp);
			if (pet_names[i] == pet_name) {pet = pet_temp;}
		}

		assertEquals(pet, owner.getPet(pet_name));
	}
}
