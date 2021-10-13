package org.springframework.samples.petclinic.owner;
import org.springframework.samples.petclinic.visit.Visit;

import org.junit.After;
import org.junit.Before;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.*;

import java.time.LocalDate;
import java.util.*;

@RunWith(Theories.class)
public class PetTest {

	Pet pet;
	public static List<Visit> sorted_visit;
	public static Visit visit1 = new Visit();
	public static Visit visit2 = new Visit();
	public static Visit visit3 = new Visit();

	@Before
	public void setup() {
		pet = new Pet();
	}

	@After
	public void teardown() {
		pet = null;
	}

	@DataPoints
	public static List<List<Visit>> visit_list() {
		visit1.setDate(LocalDate.of(2019,8,13));
		visit2.setDate(LocalDate.of(2021,8,13));
		visit3.setDate(LocalDate.of(2021,10,13));

		List<Visit> visit_list1 = new ArrayList<>();
		visit_list1.add(visit1);
		visit_list1.add(visit2);
		visit_list1.add(visit3);

		List<Visit> visit_list2 = new ArrayList<>();
		visit_list2.add(visit3);
		visit_list2.add(visit2);
		visit_list2.add(visit1);

		sorted_visit = visit_list2;

		List<Visit> visit_list3 = new ArrayList<>();
		visit_list3.add(visit2);
		visit_list3.add(visit3);
		visit_list3.add(visit1);

		List<List<Visit>> visit_list = new ArrayList<>();
		visit_list.add(visit_list1);
		visit_list.add(visit_list2);
		visit_list.add(visit_list3);

		return visit_list;
	}

	@Theory
	public void TestGetVisits(List<Visit> visit_list) {
		assumeTrue(visit_list != null);
		assumeTrue(visit_list.size()>0);
		for(Visit v: visit_list) {
			pet.addVisit(v);
		}
		assertEquals(sorted_visit, pet.getVisits());
	}

}
