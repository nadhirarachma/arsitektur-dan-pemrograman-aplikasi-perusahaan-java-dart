package apap.ta.rumahsehat.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter @Getter
@Entity
@Table(name = "pasien")
public class PasienModel extends UserModel{

	@NotNull
	@Column(name = "saldo", nullable = false)
	private Integer saldo;

	@NotNull
	@Column(name = "umur", nullable = false)
	private Integer umur;

	@ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "kodeAppointment", referencedColumnName = "kode", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private AppointmentModel appointment;
}
