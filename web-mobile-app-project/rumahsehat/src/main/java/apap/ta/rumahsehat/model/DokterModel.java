package apap.ta.rumahsehat.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter @Getter
@Entity
@Table(name = "dokter")
public class DokterModel extends UserModel{

	@NotNull
	@Column(name = "tarif", nullable = false)
	private Integer tarif;

	@OneToMany(mappedBy = "dokter", cascade = CascadeType.ALL)
	@JsonIgnore
    private List<AppointmentModel> appointment;
}
