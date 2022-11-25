package apap.ta.rumahsehat.payload;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Data
public class AppointmentDTO {

    private LocalDateTime tanggal; // format yyyy-MM-DD
    private String username; // balikin uuid aja dari flutter
}
