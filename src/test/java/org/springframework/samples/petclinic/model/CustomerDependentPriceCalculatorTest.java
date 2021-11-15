package org.springframework.samples.petclinic.model;

import org.junit.*;
import java.util.*;

import org.springframework.samples.petclinic.model.priceCalculators.CustomerDependentPriceCalculator;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class CustomerDependentPriceCalculatorTest {


	public CustomerDependentPriceCalculator customerDependentPriceCalculator;
	double basePricePerPet;
	public List<Pet> pets, more_than_one_pets;
	public Pet pet;
	PetType petType;
	Date birthday_not_infant;
	Date birthday_infant;

	@Before
	public void setup() {
		customerDependentPriceCalculator = new CustomerDependentPriceCalculator();
		pet = mock(Pet.class);
		basePricePerPet = 10;
		pets = Arrays.asList(pet);
		more_than_one_pets = Arrays.asList(pet,pet,pet,pet,pet);
		petType = mock(PetType.class);
		birthday_not_infant = new GregorianCalendar(2000, 5, 11).getTime();
		birthday_infant = new GregorianCalendar(2020, 5, 11).getTime();
	}

	@After
	public void tearDown() {
		customerDependentPriceCalculator = null;
	}

	@Test
	public void calcPrice_rareNotInfantPet_newUser_noDiscount() {
		when(pet.getBirthDate()).thenReturn(birthday_not_infant);
		when(pet.getType()).thenReturn(petType);
		when(petType.getRare()).thenReturn(true);
		assertEquals(12, customerDependentPriceCalculator.calcPrice(pets, 0, basePricePerPet, UserType.NEW), 0.01);
	}

	@Test
	public void calcPrice_rareNotInfantPet_goldUser_noDiscount() {
		when(pet.getBirthDate()).thenReturn(birthday_not_infant);
		when(pet.getType()).thenReturn(petType);
		when(petType.getRare()).thenReturn(true);
		assertEquals(9.6, customerDependentPriceCalculator.calcPrice(pets, 0, basePricePerPet, UserType.GOLD), 0.01);
	}

	@Test
	public void calcPrice_rareInfantPet_newUser_noDiscount() {
		when(pet.getBirthDate()).thenReturn(birthday_infant);
		when(pet.getType()).thenReturn(petType);
		when(petType.getRare()).thenReturn(true);
		assertEquals(16.8, customerDependentPriceCalculator.calcPrice(pets, 0, basePricePerPet, UserType.NEW), 0.01);
	}

	@Test
	public void calcPrice_rareInfantPet_goldUser_noDiscount() {
		when(pet.getBirthDate()).thenReturn(birthday_infant);
		when(pet.getType()).thenReturn(petType);
		when(petType.getRare()).thenReturn(true);
		assertEquals(13.44, customerDependentPriceCalculator.calcPrice(pets, 0, basePricePerPet, UserType.GOLD), 0.01);
	}

	@Test
	public void calcPrice_notRareNotInfantPet_newUser_noDiscount() {
		when(pet.getBirthDate()).thenReturn(birthday_not_infant);
		when(pet.getType()).thenReturn(petType);
		when(petType.getRare()).thenReturn(false);
		assertEquals(10, customerDependentPriceCalculator.calcPrice(pets, 0, basePricePerPet, UserType.NEW), 0.01);
	}

	@Test
	public void calcPrice_notRareNotInfantPet_goldUser_noDiscount() {
		when(pet.getBirthDate()).thenReturn(birthday_not_infant);
		when(pet.getType()).thenReturn(petType);
		when(petType.getRare()).thenReturn(false);
		assertEquals(8, customerDependentPriceCalculator.calcPrice(pets, 0, basePricePerPet, UserType.GOLD), 0.01);
	}

	@Test
	public void calcPrice_notRareInfantPet_newUser_noDiscount() {
		when(pet.getBirthDate()).thenReturn(birthday_infant);
		when(pet.getType()).thenReturn(petType);
		when(petType.getRare()).thenReturn(false);
		assertEquals(12, customerDependentPriceCalculator.calcPrice(pets, 0, basePricePerPet, UserType.NEW), 0.01);
	}

	@Test
	public void calcPrice_notRareInfantPet_goldUser_noDiscount() {
		when(pet.getBirthDate()).thenReturn(birthday_infant);
		when(pet.getType()).thenReturn(petType);
		when(petType.getRare()).thenReturn(false);
		assertEquals(9.6, customerDependentPriceCalculator.calcPrice(pets, 0, basePricePerPet, UserType.GOLD), 0.01);
	}

	@Test
	public void calcPrice_RareInfantPet_newUser_Discount() {
		when(pet.getBirthDate()).thenReturn(birthday_infant);
		when(pet.getType()).thenReturn(petType);
		when(petType.getRare()).thenReturn(true);
		assertEquals(89.8, customerDependentPriceCalculator.calcPrice(more_than_one_pets, 10, basePricePerPet, UserType.NEW), 0.01);
	}

	@Test
	public void calcPrice_RareInfantPet_goldUser_Discount() {
		when(pet.getBirthDate()).thenReturn(birthday_infant);
		when(pet.getType()).thenReturn(petType);
		when(petType.getRare()).thenReturn(true);
		assertEquals(75.2, customerDependentPriceCalculator.calcPrice(more_than_one_pets, 10, basePricePerPet, UserType.GOLD), 0.01);
	}
}
