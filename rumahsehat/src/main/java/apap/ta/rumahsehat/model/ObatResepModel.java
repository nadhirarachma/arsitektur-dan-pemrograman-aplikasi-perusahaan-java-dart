package apap.ta.rumahsehat.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "obat_resep")
public class ObatResepModel {
    @NotNull
    @Column(name = "jumlah", nullable = false)
    private Integer jumlah;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Relation dengan ObatModel
    @ManyToOne
    @JoinColumn(name = "obat", referencedColumnName = "id_obat", nullable = false)
    private ObatModel obat;

    // Relation dengan ResepModel
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "resep", referencedColumnName = "id", nullable = false)
    private ResepModel resep;
}
