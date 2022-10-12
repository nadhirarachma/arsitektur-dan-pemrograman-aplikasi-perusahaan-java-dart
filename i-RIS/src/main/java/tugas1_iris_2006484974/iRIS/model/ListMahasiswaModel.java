package tugas1_iris_2006484974.iRIS.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "list_mahasiswa")
public class ListMahasiswaModel implements Serializable {
    @Id
    private String id;

    @OneToMany(mappedBy = "uuid")
    List<MahasiswaModel> listMahasiswa;
}
