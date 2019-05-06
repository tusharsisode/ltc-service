package com.nab.ltc.lambda.repository;

import static org.junit.Assert.assertFalse;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.nab.ltc.lambda.model.LTC;

/**
 * @author Tushar Sisode
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class LTCRepositoryTests {

    @Autowired
    private LTCRepository ltcRepository;
    
	@Test
	public void testFetchLTCQuotes() {
		List<LTC> ltcQuotes = ltcRepository.fetchLTCQuotes();
		assertFalse(ltcQuotes.isEmpty()); // Test if returned list is not empty
	}

}
