package com.addicts.rph.matching.config.database.dialect;

import com.addicts.rph.matching.config.database.function.ContainsJsonFunction;
import com.vladmihalcea.hibernate.type.json.JsonNodeBinaryType;
import java.sql.Types;
import org.hibernate.dialect.PostgreSQL82Dialect;
import org.hibernate.dialect.function.SQLFunctionTemplate;
import org.hibernate.type.StandardBasicTypes;

/**
 * @author sravantatikonda
 */
public final class PostgreSQLDialect extends PostgreSQL82Dialect {

  public PostgreSQLDialect() {
    super();

    registerFunction("jsonb_extract_path_boolean",
        new SQLFunctionTemplate(StandardBasicTypes.BOOLEAN,
            "jsonb_extract_path_text(?1, ?2)::boolean"));
    registerFunction("jsonb_extract_path_integer",
        new SQLFunctionTemplate(StandardBasicTypes.INTEGER,
            "jsonb_extract_path_text(?1, ?2)::integer"));
    registerFunction("jsonb_extract_path_double",
        new SQLFunctionTemplate(StandardBasicTypes.DOUBLE,
            "jsonb_extract_path_text(?1, ?2)::double precision"));

    registerFunction("contains_json", new ContainsJsonFunction());

    this.registerHibernateType(
        Types.OTHER, JsonNodeBinaryType.class.getName()
    );
  }
}
