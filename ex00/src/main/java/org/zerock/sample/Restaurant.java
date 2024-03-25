package org.zerock.sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Data
@Component
@RequiredArgsConstructor
public class Restaurant {
	
	@Setter(onMethod_ = @Autowired)
	//@NonNull
	private Chef chef;
	//final 
	
	@Autowired
	public void setChef(Chef chef) {
		this.chef =chef;
	}

	//생성자 주입(묵시적)
//	public Restaurant(Chef chef) {
//		this.chef = chef;
//	}
	
}
