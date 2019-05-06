package com.nab.ltc.lambda.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nab.ltc.lambda.model.LTC;
import com.nab.ltc.lambda.service.LTCService;

/**
 * @author Tushar Sisode
 *
 */
@RestController
public class LTCController {

	@Autowired
	private LTCService ltcService;
	
	/**
	 * @param inputDate
	 * @return
	 */
	@CrossOrigin() //Not a good practice, but keeping globally open for this solution.
	@GetMapping("/ltc")
	public LTC getLTCQuotes(@RequestParam(value = "inputDate") String inputDate) {
		final Logger logger = LoggerFactory.getLogger(LTCController.class);
		logger.info(".... Fetching LTC Quotes for the Input Date");

		// Filter the LTC data for the inputDate
		LTC filteredLtcQuotes = ltcService.filterLTCQuotes(inputDate);
		logger.info(".... Filtered LTC Quotes for the Input Date");

		// Return the found LTC quote details for the inputDate
		return filteredLtcQuotes;
	}
}
