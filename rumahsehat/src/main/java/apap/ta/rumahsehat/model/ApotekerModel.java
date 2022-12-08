package apap.ta.rumahsehat.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter @Getter
@Entity
@Table(name = "apoteker")
public class ApotekerModel extends UserModel{

    @OneToMany(mappedBy = "apotek", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<ResepModel> resep;
}
