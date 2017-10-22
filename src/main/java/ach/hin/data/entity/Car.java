package ach.hin.data.entity;

import java.util.Date;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
public class Car {

	@Id
	private String id;
	@Version
	private Long version;
	
	@NonNull
	private String registrationNumber;
	
	@NonNull
	private Model model;
	
	@LastModifiedDate
	private Date lasteModified;
	
	@CreatedDate
	private Date createdDate; 

}
