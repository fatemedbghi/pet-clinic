package org.springframework.samples.petclinic.model;

import org.junit.*;
import java.util.*;
import org.springframework.samples.petclinic.model.priceCalculators.SimplePriceCalculator;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class SimplePriceCalculatorTest {

	public SimplePriceCalculator simplePriceCalculator;
	PetType petType;
	public List<Pet> pets;
	public Pet pet;

	@Before
	public void setup() {
		simplePriceCalculator = new SimplePriceCalculator();
		pet = mock(Pet.class);
		pets = Arrays.asList(pet);
		petType = mock(PetType.class);
	}

	@After
	public void tearDown() {
		simplePriceCalculator = null;
	}

	@Test
	public void calcPrice_rarePet_newUser() {
		when(pet.getType()).thenReturn(petType);
		when(petType.getRare()).thenReturn(true);
		assertEquals(11.4, simplePriceCalculator.calcPrice(pets, 0, 10, UserType.NEW), 0.01);
	}

	@Test
	public void calcPrice_rarePet_oldUser() {
		when(pet.getType()).thenReturn(petType);
		when(petType.getRare()).thenReturn(true);
		assertEquals(12, simplePriceCalculator.calcPrice(pets, 0, 10, UserType.SILVER), 0.01);
	}

	@Test
	public void calcPrice_notRarePet_newUser() {
		when(pet.getType()).thenReturn(petType);
		when(petType.getRare()).thenReturn(false);
		assertEquals(9.5, simplePriceCalculator.calcPrice(pets, 0, 10, UserType.NEW), 0.01);
	}

	@Test
	public void calcPrice_notRarePet_oldUser() {
		when(pet.getType()).thenReturn(petType);
		when(petType.getRare()).thenReturn(false);
		assertEquals(10, simplePriceCalculator.calcPrice(pets, 0, 10, UserType.SILVER), 0.01);
	}

	@Test
	public void calcPrice_noPets_newUser() {
		pets = new ArrayList<>();
		assertEquals(0, simplePriceCalculator.calcPrice(pets, 0, 10, UserType.NEW), 0.01);
	}

	@Test
	public void calcPrice_noPets_oldUser() {
		pets = new ArrayList<>();
		assertEquals(0, simplePriceCalculator.calcPrice(pets, 0, 10, UserType.SILVER), 0.01);
	}

}
