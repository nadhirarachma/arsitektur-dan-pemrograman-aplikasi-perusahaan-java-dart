package apap.ta.rumahsehat.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter @Getter
@Entity
@JsonIgnoreProperties(value={"uuid", "appointment"}, allowSetters = true)
@Table(name = "pasien")
public class PasienModel extends UserModel{

	@NotNull
	@Column(name = "saldo", nullable = false)
	private Integer saldo;

	@NotNull
	@Column(name = "umur", nullable = false)
	private Integer umur;

	@OneToMany(mappedBy = "pasien", cascade = CascadeType.ALL)
    private List<AppointmentModel> appointment;
}