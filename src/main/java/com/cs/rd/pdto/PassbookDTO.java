package com.cs.rd.pdto;

import java.time.LocalDate;

public interface PassbookDTO {
	Integer getPid();
	Integer getRid();
	String getName();
	String getAcno();
	Double getRdamt();
	LocalDate getRddate();
	Integer getLday();
	Integer getFamt();
}
