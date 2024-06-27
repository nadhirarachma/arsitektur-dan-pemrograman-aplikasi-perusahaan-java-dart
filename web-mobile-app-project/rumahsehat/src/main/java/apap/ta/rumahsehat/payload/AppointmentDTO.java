package apap.ta.rumahsehat.payload;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AppointmentDTO {

    private LocalDateTime tanggal; // format yyyy-MM-DD
    private String username; // balikin uuid aja dari flutter
}
