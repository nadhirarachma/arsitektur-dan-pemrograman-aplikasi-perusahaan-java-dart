package apap.ta.rumahsehat.restcontroller;

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

import apap.ta.rumahsehat.model.PasienModel;
import apap.ta.rumahsehat.model.UserModel;
import apap.ta.rumahsehat.payload.PasienProfileDTO;
import apap.ta.rumahsehat.payload.TopupDTO;
import apap.ta.rumahsehat.service.PasienRestService;
import apap.ta.rumahsehat.service.UserService;

@RestController
@RequestMapping("/api/v1/pasien")
public class PasienRestController {
	
	@Autowired
	private PasienRestService pasienRestService;

	@Autowired
	private UserService userService;

	@Autowired
	private ModelMapper modelMapper;

	@PostMapping("/add")
	private PasienModel createPasien(@Valid @RequestBody PasienModel pasien, BindingResult bindingResult){
		if (bindingResult.hasFieldErrors()) {
			throw new ResponseStatusException(
				HttpStatus.BAD_REQUEST, "Request body has invalid type or missing field"
			);
		}else{
			// createUser(pasien);
			// pasien.setPassword(userService.getUserByUsername(pasien.getUsername()).getPassword());
			return pasienRestService.addPasien(pasien);
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
}
