package apap.ta.rumahsehat.restcontroller;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import apap.ta.rumahsehat.model.AppointmentModel;
import apap.ta.rumahsehat.model.PasienModel;
import apap.ta.rumahsehat.model.TagihanModel;
import apap.ta.rumahsehat.model.UserModel;
import apap.ta.rumahsehat.payload.ListTagihanDTO;
import apap.ta.rumahsehat.payload.PasienProfileDTO;
import apap.ta.rumahsehat.payload.TopupDTO;
import apap.ta.rumahsehat.service.PasienRestService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/v1/pasien")
public class PasienRestController {
	
	@Autowired
	private PasienRestService pasienRestService;

	@Autowired
	private ModelMapper modelMapper;

	@PostMapping("/add")
	private PasienModel createPasien(@Valid @RequestBody PasienModel pasien, BindingResult bindingResult){
		if (bindingResult.hasFieldErrors()) {
			log.warn("Failed to Create Pasien");
			throw new ResponseStatusException(
				HttpStatus.BAD_REQUEST, "Request body has invalid type or missing field"
			);
		}
		else{
			PasienModel newPasien = pasienRestService.addPasien(pasien);
			if (newPasien.equals(pasien)) {
				log.info("Pasien Successfully Created");
				return newPasien;
			}
			else {
				log.warn("Failed to create pasien. User with same username or email already exist.");
				throw new ResponseStatusException(
					HttpStatus.INTERNAL_SERVER_ERROR, "User dengan username atau email yang sama telah terdapat pada sistem"
				);
			}
		}
	}

	// api login pasien jadi gabisa dipanggil
	// private UserModel createUser(@Valid @RequestBody PasienModel pasien){
	// 	UserModel user = new UserModel();
	// 	user.setNama(pasien.getNama());
	// 	user.setRole("Pasien");
	// 	user.setUsername(pasien.getUsername());
	// 	user.setPassword(pasien.getPassword());
	// 	user.setEmail(pasien.getEmail());
	// 	return userService.addUser(user);
	// }

	@GetMapping("/profile/{username}")
	private PasienProfileDTO getPasienProfile(@PathVariable("username") String username){
		try {
			PasienModel pasien = pasienRestService.getPasienByUsername(username);
			PasienProfileDTO profile = modelMapper.map(pasien, PasienProfileDTO.class);

			return profile;
		} catch (NoSuchElementException e) {
			throw new ResponseStatusException(
				HttpStatus.NOT_FOUND, "Tidak ada pasien dengan username " + username
			);
		}
	}

	@PostMapping("/topup")
	private PasienModel topupSaldoPasien(@Valid @RequestBody TopupDTO topup, BindingResult bindingResult){
		PasienModel pasien = pasienRestService.getPasienByUsername(topup.getUsername());
		pasien.setSaldo(pasien.getSaldo() + topup.getJumlah());
		pasienRestService.addPasien(pasien);
		return pasien;
	}

	@GetMapping("/{username}/tagihan/viewall")
	private ListTagihanDTO lihatDaftarTagihan(@PathVariable("username") String username){
		ListTagihanDTO listTagihanDTO = new ListTagihanDTO();
		PasienModel pasien = pasienRestService.getPasienByUsername(username);
		List<TagihanModel> listTagihan = new ArrayList<>();
		for (AppointmentModel a : pasien.getAppointment()) {
			listTagihan.add(a.getTagihan());
		}

		listTagihanDTO.setNama(username);
		listTagihanDTO.setListTagihan(listTagihan);
		return listTagihanDTO;
	}
}
