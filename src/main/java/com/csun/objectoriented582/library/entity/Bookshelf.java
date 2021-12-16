package com.csun.objectoriented582.library.entity;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @Author HaoLu
 * @Date 2021-12-14 18:33:51 
 */
@Data
@Entity
@Table ( name ="bookshelf" )
public class Bookshelf  implements Serializable {

	private static final long serialVersionUID =  7147492817053952260L;

	@Id
   	@Column(name = "id" )
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

   	@Column(name = "bookshelf_code" )
	private String bookshelfCode;

   	@Column(name = "bookshelf_capacity" )
	private Long bookshelfCapacity;

   	@Column(name = "bookshelf_color" )
	private String bookshelfColor;

   	@Column(name = "bookshelf_layers" )
	private Long bookshelfLayers;

    @Column(name = "created")
    private LocalDateTime created;

    @Column(name = "updated")
    private LocalDateTime updated;

	@ManyToOne
	@JoinColumn(name="room_id")
    private Room room;
}
