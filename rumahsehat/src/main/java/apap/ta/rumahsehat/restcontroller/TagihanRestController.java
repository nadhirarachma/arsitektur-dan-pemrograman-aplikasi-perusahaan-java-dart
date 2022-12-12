package apap.ta.rumahsehat.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import apap.ta.rumahsehat.model.TagihanModel;
import apap.ta.rumahsehat.service.PasienService;
import apap.ta.rumahsehat.service.TagihanService;

@RestController
@RequestMapping("/api/v1/tagihan")
public class TagihanRestController {
	
	@Autowired
	TagihanService tagihanService;

	@Autowired
	PasienService pasienService;

	@GetMapping("/{username}")
	private List<TagihanModel> getListTagihan(@PathVariable String username){
		return tagihanService.getListTagihan(pasienService.getPasienByUsername(username));
	}
}
