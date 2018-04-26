package com.example.demo.service.export;

import java.util.function.Function;

import com.example.demo.dto.ClientDTO;

public class ExporterCSV {


	public void addcolumn(String headerName, Function<ClientDTO, Object> function) {
		// TODO Auto-generated method stub
		headers.add(headerName);
		functions.add(function);
	}
}
