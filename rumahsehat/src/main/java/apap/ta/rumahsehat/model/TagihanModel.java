package apap.ta.rumahsehat.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@NoArgsConstructor
@Setter
@Entity
@Table(name = "tagihan")
public class TagihanModel implements Serializable {

	@Id
    @NotNull
    @Column(name = "kode", nullable = false, unique = true)
    private String kode;
	
    @NotNull
    @Column(nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime tanggalTerbuat;

    @NotNull
    @Column(nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime tanggalBayar;

    @NotNull
    @Column(name = "isPaid", nullable = false)
    private Boolean isPaid;

    @NotNull
    @Column(name = "jumlah_tagihan", nullable = false)
    private Integer jumlahTagihan;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "kodeAppointment", referencedColumnName = "kode", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private AppointmentModel appointment;
}