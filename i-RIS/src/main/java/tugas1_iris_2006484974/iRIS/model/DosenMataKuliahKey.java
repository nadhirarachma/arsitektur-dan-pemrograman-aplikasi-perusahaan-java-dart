package tugas1_iris_2006484974.iRIS.model;

import java.io.Serializable;
import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class DosenMataKuliahKey implements Serializable {

    @Column(name = "nip")
    private String nip;

    @Column(name = "idMataKuliah")
    private Long idMataKuliah;
}
