package tugas1_iris_2006484974.iRIS.model;

import java.util.List;
import java.io.Serializable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "irs")
public class IRSModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long idIRS;

    @NotNull
    @Size(max = 255)
    @Column(name = "status", nullable = false)
    private String status;

    @NotNull
    @Size(max = 8)
    @Column(name = "semester", nullable = false)
    private String semester;

    @NotNull
    @Column(name = "jumlah_sks", nullable = false)
    private int jumlahSks;

    // Relasi dengan MahasiswaModel
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "npm_mahasiswa", referencedColumnName = "npm", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private MahasiswaModel npmMahasiswa;

    // Relasi dengan MataKuliahModel
    @ManyToMany
    @JoinTable(name = "irs_mata_kuliah", joinColumns = @JoinColumn(name = "id_irs"), inverseJoinColumns = @JoinColumn(name = "id_mata_kuliah"))
    List<MataKuliahModel> listMataKuliah;
}
