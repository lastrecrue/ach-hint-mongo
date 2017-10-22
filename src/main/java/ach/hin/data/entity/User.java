package ach.hin.data.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.mockito.exceptions.misusing.FriendlyReminderException;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@EqualsAndHashCode(exclude = "friends")
@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
@Document
public class User {

	@Id
	private String id;

	@NonNull
	private String name;

	private Date birthDate;

	@DBRef(lazy = true)
	@JsonBackReference
	private Friends friends;

	private Set<Car> cars = new HashSet<>();

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

}
