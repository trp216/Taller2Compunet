package co.edu.icesi.dev.uccareapp.transport.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import co.edu.icesi.dev.uccareapp.transport.model.person.Address;
import co.edu.icesi.dev.uccareapp.transport.model.user.UserApp;
import co.edu.icesi.dev.uccareapp.transport.services.AddressServiceImp;
import co.edu.icesi.dev.uccareapp.transport.services.StateprovinceServiceImp;

@Controller
public class AddressControllerImpl {
	
	AddressServiceImp addressService;
	StateprovinceServiceImp spService;

	@Autowired
	public AddressControllerImpl(AddressServiceImp addressService, StateprovinceServiceImp spService) {
	
		this.addressService = addressService;
		this.spService = spService;
	}



	@GetMapping("/address/")
	public String indexAddress(Model model) {
		model.addAttribute("addresses", addressService.findAll());
		return "address/index";
	}
	
	@GetMapping("/address/add")
	public String addAddress(Model model) {
		model.addAttribute("address", new Address());
		model.addAttribute("stateprovinces", spService.findAll());

		return "address/add-address";
	}

	@PostMapping("/address/add/")
	public String saveAddress(@ModelAttribute Address address, BindingResult bindingResult,
			Model model, @RequestParam(value = "action", required = true) String action) {
		if (!action.equals("Cancel")) {
			model.addAttribute("address", address);

			if (bindingResult.hasErrors()) {
				model.addAttribute("provinces", spService.findAll());


				return "/address/add-address";

			}

			addressService.save(address);
		}
		return "redirect:/address/";
	}
	
	

}
