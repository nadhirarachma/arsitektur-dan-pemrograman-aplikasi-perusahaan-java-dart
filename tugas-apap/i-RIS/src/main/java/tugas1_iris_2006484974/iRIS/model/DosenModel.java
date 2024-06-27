package tugas1_iris_2006484974.iRIS.model;

import java.io.Serializable;
import java.util.List;

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
@Table(name = "dosen")
public class DosenModel implements Serializable {
    @Id
    @Size(max = 10)
    private String nip;

    @NotNull
    @Size(max = 255)
    @Column(name = "nama_depan", nullable = false)
    private String namaDepan;

    @NotNull
    @Size(max = 255)
    @Column(name = "nama_belakang", nullable = false)
    private String namaBelakang;

    @OneToMany(mappedBy = "dosen")
    List<DosenMataKuliahModel> dosenMataKuliah;
}
