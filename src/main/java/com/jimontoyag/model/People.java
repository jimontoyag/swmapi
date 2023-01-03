package com.jimontoyag.model;

public record People(int id, String name) {

    @Override
    public boolean equals(Object obj)
    {
        if (obj == null) return false;
        if (this == obj) return true;
        if (this.getClass() != obj.getClass()) return false;
        People people = (People) obj;
        if (this.id != people.id) return false;
        if (this.name != people.name) return false;
        return true;
    }
}
