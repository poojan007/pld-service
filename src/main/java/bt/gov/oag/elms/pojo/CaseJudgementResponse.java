package bt.gov.oag.elms.pojo;

import org.springframework.http.HttpStatus;

import lombok.Data;

@Data
public class CaseJudgementResponse {

	private String message;
	private HttpStatus status;
	private String exception;
}
