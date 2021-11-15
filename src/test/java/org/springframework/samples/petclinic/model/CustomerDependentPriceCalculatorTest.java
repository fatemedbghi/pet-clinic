package org.springframework.samples.petclinic.model;

import org.junit.*;
import java.util.*;

import org.springframework.samples.petclinic.model.priceCalculators.CustomerDependentPriceCalculator;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class CustomerDependentPriceCalculatorTest {


	public CustomerDependentPriceCalculator customerDependentPriceCalculator;
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
		customerDependentPriceCalculator = new CustomerDependentPriceCalculator();
	}

	@After
	public void tearDown() {
	}

	@Test
	public void calcPrice_rareNotInfantPet_newUser_noDiscount() {
		Date birthday = new GregorianCalendar(2000, 5, 11).getTime();
		when(pet.getBirthDate()).thenReturn(birthday);
		when(pet.getType()).thenReturn(petType);
		when(petType.getRare()).thenReturn(true);
		userType = UserType.NEW;
		assertEquals(12, customerDependentPriceCalculator.calcPrice(pets, baseCharge, basePricePerPet, userType), 0.01);
	}

	@Test
	public void calcPrice_rareNotInfantPet_goldUser_noDiscount() {
		Date birthday = new GregorianCalendar(2000, 5, 11).getTime();
		when(pet.getBirthDate()).thenReturn(birthday);
		when(pet.getType()).thenReturn(petType);
		when(petType.getRare()).thenReturn(true);
		userType = UserType.GOLD;
		assertEquals(9.6, customerDependentPriceCalculator.calcPrice(pets, baseCharge, basePricePerPet, userType), 0.01);
	}

	@Test
	public void calcPrice_rareInfantPet_newUser_noDiscount() {
		Date birthday = new GregorianCalendar(2020, 5, 11).getTime();
		when(pet.getBirthDate()).thenReturn(birthday);
		when(pet.getType()).thenReturn(petType);
		when(petType.getRare()).thenReturn(true);
		userType = UserType.NEW;
		assertEquals(16.8, customerDependentPriceCalculator.calcPrice(pets, baseCharge, basePricePerPet, userType), 0.01);
	}

	@Test
	public void calcPrice_rareInfantPet_goldUser_noDiscount() {
		Date birthday = new GregorianCalendar(2020, 5, 11).getTime();
		when(pet.getBirthDate()).thenReturn(birthday);
		when(pet.getType()).thenReturn(petType);
		when(petType.getRare()).thenReturn(true);
		userType = UserType.GOLD;
		assertEquals(13.44, customerDependentPriceCalculator.calcPrice(pets, baseCharge, basePricePerPet, userType), 0.01);
	}

	@Test
	public void calcPrice_notRareNotInfantPet_newUser_noDiscount() {
		Date birthday = new GregorianCalendar(2000, 5, 11).getTime();
		when(pet.getBirthDate()).thenReturn(birthday);
		when(pet.getType()).thenReturn(petType);
		when(petType.getRare()).thenReturn(false);
		userType = UserType.NEW;
		assertEquals(10, customerDependentPriceCalculator.calcPrice(pets, baseCharge, basePricePerPet, userType), 0.01);
	}

	@Test
	public void calcPrice_notRareNotInfantPet_goldUser_noDiscount() {
		Date birthday = new GregorianCalendar(2000, 5, 11).getTime();
		when(pet.getBirthDate()).thenReturn(birthday);
		when(pet.getType()).thenReturn(petType);
		when(petType.getRare()).thenReturn(false);
		userType = UserType.GOLD;
		assertEquals(8, customerDependentPriceCalculator.calcPrice(pets, baseCharge, basePricePerPet, userType), 0.01);
	}

	@Test
	public void calcPrice_notRareInfantPet_newUser_noDiscount() {
		Date birthday = new GregorianCalendar(2020, 5, 11).getTime();
		when(pet.getBirthDate()).thenReturn(birthday);
		when(pet.getType()).thenReturn(petType);
		when(petType.getRare()).thenReturn(false);
		userType = UserType.NEW;
		assertEquals(12, customerDependentPriceCalculator.calcPrice(pets, baseCharge, basePricePerPet, userType), 0.01);
	}

	@Test
	public void calcPrice_notRareInfantPet_goldUser_noDiscount() {
		Date birthday = new GregorianCalendar(2020, 5, 11).getTime();
		when(pet.getBirthDate()).thenReturn(birthday);
		when(pet.getType()).thenReturn(petType);
		when(petType.getRare()).thenReturn(false);
		userType = UserType.GOLD;
		assertEquals(9.6, customerDependentPriceCalculator.calcPrice(pets, baseCharge, basePricePerPet, userType), 0.01);
	}

	@Test
	public void calcPrice_RareInfantPet_newUser_Discount() {
		pets = Arrays.asList(pet,pet,pet,pet,pet);
		Date birthday = new GregorianCalendar(2020, 5, 11).getTime();
		when(pet.getBirthDate()).thenReturn(birthday);
		when(pet.getType()).thenReturn(petType);
		when(petType.getRare()).thenReturn(true);
		userType = UserType.NEW;
		assertEquals(89.8, customerDependentPriceCalculator.calcPrice(pets, 10, basePricePerPet, userType), 0.01);
	}

	@Test
	public void calcPrice_RareInfantPet_goldUser_Discount() {
		pets = Arrays.asList(pet,pet,pet,pet,pet);
		Date birthday = new GregorianCalendar(2020, 5, 11).getTime();
		when(pet.getBirthDate()).thenReturn(birthday);
		when(pet.getType()).thenReturn(petType);
		when(petType.getRare()).thenReturn(true);
		userType = UserType.GOLD;
		assertEquals(75.2, customerDependentPriceCalculator.calcPrice(pets, 10, basePricePerPet, userType), 0.01);
	}
}
