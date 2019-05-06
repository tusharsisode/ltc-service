package com.nab.ltc.lambda.repository;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nab.ltc.lambda.model.LTC;

/**
 * @author Tushar Sisode
 *
 */
@Repository
public class LTCRepository {

	/**
	 * @return
	 */
	@Cacheable("ltc")
	public List<LTC> fetchLTCQuotes() {
		// Read the JSON file containing the LTC currency data
		List<LTC> ltcList = null;
		ObjectMapper mapper = new ObjectMapper();
		TypeReference<List<LTC>> mapType = new TypeReference<List<LTC>>() {
		};
		InputStream is = TypeReference.class.getResourceAsStream("/json/ltc.json");
		try {
			ltcList = mapper.readValue(is, mapType);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return ltcList;
	}

}
