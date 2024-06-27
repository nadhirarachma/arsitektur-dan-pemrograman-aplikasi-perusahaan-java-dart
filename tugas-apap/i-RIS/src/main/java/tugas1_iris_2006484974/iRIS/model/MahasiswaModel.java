package tugas1_iris_2006484974.iRIS.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "mahasiswa")
public class MahasiswaModel implements Serializable {
    @Id
    @Size(max = 255)
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String uuid;

    @NotNull
    @Size(max = 10)
    @Column(name = "npm", unique = true, nullable = false)
    private String npm;

    @NotNull
    @Size(max = 255)
    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @NotNull
    @Size(max = 255)
    @Column(name = "nama_depan", nullable = false)
    private String namaDepan;

    @NotNull
    @Size(max = 255)
    @Column(name = "nama_belakang", nullable = false)
    private String namaBelakang;

    @NotNull
    @Column(name = "status_mahasiswa", nullable = false)
    private int statusMahasiswa;

    // Relasi dengan IRSModel
    @OneToMany(mappedBy = "npmMahasiswa")
    private List<IRSModel> listIRS;
}
