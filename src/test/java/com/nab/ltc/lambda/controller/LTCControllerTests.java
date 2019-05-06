package com.nab.ltc.lambda.controller;

import static org.hamcrest.CoreMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.nab.ltc.lambda.model.LTC;
import com.nab.ltc.lambda.model.Quote;
import com.nab.ltc.lambda.service.LTCService;


/**
 * @author Tushar Sisode
 *
 */
@RunWith(SpringRunner.class)
@WebMvcTest(LTCController.class)
public class LTCControllerTests {
	
    @Autowired
    private MockMvc mvc;
 
    @MockBean
    private LTCService ltcService;
    
	@Test
	public void testGetLTCQuotes() throws Exception {
    	LTC ltc = new LTC();
    	ltc.setCurrency("LTC");
    	ltc.setDate("20190507");
    	
    	List<Quote> quotes = new ArrayList<Quote>();
    	Quote quote = new Quote();
    	quote.setTime("0915");
    	quote.setPrice("34.98");
    	quotes.add(quote);
		ltc.setQuotes(quotes);

        given(ltcService.filterLTCQuotes("05/07/2019"))
          .willReturn(ltc);
        
	    mvc.perform(get("/ltc?inputDate=05/07/2019")
	    	      .contentType(MediaType.APPLICATION_JSON))
	    	      .andExpect(status().isOk())
	    	      .andExpect(jsonPath("$.date",is(ltc.getDate())))
	    	      .andExpect(jsonPath("$.currency", is(ltc.getCurrency())));
	}

}
