package bt.gov.oag.elms.pojo;

import org.springframework.http.HttpStatus;

import lombok.Data;

@Data
public class CaseInformationResponse {

	public static final String HttpStatus = null;
	private String message;
	private HttpStatus status;
	private String exception;
}
