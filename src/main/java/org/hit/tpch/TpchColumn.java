package org.hit.tpch;

public interface TpchColumn<E extends TpchEntity> {
	String getColumnName();

	TpchColumnType getType();

	double getDouble(E entity);

	long getIdentifier(E entity);

	int getInteger(E entity);

	String getString(E entity);

	int getDate(E entity);

	String TPCH_COLUMN_VALID_PREFIX_REGEX = "(?i)^(p|ps|l|o|s|c|n|r)_";

	default String getSimplifiedColumnName() {
		return getColumnName().replaceFirst(TPCH_COLUMN_VALID_PREFIX_REGEX, "");
	}
}