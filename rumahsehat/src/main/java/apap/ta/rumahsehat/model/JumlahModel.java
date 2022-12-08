package apap.ta.rumahsehat.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "jumlah")
public class JumlahModel {
    @NotNull
    @Column(name = "kuantitas", nullable = false)
    private Integer kuantitas;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Relation dengan ObatModel
    @ManyToOne
    @JoinColumn(name = "obat", referencedColumnName = "id_obat", nullable = false)
    private ObatModel obat;

    // Relation dengan ResepModel
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name = "resep", referencedColumnName = "id", nullable = false)
    private ResepModel resep;
}
