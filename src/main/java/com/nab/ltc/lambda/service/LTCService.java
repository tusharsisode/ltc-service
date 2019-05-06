package com.nab.ltc.lambda.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nab.ltc.lambda.repository.LTCRepository;
import com.nab.ltc.lambda.model.LTC;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

/**
 * @author Tushar Sisode
 *
 */
@Service
public class LTCService {

	@Autowired
	private LTCRepository ltcRepository;

	/**
	 * @param inputDate
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "fallbackLTCQuote")
	public LTC filterLTCQuotes(String inputDate) {
		// Convert the inputDate in desired format
		String localDateyyyyMMdd = formatInputDate(inputDate);

		// Get LTC quotes from the repository
		List<LTC> ltcQuotes = ltcRepository.fetchLTCQuotes();

		// Filter LTC quotes for the input date
		LTC filteredLtcQuotes = ltcQuotes.stream()
				.filter(d -> d.getDate().equals(localDateyyyyMMdd)).findAny()
				.orElse(new LTC());

		// Return filtered LTC Quotes
		return filteredLtcQuotes;
	}
	
	/**
	 * @return
	 */
	public LTC fallbackLTCQuote(String inputDate) {
		  return new LTC();
	}

	/**
	 * @param inputDate
	 * @return
	 */
	private String formatInputDate(String inputDate) {
		DateTimeFormatter formatterMMddyyyy = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		LocalDate localDateMMddyyyy = LocalDate.parse(inputDate, formatterMMddyyyy);

		DateTimeFormatter formatteryyyyMMdd = DateTimeFormatter.ofPattern("yyyyMMdd");
		String localDateyyyyMMdd = formatteryyyyMMdd.format(localDateMMddyyyy);
		return localDateyyyyMMdd;
	}
}
