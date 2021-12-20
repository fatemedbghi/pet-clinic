package org.springframework.samples.petclinic.utility;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.samples.petclinic.owner.Pet;
import org.springframework.samples.petclinic.visit.Visit;
import org.junit.*;
import java.time.LocalDate;
import java.util.*;
import static org.junit.Assert.assertEquals;

@SpringBootTest
public class PriceCalculatorTest {

	double baseCharge;
	double basePricePerPet;
	public PriceCalculator priceCalculator;
	public List<Pet> pets;
	public Pet pet;
	public Visit visit1;
	public Visit visit2;

	@Before
	public void setup() {
		priceCalculator = new PriceCalculator();
		baseCharge = 5;
		basePricePerPet = 10;
		pets = new ArrayList<>();
		visit1 = new Visit().setDate(LocalDate.now().minusDays(1));
		visit2 = new Visit().setDate(LocalDate.now().minusDays(100));
	}

	@Test
	public void calcPrice_notInfant_noDiscount() {
		pet = new Pet();
		pet.setBirthDate(LocalDate.now().minusYears(3));
		pets.add(pet);
		assertEquals(12, priceCalculator.calcPrice(pets, baseCharge, basePricePerPet), 0.01);
	}

	@Test
	public void calcPrice_notInfant_visitRecently() {
		for(int i=0;i<10;i++)
		{
			pet = new Pet();
			pet.setBirthDate(LocalDate.now().minusYears(3));
			pet.addVisit(visit1);
			pets.add(pet);
		}
		assertEquals(233, priceCalculator.calcPrice(pets, baseCharge, basePricePerPet), 0.01);
	}

	@Test
	public void calcPrice_notInfant_visitNotRecently() {
		for(int i=0;i<10;i++)
		{
			pet = new Pet();
			pet.setBirthDate(LocalDate.now().minusYears(3));
			pet.addVisit(visit2);
			pets.add(pet);
		}
		assertEquals(238, priceCalculator.calcPrice(pets, baseCharge, basePricePerPet), 0.01);
	}

	@Test
	public void calcPrice_infant_noDiscount() {
		pet = new Pet();
		pet.setBirthDate(LocalDate.now().minusYears(1));
		pets.add(pet);
		assertEquals(16.8, priceCalculator.calcPrice(pets, baseCharge, basePricePerPet), 0.01);
	}

	@Test
	public void calcPrice_infant_visitRecently() {
		for(int i=0;i<10;i++)
		{
			pet = new Pet();
			pet.setBirthDate(LocalDate.now().minusYears(1));
			pet.addVisit(visit1);
			pets.add(pet);
		}
		assertEquals(5674.2, priceCalculator.calcPrice(pets, baseCharge, basePricePerPet), 0.01);
	}

	@Test
	public void calcPrice_infant_visitNotRecently() {
		for(int i=0;i<10;i++)
		{
			pet = new Pet();
			pet.setBirthDate(LocalDate.now().minusYears(1));
			pet.addVisit(visit2);
			pets.add(pet);
		}
		assertEquals(5989.2, priceCalculator.calcPrice(pets, baseCharge, basePricePerPet), 0.01);
	}

	@Test
	public void calcPrice_bothPet_bothVisit() {
		for (int i = 0; i < 5; i++) {
			pet = new Pet();
			pet.setBirthDate(LocalDate.now().minusYears(1));
			if(i%2==0) {
				pet.addVisit(visit1);
			}
			else {
				pet.addVisit(visit2);
			}
			pets.add(pet);
		}
		for (int i = 0; i < 5; i++) {
			pet = new Pet();
			pet.setBirthDate(LocalDate.now().minusYears(3));
			if(i%2==0) {
				pet.addVisit(visit1);
			}
			else {
				pet.addVisit(visit2);
			}
			pets.add(pet);
		}
		assertEquals(5575.4, PriceCalculator.calcPrice(pets, baseCharge, basePricePerPet),0.01);
	}

	@Test
	public void calcPrice_noPet() {
		assertEquals(0, priceCalculator.calcPrice(pets, baseCharge, basePricePerPet), 0.01);
	}
}
