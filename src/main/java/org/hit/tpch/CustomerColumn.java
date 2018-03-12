package org.hit.tpch;

import static org.hit.tpch.TpchColumnTypes.IDENTIFIER;
import static org.hit.tpch.TpchColumnTypes.DOUBLE;
import static org.hit.tpch.TpchColumnTypes.varchar;

public enum CustomerColumn
        implements TpchColumn<Customer>
{
    CUSTOMER_KEY("c_custkey", IDENTIFIER)
            {
                public long getIdentifier(Customer customer)
                {
                    return customer.getCustomerKey();
                }
            },

    NAME("c_name", varchar(25))
            {
                public String getString(Customer customer)
                {
                    return customer.getName();
                }
            },

    ADDRESS("c_address", varchar(40))
            {
                public String getString(Customer customer)
                {
                    return customer.getAddress();
                }
            },

    NATION_KEY("c_nationkey", IDENTIFIER)
            {
                public long getIdentifier(Customer customer)
                {
                    return customer.getNationKey();
                }
            },

    PHONE("c_phone", varchar(15))
            {
                public String getString(Customer customer)
                {
                    return customer.getPhone();
                }
            },

    ACCOUNT_BALANCE("c_acctbal", DOUBLE)
            {
                public double getDouble(Customer customer)
                {
                    return customer.getAccountBalance();
                }

                public long getIdentifier(Customer customer)
                {
                    return customer.getAccountBalanceInCents();
                }
            },

    MARKET_SEGMENT("c_mktsegment", varchar(10))
            {
                public String getString(Customer customer)
                {
                    return customer.getMarketSegment();
                }
            },

    COMMENT("c_comment", varchar(117))
            {
                public String getString(Customer customer)
                {
                    return customer.getComment();
                }
            };

    private final String columnName;
    private final TpchColumnType type;

    CustomerColumn(String columnName, TpchColumnType type)
    {
        this.columnName = columnName;
        this.type = type;
    }

    @Override
    public String getColumnName()
    {
        return columnName;
    }

    @Override
    public TpchColumnType getType()
    {
        return type;
    }

    @Override
    public double getDouble(Customer customer)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public long getIdentifier(Customer customer)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public int getInteger(Customer customer)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public String getString(Customer customer)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public int getDate(Customer entity)
    {
        throw new UnsupportedOperationException();
    }
}
