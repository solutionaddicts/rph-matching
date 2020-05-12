package com.addicts.rph.matching.entity;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

/**
 * @author sravantatikonda
 */
@Data
@Entity
@Table(name = "RPH_PROP_FAV")
@TypeDefs({@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)})
public class FavoriteListings implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @Column(name = "user_id", nullable = false)
  private Integer userId;

  @Type(type = "jsonb")
  @Column(name = "favorites", columnDefinition = "jsonb")
  @Basic(fetch = FetchType.LAZY)
  private ListingIdsJson listingIdsJson;
}
