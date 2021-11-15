package org.springframework.samples.petclinic.model;

import org.junit.*;
import java.util.*;
import org.springframework.samples.petclinic.model.priceCalculators.SimplePriceCalculator;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class SimplePriceCalculatorTest {

	public SimplePriceCalculator simplePriceCalculator;
	double baseCharge;
	double basePricePerPet;
	PetType petType;
	public List<Pet> pets;
	public Pet pet;
	UserType userType;

	@Before
	public void setup() {
		baseCharge = 0;
		basePricePerPet = 10;
		petType = mock(PetType.class);
		pet = mock(Pet.class);
		pets = Arrays.asList(pet);
		simplePriceCalculator = new SimplePriceCalculator();
	}

	@After
	public void tearDown() {
	}

	@Test
	public void calcPrice_rarePet_newUser() {
		when(pet.getType()).thenReturn(petType);
		when(petType.getRare()).thenReturn(true);
		userType = UserType.NEW;
		assertEquals(11.4, simplePriceCalculator.calcPrice(pets, baseCharge, basePricePerPet, userType), 0.01);
	}

	@Test
	public void calcPrice_rarePet_oldUser() {
		when(pet.getType()).thenReturn(petType);
		when(petType.getRare()).thenReturn(true);
		userType = UserType.SILVER;
		assertEquals(12, simplePriceCalculator.calcPrice(pets, baseCharge, basePricePerPet, userType), 0.01);
	}

	@Test
	public void calcPrice_notRarePet_newUser() {
		when(pet.getType()).thenReturn(petType);
		when(petType.getRare()).thenReturn(false);
		userType = UserType.NEW;
		assertEquals(9.5, simplePriceCalculator.calcPrice(pets, baseCharge, basePricePerPet, userType), 0.01);
	}

	@Test
	public void calcPrice_notRarePet_oldUser() {
		when(pet.getType()).thenReturn(petType);
		when(petType.getRare()).thenReturn(false);
		userType = UserType.SILVER;
		assertEquals(10, simplePriceCalculator.calcPrice(pets, baseCharge, basePricePerPet, userType), 0.01);
	}

}
