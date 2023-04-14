package org.examples;

public class BiDirectionalAssociation<E> {

    private BiDirectionalAssociation associatedObject;

    private E value;

    public BiDirectionalAssociation(){}

    public void set(BiDirectionalAssociation<E>  obj)
    {
        if (this.associatedObject != null)
            this.associatedObject.basicUnSet();
        this.associatedObject = obj;
        obj.basicSet(this);
    }

    private void basicSet(BiDirectionalAssociation<E>  obj)
    {
        this.associatedObject = obj;
    }

    public void unSet()
    {
        if (this.associatedObject != null)
           this.associatedObject.basicUnSet();

        this.associatedObject = null;
    }

    private void basicUnSet()
    {
        this.associatedObject = null;
    }

    public Object get()
    {
        return associatedObject;
    }

    public E value()
    {
        return  value;
    }

    public void setValue(E value)
    {
        this.value = value;
    }


}
