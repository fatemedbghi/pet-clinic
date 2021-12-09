package org.springframework.samples.petclinic.owner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.BDDMockito.given;
import org.assertj.core.util.Lists;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.http.MediaType;
import org.springframework.samples.petclinic.utility.PetTimedCache;




@WebMvcTest(value = PetController.class,
	includeFilters = {
		@ComponentScan.Filter(value = PetTypeFormatter.class, type = FilterType.ASSIGNABLE_TYPE),
		@ComponentScan.Filter(value = PetService.class, type = FilterType.ASSIGNABLE_TYPE),
		@ComponentScan.Filter(value = LoggerConfig.class, type = FilterType.ASSIGNABLE_TYPE),
		@ComponentScan.Filter(value = PetTimedCache.class, type = FilterType.ASSIGNABLE_TYPE),
	}
)


class PetControllerTests {
	private static final String VIEWS_PETS_CREATE_OR_UPDATE_FORM = "pets/createOrUpdatePetForm";
	@Autowired
	private MockMvc mvc;

	@MockBean
	private PetRepository pets;

	@MockBean
	private OwnerRepository owners;

	private Owner owner;
	private PetType cat;
	private Pet pet;

	private static final int OWNER_ID = 1;
	private static final int PETTYPE_ID = 10;
	private static final int PET_ID = 1;


	@BeforeEach
	void setup() {
		owner = new Owner();
		owner.setId(OWNER_ID);
		cat = new PetType();
		cat.setId(PETTYPE_ID);
		cat.setName("cat");
		pet = new Pet();
		pet.setId(PET_ID);
		given(pets.findPetTypes()).willReturn(Lists.newArrayList(cat));
		given(owners.findById(OWNER_ID)).willReturn(owner);
		given(pets.findById(PET_ID)).willReturn(pet);
	}


	@Test
	public void testInitCreationFormWorksIdeal() throws Exception {
		mvc.perform(get("/owners/1/pets/new").contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(model().attributeExists("pet"))
			.andExpect(view().name(VIEWS_PETS_CREATE_OR_UPDATE_FORM));

	}

	@Test
	public void testInitUpdateFormWorksIdeal() throws Exception {
		mvc.perform(get("/owners/1/pets/1/edit"))
			.andExpect(status().isOk())
			.andExpect(view().name(VIEWS_PETS_CREATE_OR_UPDATE_FORM))
			.andExpect(model().attributeExists("pet"));
	}

	@Test
	public void testProcessCreationFormWorksIdeal() throws  Exception {
		mvc.perform(post("/owners/1/pets/new")
				.param("name", "pishi")
				.param("type", "cat")
				.param("id", "1")
				.param("BirthDate", "2020-02-02"))
			.andExpect(status().is3xxRedirection())
			.andExpect(view().name("redirect:/owners/{ownerId}"))
			.andExpect(model().attributeDoesNotExist("pet"));
	}
	@Test
	public void ProcessCreationFailsBecauseOfInvalidfeilds() throws  Exception {
		mvc.perform(post("/owners/1/pets/new")
				.param("name", "")
				.param("type", "")
				.param("id", "")
				.param("BirthDate", "2020-02-02"))
			.andExpect(status().isOk())
			.andExpect(view().name(VIEWS_PETS_CREATE_OR_UPDATE_FORM))
			.andExpect(model().attributeExists("pet"));
	}

	@Test
	public void testProcessUpdateFormWorksIdeal() throws  Exception {
		mvc.perform(post("/owners/1/pets/1/edit")
				.param("name", "pishi")
				.param("type", "cat")
				.param("id", "1")
				.param("BirthDate", "2020-02-02"))
			.andExpect(status().is3xxRedirection())
			.andExpect(view().name("redirect:/owners/{ownerId}"))
			.andExpect(model().attributeDoesNotExist("pet"));
	}
	@Test
	public void ProcessUpdateFailswithError() throws  Exception {
		mvc.perform(post("/owners/1/pets/1/edit")
				.param("name", "sag")
				.param("type", "dog")
				.param("id", "2")
				.param("BirthDate", "2020-02-02"))
			.andExpect(status().isOk())
			.andExpect(model().attributeExists("pet"))
			.andExpect(view().name(VIEWS_PETS_CREATE_OR_UPDATE_FORM));
	}

}

