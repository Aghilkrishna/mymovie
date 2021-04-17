package com.app.mymovieserver.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;

import lombok.Data;
import lombok.ToString;


/**
 * @author aghil
 *
 */
@Entity
@Data
@ToString
@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
public class MovieScreen implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	long id;

	String name;

	Boolean status;

	Date screeningStartTime;

	Date screeningEndTime;

	Long createdBy;

	Long modifiedBy;

	Date createdTime;

	Date modifiedTime;

	@OneToOne
	Movie movie;

}
