package tugas1_iris_2006484974.iRIS.model;

import java.util.List;
import java.io.Serializable;
import java.time.LocalTime;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "mata_kuliah")
public class MataKuliahModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long idMataKuliah;

    @NotNull
    @Size(max = 14)
    @Column(name = "code", unique = true, nullable = false)
    private String code;

    @NotNull
    @Size(max = 6)
    @Column(name = "semester", nullable = false)
    private String semester;

    @NotNull
    @Column(name = "kapasitas_kelas", nullable = false)
    private int kapasitasKelas;

    @NotNull
    @Column(name = "sks", nullable = false)
    private int sks;

    @NotNull
    @Size(max = 255)
    @Column(name = "nama_matkul", nullable = false)
    private String namaMatkul;

    @NotNull
    @Column(nullable = false)
    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime waktuMulai;

    @NotNull
    @Column(nullable = false)
    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime waktuSelesai;

    @NotNull
    @Size(max = 20)
    @Column(name = "hari", nullable = false)
    private String hari;

    @NotNull
    @Column(name = "total_mahasiswa", nullable = false)
    private int totalMahasiswa;

    // Relasi dengan IRSModel
    @ManyToMany
    @JoinTable(name = "irs_mata_kuliah", joinColumns = @JoinColumn(name = "id_mata_kuliah"), inverseJoinColumns = @JoinColumn(name = "id_irs"))
    List<IRSModel> listIRS;

    @OneToMany(mappedBy = "mataKuliah")
    List<DosenMataKuliahModel> dosenMataKuliah;
}