package com.nab.ltc.lambda.service;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.nab.ltc.lambda.model.LTC;
import com.nab.ltc.lambda.model.Quote;
import com.nab.ltc.lambda.repository.LTCRepository;

/**
 * @author Tushar Sisode
 *
 */
@RunWith(SpringRunner.class)
public class LTCServiceTests {
	
    @TestConfiguration
    static class LTCServiceTestContextConfiguration {
  
        @Bean
        public LTCService ltcService() {
            return new LTCService();
        }
    }
 
    @Autowired
    private LTCService ltcService;
 
    @MockBean
    private LTCRepository ltcRepository;
    
    @Before
    public void setUp() {
    	List<LTC> ltcQuotes = new ArrayList<LTC>();
    	
    	LTC ltc = new LTC();
    	ltc.setCurrency("LTC");
    	ltc.setDate("20190507");
    	
    	List<Quote> quotes = new ArrayList<Quote>();
    	Quote quote = new Quote();
    	quote.setTime("0915");
    	quote.setPrice("34.98");
    	quotes.add(quote);
		ltc.setQuotes(quotes);

		ltcQuotes.add(ltc);
		
        Mockito.when(ltcRepository.fetchLTCQuotes())
          .thenReturn(ltcQuotes);
    }
    
	@Test
	public void testFilterLTCQuotes() {
		LTC ltc = ltcService.filterLTCQuotes("05/07/2019");
		assertEquals("20190507", ltc.getDate());
		assertEquals("LTC", ltc.getCurrency());
		assertEquals("0915", ltc.getQuotes().get(0).getTime());
		assertEquals("34.98", ltc.getQuotes().get(0).getPrice());
	}

}
