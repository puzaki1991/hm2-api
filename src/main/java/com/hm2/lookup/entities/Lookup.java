package com.hm2.lookup.entities;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.hm2.common.entities.BaseEntity;

import lombok.Data;

@Entity
@Table(name = "T_LOOKUP")
@Data
@AttributeOverride(name = "ID", column = @Column(name = "LOOKUP_ID", nullable = false))
public class Lookup extends BaseEntity {

	private static final long serialVersionUID = -3051635426767530525L;
	
	@Column(name = "LOOKUP_TYPE", length = 100)
	private String type;
	@Column(name = "LOOKUP_LABEL", length = 500)
	private String label;
	@Column(name = "LOOKUP_VALUE", length = 500)
	private String value;
	@Column(name = "LOOKUP_NOTE_TEST", length = 500)
	private String noteTest;
	@Column(name = "LOOKUP_GROUP", length = 100)
	private String group;
	@Column(name = "LOOKUP_GROUP2", length = 100)
	private String group2;
	@Column(name = "LOOKUP_ORDER", length = 100)
	private Integer order = 0;
	@Override
	public String toString() {
		return "Lookup [type=" + type + ", label=" + label + ", value=" + value + ", noteTest=" + noteTest + ", group="
				+ group + ", group2=" + group2 + ", order=" + order + "]";
	}


//	public static Comparator<Lookup> comparatorLkuOrder = new Comparator<Lookup>() {
//
//		public int compare(Student s1, Student s2) {
//
//			int rollno1 = s1.getRollno();
//			int rollno2 = s2.getRollno();
//
//			/*For ascending order*/
//			return rollno1-rollno2;
//
//			/*For descending order*/
//			//rollno2-rollno1;
//		}};
}
