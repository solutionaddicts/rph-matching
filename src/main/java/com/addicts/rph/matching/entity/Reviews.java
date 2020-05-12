package com.addicts.rph.matching.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;


/**
 * @author sravantatikonda
 */
@Data
public class Reviews implements Serializable {

  private static final long serialVersionUID = 1L;

  private List<Review> reviews = new ArrayList<>();

}
