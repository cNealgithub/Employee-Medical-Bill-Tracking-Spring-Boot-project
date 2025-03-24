package com.CMPDI.CPRMSE.NE.poratl.services;

import org.springframework.web.multipart.MultipartFile;

public interface Fileservice {

	boolean hasCsvFormat(MultipartFile file);

	void ProcessAndSaveData(MultipartFile file);

}
