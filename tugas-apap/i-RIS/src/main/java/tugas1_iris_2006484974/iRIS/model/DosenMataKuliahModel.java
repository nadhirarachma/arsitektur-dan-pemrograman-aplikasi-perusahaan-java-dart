package tugas1_iris_2006484974.iRIS.model;

import java.io.Serializable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "dosen_mata_kuliah")
public class DosenMataKuliahModel implements Serializable {
    @EmbeddedId
    DosenMataKuliahKey key;

    @ManyToOne
    @MapsId("nip")
    @JoinColumn(name = "nip")
    private DosenModel dosen;

    @ManyToOne
    @MapsId("idMataKuliah")
    @JoinColumn(name = "id_mata_kuliah")
    private MataKuliahModel mataKuliah;
    
    @NotNull
    @Size(max = 20)
    @Column(name = "ruang_kelas", nullable = false)
    private String ruangKelas;
}