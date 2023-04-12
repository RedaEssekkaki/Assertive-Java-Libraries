package org.examples;

public class UniDirectionalAssociation<E> {
    private E associatedValue;

    public UniDirectionalAssociation()
    {
        associatedValue = null;
    }

    public E getAssociatedValue()
    {
        return associatedValue;
    }

    public void setAssociatedValue(E associatedValue) {
        this.associatedValue = associatedValue;
    }

    public boolean isSet()
    {
        return associatedValue != null;
    }
}
