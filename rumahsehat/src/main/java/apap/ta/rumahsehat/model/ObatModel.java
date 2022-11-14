package apap.ta.rumahsehat.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="obat")
public class ObatModel {
    @Id
    @Size (max=20)
    @Column(name = "id_obat", nullable = false)
    private String idObat;

    @NotNull
    @Size(max = 50)
    @Column(name = "nama_obat", nullable = false)
    private String namaObat;

    @NotNull
    @Column(name = "stok", columnDefinition = "integer default 100")
    private Integer stok;

    @NotNull
    @Column(name = "harga", nullable = false)
    private Integer harga;

    //Relasi dengan ResepModel
    @OneToMany(mappedBy = "obat", cascade = CascadeType.ALL)
    private List<JumlahModel> jumlah;
}
