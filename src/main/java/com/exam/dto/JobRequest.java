package com.exam.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@AllArgsConstructor
//@RequiredArgsConstructor
@Getter
@Setter
public class JobRequest {

	private static final long serialVersionUID = 1L;

	private String jobName;
	private String fileType;
	private String fileInPath;
	private String fileOutPath;
	private String status;


	
}
