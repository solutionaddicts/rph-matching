package com.addicts.rph.matching.dto;

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
public class StringList implements Serializable {

  private static final long serialVersionUID = 1L;

  transient Set<String> set;
}
