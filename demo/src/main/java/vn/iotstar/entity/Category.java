package vn.iotstar.entity;

import java.io.Serializable;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "categories")
public class Category implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CategoryId")
	private long id;
	@Column(name = "Categoryname", columnDefinition = "VARCHAR(200)")
	private String name;
	@Column(name = "Images", columnDefinition = "VARCHAR(255)")
	private String images;
	@Column(name = "Status")
	private int status;
}
