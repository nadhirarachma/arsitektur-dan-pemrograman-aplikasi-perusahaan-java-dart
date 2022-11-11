package apap.ta.rumahsehat.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="resep")
public class ResepModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "isDone", nullable = false)
    private Boolean isDone;

    @NotNull
    @Column(nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime createdAt;

    @OneToOne(cascade=CascadeType.ALL)
    private AppointmentModel appointment;

    //Relasi dengan ObatModel
    @OneToMany(mappedBy = "obat", cascade = CascadeType.ALL)
    private List<ObatResepModel> obatResep;

    @ManyToOne(fetch= FetchType.EAGER, optional = false)
    @JoinColumn(name = "confirmer_uuid", referencedColumnName= "uuid", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private ApotekerModel apotek;
}
