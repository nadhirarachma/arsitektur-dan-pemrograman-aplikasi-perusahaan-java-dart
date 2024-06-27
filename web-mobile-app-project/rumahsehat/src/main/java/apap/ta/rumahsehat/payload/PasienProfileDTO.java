package apap.ta.rumahsehat.payload;

import lombok.Data;

@Data
public class PasienProfileDTO {
	private String username;
	private String email;
	private String nama;
	private Integer saldo;
}
