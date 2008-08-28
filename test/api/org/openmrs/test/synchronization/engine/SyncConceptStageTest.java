/**
 * The contents of this file are subject to the OpenMRS Public License
 * Version 1.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * http://license.openmrs.org
 *
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
 * License for the specific language governing rights and limitations
 * under the License.
 *
 * Copyright (C) OpenMRS, LLC.  All Rights Reserved.
 */
package org.openmrs.test.synchronization.engine;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.HashSet;

import org.junit.Test;
import org.openmrs.Concept;
import org.openmrs.ConceptAnswer;
import org.openmrs.ConceptName;
import org.openmrs.ConceptNumeric;
import org.openmrs.ConceptSet;
import org.openmrs.ConceptSynonym;
import org.openmrs.api.ConceptService;
import org.openmrs.api.context.Context;
import org.openmrs.test.testutil.BaseContextSensitiveTest;
import org.openmrs.util.OpenmrsConstants;
import org.springframework.test.annotation.NotTransactional;

/**
 *
 */
public class SyncConceptStageTest extends BaseContextSensitiveTest {

	@Test
	public void shouldConceptsFromStage() throws Exception {
		
		ConceptService cs = Context.getConceptService();		
		
		/* do concept update */
        Concept wt =  new ConceptNumeric(5089); //weight
        wt.addName(new ConceptName("WEIGHT (KG)", null, "JUNIT TEST: Weight in kg", OpenmrsConstants.GLOBAL_DEFAULT_LOCALE));
        wt.setSynonyms(new HashSet<ConceptSynonym>());
        wt.setAnswers(new HashSet<ConceptAnswer>());
        wt.setConceptSets(new HashSet<ConceptSet>());
        wt.setDatatype(cs.getConceptDatatypeByName("Numeric"));
        wt.setConceptClass(cs.getConceptClassByName("Misc"));

        //now update it
        cs.saveConcept(wt);

        Concept c = cs.getConceptByName("WEIGHT (KG)");
		assertNotNull("Failed to update numeric", c);
		assertEquals(c.getName(), wt.getName());
		assertEquals(c.getName().getDescription(), wt.getName().getDescription());
		
		/* do concept create */
		
	}	
}
