package com.couchbase.workshop;

import com.couchbase.client.java.query.dsl.path.index.IndexType;
import java.util.Arrays;

public class Index {

    private final String name;
    private final boolean primary;
    private final String[] fields;
    private final IndexType indexType;

    public Index(String name, boolean primary, IndexType indexType, String... fields) {
        this.name = name;
        this.primary = primary;
        this.fields = fields;
        this.indexType = indexType;
    }

    public String getName() {
        return name;
    }

    public boolean isPrimary() {
        return primary;
    }

    public String[] getFields() {
        return fields;
    }

    public IndexType getIndexType() {
        return indexType;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Index{");
        sb.append("name='").append(name).append('\'');
        sb.append(", primary=").append(primary);
        sb.append(", fields=").append(Arrays.toString(fields));
        sb.append(", indexType=").append(indexType);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Index index = (Index) o;

        if (primary != index.primary) return false;
        if (name != null ? !name.equals(index.name) : index.name != null) return false;
        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        if (!Arrays.equals(fields, index.fields)) return false;
        return indexType == index.indexType;

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (primary ? 1 : 0);
        result = 31 * result + (fields != null ? Arrays.hashCode(fields) : 0);
        result = 31 * result + (indexType != null ? indexType.hashCode() : 0);
        return result;
    }
}
