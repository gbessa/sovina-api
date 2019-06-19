package br.com.gbessa.sovina.dtos;

import java.net.URLEncoder;
import java.util.List;
import java.util.stream.Collectors;

import br.com.gbessa.sovina.models.Store;
import lombok.Getter;

@Getter
public class StoreDto {

	private Long id;
	private String name;
	private String location;
	private String plusCode;

	public StoreDto(Store store) {
		this.id = store.getId();
		this.name = store.getName();
		this.location = store.getLocation();
		this.plusCode = "https://plus.codes/" + URLEncoder.encode(store.getPlusCode());
	}
	
	public static List<StoreDto> toDto(List<Store> stores) {
		return stores.stream().map(StoreDto::new).collect(Collectors.toList());
	}

	public static StoreDto toDto(Store store) {
		return new StoreDto(store);
	}
	
}
