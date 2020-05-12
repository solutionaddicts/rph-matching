package com.addicts.rph.matching.entity;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import java.io.Serializable;
import java.util.Set;
import lombok.Data;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

/**
 * @author sravantatikonda
 */
@Data
@TypeDefs({@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)})
public class ListingIdsJson implements Serializable {

  private static final long serialVersionUID = 1L;

  private Set<Long> listingIds;
}
