package org.hit.tpch;
public class TpchColumnTypes
{
    private TpchColumnTypes() {}

    public static final TpchColumnType INTEGER = new TpchColumnType(TpchColumnType.Base.INTEGER);
    public static final TpchColumnType IDENTIFIER = new TpchColumnType(TpchColumnType.Base.IDENTIFIER);
    public static final TpchColumnType DATE = new TpchColumnType(TpchColumnType.Base.DATE);
    public static final TpchColumnType DOUBLE = new TpchColumnType(TpchColumnType.Base.DOUBLE);

    public static TpchColumnType varchar(long precision) {
        return new TpchColumnType(TpchColumnType.Base.VARCHAR, precision);
    }
}