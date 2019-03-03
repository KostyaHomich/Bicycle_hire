package epam.project.database.dao;

import java.io.Serializable;

public interface Identified<PK extends Serializable> {
    /**
     * Get primary key
     * @return primary key
     */
    PK getId();
    void setId(int id);
}