package fr.eni.clinique.common.util;

//utilisé pour les combobox

public class Item<T>
{
    private T id;
    private String description;
    
 

    public Item(T id, String description)
    {
        this.id = id;
        this.description = description;
    }


	public T getId()
    {
        return id;
    }

    public String getDescription()
    {
        return description;
    }

    public String toString()
    {
        return description;
    }
}