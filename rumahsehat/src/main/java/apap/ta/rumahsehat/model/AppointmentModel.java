package apap.ta.rumahsehat.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;
import reactor.util.annotation.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@NoArgsConstructor
@Setter
@Entity
@Table(name = "appointment")
public class AppointmentModel implements Serializable{

	@Id
    @NotNull
    @Column(name = "kode", nullable = false, unique = true)
    private String kode;

    @NotNull
    @Column(nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime waktuAwal;

    @NotNull
    @Column(name = "isDone", nullable = false)
    private Boolean isDone;

    //relasi dengan pasien
    @Nullable
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "uuidPasien", referencedColumnName = "uuid", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private PasienModel pasien;

    //relasi dengan dokter
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "uuidDokter", referencedColumnName = "uuid", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private DokterModel dokter;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_resep", referencedColumnName = "id")
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    private ResepModel resep;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "kode_tagihan", referencedColumnName = "kode")
    private TagihanModel tagihan;
}
