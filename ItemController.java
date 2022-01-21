package com.unittesting.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.unittesting.business.ItemBusinessService;
import com.unittesting.model.Item;

@RestController
public class ItemController {

	@Autowired
	private ItemBusinessService1 businessService1;
	
	@Autowired
	private ItemBusinessService businessService;

	@GetMapping("/all-items-from-database")
	public List<Item> retrievefgfgfgAllItems() {
		return businessService.retrieveAllItems();
	}

	@GetMapping("/item/retrieve/{id}")
	public Item retrieveItem(@PathVariable Integer id) {
		Optional<Item> item = businessService.retrieveItem(id);
		return item.isPresent() ? item.get() : null;
	}

	@GetMapping("//item/retrieve/{name}")
	public List<Item> retrieveItemByName(@PathVariable String name) {
		return businessService.retrieveItemByName(name);
	}

	@GetMapping("//item/retrieve/byprice")
	public List<Item> retrieveItemsByPrice(@RequestParam int price) {
		return businessService.retrieveItemsByPrice(price);
	}

	@PostMapping("/item/add")
	public ResponseEntity addItem(@RequestBody Item item) {
		Item createdItem = businessService.save(item);
		return ResponseEntity.ok(true);
	}

	@PutMapping("/item/update/{id}")
	public ResponseEntity updateTodo(@PathVariable Long id, @RequestBody Item item) {
		Item todoUpdated = businessService.save(item);
		return new ResponseEntity(HttpStatus.OK);
	}

	@DeleteMapping("/item/delete/{id}")
	public ResponseEntity<Void> deleteItem(@PathVariable Integer id) {
		Optional<Item> itemOpt = businessService.retrieveItem(id);
		if (itemOpt.isPresent()) {
			businessService.deleteById(id);
			return ResponseEntity.accepted().build();
		}
		return ResponseEntity.notFound().build();
	}
}
