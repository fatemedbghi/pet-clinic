package org.springframework.samples.petclinic.utility;

import com.github.mryf323.tractatus.*;
import com.github.mryf323.tractatus.experimental.extensions.ReportingExtension;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ExtendWith(ReportingExtension.class)
class TriCongruenceTest {

	private static final Logger log = LoggerFactory.getLogger(TriCongruenceTest.class);

	@Test
	public void sampleTest() {
		Triangle t1 = new Triangle(2, 3, 7);
		Triangle t2 = new Triangle(7, 2, 3);
		boolean areCongruent = TriCongruence.areCongruent(t1, t2);
		log.debug("Triangles identified as '{}'.", areCongruent ? "Congruent" : "Not Congruent");
		Assertions.assertFalse(areCongruent);
	}

	/*
	part1 line 14
	a = t1arr[0] != t2arr[0]
	b = t1arr[1] != t2arr[1]
	c = t1arr[2] != t2arr[2]
	p -> a + b + c
	CUTPNFP -> Corresponding unique true points and near false points
	unique true points -> {TFF,FTF,FFT}
	near false points -> {FFF}
	 */

	@Test
	public void TestAreCongruentTFF()
	{
		Triangle t1 = new Triangle(1, 4, 4);
		Triangle t2 = new Triangle(2, 4, 4);
		boolean areCongruent = TriCongruence.areCongruent(t1, t2);
		log.debug("Triangles identified as '{}'.", areCongruent ? "Congruent" : "Not Congruent");
		Assertions.assertFalse(areCongruent);
	}

	@Test
	public void TestAreCongruentFTF()
	{
		Triangle t1 = new Triangle(2, 4, 4);
		Triangle t2 = new Triangle(2, 3, 4);
		boolean areCongruent = TriCongruence.areCongruent(t1, t2);
		log.debug("Triangles identified as '{}'.", areCongruent ? "Congruent" : "Not Congruent");
		Assertions.assertFalse(areCongruent);
	}

	@Test
	public void TestAreCongruentFFT()
	{
		Triangle t1 = new Triangle(2, 4, 4);
		Triangle t2 = new Triangle(2, 4, 5);
		boolean areCongruent = TriCongruence.areCongruent(t1, t2);
		log.debug("Triangles identified as '{}'.", areCongruent ? "Congruent" : "Not Congruent");
		Assertions.assertFalse(areCongruent);
	}

	@Test
	public void TestAreCongruentFFF()
	{
		Triangle t1 = new Triangle(2, 4, 5);
		Triangle t2 = new Triangle(2, 4, 5);
		boolean areCongruent = TriCongruence.areCongruent(t1, t2);
		log.debug("Triangles identified as '{}'.", areCongruent ? "Congruent" : "Not Congruent");
		Assertions.assertTrue(areCongruent);
	}



	/**
	 * TODO
	 * explain your answer here
	 */
	private static boolean questionTwo(boolean a, boolean b, boolean c, boolean d, boolean e) {
		boolean predicate = false;
//		predicate = a predicate with any number of clauses
		return predicate;
	}
}
