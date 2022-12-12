package apap.ta.rumahsehat.restcontroller;

import java.time.LocalDateTime;
import java.util.List;

import javax.validation.Valid;

import org.modelmapper.spi.ErrorMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import apap.ta.rumahsehat.model.PasienModel;
import apap.ta.rumahsehat.model.TagihanModel;
import apap.ta.rumahsehat.payload.BayarDTO;
import apap.ta.rumahsehat.service.PasienRestService;
import apap.ta.rumahsehat.service.PasienService;
import apap.ta.rumahsehat.service.TagihanService;

@RestController
@RequestMapping("/api/v1/tagihan")
public class TagihanRestController {
	
	@Autowired
	TagihanService tagihanService;

	@Autowired
	PasienService pasienService;

	@Autowired
	PasienRestService pasienRestService;

	@GetMapping("/{username}")
	private List<TagihanModel> getListTagihan(@PathVariable String username){
		return tagihanService.getListTagihan(pasienService.getPasienByUsername(username));
	}

	@GetMapping("/detail/{kode}")
	private TagihanModel getTagihan(@PathVariable String kode){
		return tagihanService.getTagihan(kode);
	}

	@PostMapping("/bayar")
	private PasienModel bayarTagihan(@Valid @RequestBody BayarDTO bayar, BindingResult bindingResult) throws Exception{
		PasienModel pasienModel = pasienService.getPasienByUsername(bayar.getUsername());
		TagihanModel tagihanModel = tagihanService.getTagihan(bayar.getKode());

		if (pasienModel.getSaldo() < tagihanModel.getJumlahTagihan()) {
			throw new Exception("saldo kurang");
		}

		tagihanModel.setIsPaid(true);
		tagihanModel.setTanggalBayar(LocalDateTime.now());
		tagihanService.addTagihan(tagihanModel);

		pasienModel.setSaldo(pasienModel.getSaldo() - tagihanModel.getJumlahTagihan());
		pasienRestService.addPasien(pasienModel);

		return pasienModel;
	}
}
