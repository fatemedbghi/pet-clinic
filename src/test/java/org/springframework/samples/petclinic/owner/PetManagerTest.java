package org.springframework.samples.petclinic.owner;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.samples.petclinic.utility.PetTimedCache;
import org.springframework.samples.petclinic.visit.Visit;

import java.time.LocalDate;
import java.util.List;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PetManagerTest {

	@Mock
	PetTimedCache pet_time_cache;
	@Mock
	OwnerRepository owners;
	@Mock
	Logger log;
	@InjectMocks
	private PetManager pet_manager;

	private Pet pet1, pet2, pet3;
	private  PetType pet_type1, pet_type2;

	@BeforeEach
	public void SetUp() {
		pet_time_cache = mock(PetTimedCache.class);
		owners = mock(OwnerRepository.class);
		log = mock(Logger.class);
		pet_manager = new PetManager(pet_time_cache, owners, log);

		setPets();
	}

	public void setPets() {
		pet1 = new Pet();
		pet1.setName("jessy");
		pet_type1 = new PetType();
		pet1.setType(pet_type1);

		pet2 = new Pet();
		pet2.setName("jenny");
		pet_type2 = new PetType();
		pet2.setType(pet_type2);

		pet3 = new Pet();
		pet3.setName("jamey");

	}


	// Mockisty, State verification
	@Test
	public void TestFindOwnerNotNull() {
		Owner owner = mock(Owner.class);
		owner.setId(1);
		when(owners.findById(1)).thenReturn(owner);
		assertEquals(owner, pet_manager.findOwner(1));
	}

	// Mockisty, State verification
	@Test
	public void TestFindOwnerNull() {
		when(owners.findById(Mockito.anyInt())).thenReturn(null);
		assertNull(pet_manager.findOwner(1));
	}

	// Mockisty, Behavior verification
	@Test
	public void TestNewPet() {
		Owner owner = mock(Owner.class);
		pet_manager.newPet(owner);
		verify(log).info("add pet for owner {}", owner.getId());
		verify(owner).addPet(isA(Pet.class));
	}

	// Mockisty, State and Behavior ???? verification
	@Test
	public void TestFindPetNotNull() {
		Pet pet = mock(Pet.class);
		pet.setId(1);
		when(pet_time_cache.get(1)).thenReturn(pet);
		assertEquals(pet, pet_manager.findPet(1));
		verify(log).info("find pet by id {}", 1);
	}

	// Mockisty, State verification
	@Test
	public void TestFindPetNull() {
		when(pet_time_cache.get(Mockito.anyInt())).thenReturn(null);
		assertNull(pet_manager.findPet(1));
	}

	// Mockisty, Behavior verification
	@Test
	public void TestSavePet() {
		Owner owner = mock(Owner.class);
		Pet pet = mock(Pet.class);
		pet_manager.savePet(pet, owner);
		verify(log).info("save pet {}", pet.getId());
		verify(owner).addPet(pet);
		verify(pet_time_cache).save(pet);
	}

	// Mockisty, State verification
	@Test
	public void TestGetOwnerPets() {
		Owner owner1 = new Owner();
		owner1.setId(1);
		owner1.addPet(pet1);
		owner1.addPet(pet2);

		when(owners.findById(1)).thenReturn(owner1);

		List<Pet> owner_pets = pet_manager.getOwnerPets(1);
		List<Pet> expected_pets = Arrays.asList(pet1, pet2);

		Collections.sort(expected_pets, (Pet p1, Pet p2) -> p1.getName().compareTo(p2.getName()));
		assertEquals(expected_pets, owner_pets);
		verify(log).info("finding the owner's pets by id {}", owner1.getId());
	}

	// Mockisty - spy , State and behavior verification
	@Test
	public void TestGetOwnerPetTypes() {
		Owner owner1 = new Owner();
		owner1.setId(1);
		owner1.addPet(pet1);
		owner1.addPet(pet2);

		when(owners.findById(1)).thenReturn(owner1);

		Set<PetType> owner_pets_types = pet_manager.getOwnerPetTypes(1);
		Set<PetType> expected_pets_types = new HashSet<PetType>(Arrays.asList(pet_type1, pet_type2));

		assertEquals(expected_pets_types, owner_pets_types);
		verify(log).info("finding the owner's petTypes by id {}", owner1.getId());
	}

	// Mockisty - spy, State and behavior verification
	@Test
	public void TestGetVisitsBetween() {
		int petId = 1;
		Visit visit1 = new Visit().setDate(LocalDate.of(2020,3,1));
		Visit visit2 = new Visit().setDate(LocalDate.of(2020,5,1));
		Visit visit3 = new Visit().setDate(LocalDate.of(2021,8,1));

		pet1.addVisit(visit1);
		pet1.addVisit(visit2);
		pet1.addVisit(visit3);

		LocalDate start = LocalDate.of(2020,4,28);
		LocalDate end = LocalDate.of(2021,8,28);

		when(pet_time_cache.get(petId)).thenReturn(pet1);
		List<Visit> pet_visits = pet_manager.getVisitsBetween(1, start, end);
		List<Visit> expected_visits = Arrays.asList(visit2, visit3);

		assertEquals(expected_visits, pet_visits);
		verify(log).info("get visits for pet {} from {} since {}", petId, start, end);

	}
}
