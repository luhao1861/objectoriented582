package com.csun.objectoriented582.library.entity;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @Author HaoLu
 * @Date 2021-12-14 07:26:27 
 */
@Data
@Entity
@Table ( name = "book")
public class Book  implements Serializable {

	private static final long serialVersionUID =  2393701523812355135L;

	@Id
   	@Column(name = "ID" )
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

   	@Column(name = "book_name" )
	private String bookName;

   	@Column(name = "book_pages" )
	private Long bookPages;

   	@Column(name = "book_author" )
	private String bookAuthor;

   	@Column(name = "book_price" )
	private BigDecimal bookPrice;

   	@Column(name = "book_publisher" )
	private String bookPublisher;

    @Column(name = "created")
    private LocalDateTime created;

    @Column(name = "updated")
    private LocalDateTime updated;

	@ManyToOne
	@JoinColumn(name="bookshelf_id")
    private Bookshelf bookshelf;

}
